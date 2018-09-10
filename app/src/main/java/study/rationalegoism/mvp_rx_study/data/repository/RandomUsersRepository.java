package study.rationalegoism.mvp_rx_study.data.repository;

import java.util.List;

import io.reactivex.Flowable;
import study.rationalegoism.mvp_rx_study.data.model.Person;

public class RandomUsersRepository implements RandomUsersStore {
    private final RandomUsersStore localStorage;
    private final RandomUsersStore remoteStorage;

    public RandomUsersRepository(RandomUsersStore localStorage, RandomUsersStore remoteStorage) {
        this.localStorage = localStorage;
        this.remoteStorage = remoteStorage;
    }

    @Override
    public Flowable<List<Person>> loadPersons(boolean refreshRequired) {
        if(refreshRequired)
            return refresh(refreshRequired);
        else
            return localStorage.loadPersons(false)
                    .take(1)
                    .filter(list -> !list.isEmpty());
    }


    private Flowable<List<Person>> refresh(boolean refreshRequired){
        return remoteStorage.loadPersons(refreshRequired)
                .take(1)
                .doOnNext(list -> clearData())
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
