package study.rationalegoism.mvp_rx_study.data.repository;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Flowable;
import study.rationalegoism.mvp_rx_study.data.model.Person;

public class RandomUsersRepository implements RandomUsersStore {
    private final RandomUsersStore localStorage;
    private final RandomUsersStore remoteStorage;
    private List<Person> cache;

    public RandomUsersRepository(RandomUsersStore localStorage, RandomUsersStore remoteStorage) {
        this.localStorage = localStorage;
        this.remoteStorage = remoteStorage;

        cache = new ArrayList<>();
    }

    @Override
    public Flowable<List<Person>> loadPersons(boolean refreshRequired) {
        if(refreshRequired)
            return refresh();
        else
            // if cache is available, return it immediately
            if(!cache.isEmpty())
                return Flowable.just(cache);
            else{
                //else return data from local storage
                return localStorage.loadPersons(false)
                        .take(1)
                        //get Flowable<Person> from Flowable<List<Person>> from caching then
                        .flatMap(Flowable::fromIterable)
                        .doOnNext(person -> cache.add(person))
                        .toList()
                        .toFlowable()
                        .filter(list -> !list.isEmpty())
                        //if local data is empty, get data from remote source instead
                        .switchIfEmpty(refresh());
            }
    }


    private Flowable<List<Person>> refresh(){
        return remoteStorage.loadPersons(true)
                .take(1)
                .doOnNext(list -> {
                    cache.clear();
                    clearData();
                })
                .flatMap(Flowable::fromIterable)
                .doOnNext(this::addPerson)
                .toList()
                .toFlowable();
    }

    //Only localStorage can do it
    @Override
    public void addPerson(Person person) {
        localStorage.addPerson(person);
    }

    //Only localStorage can do it
    @Override
    public void clearData() {
        localStorage.clearData();
    }
}
