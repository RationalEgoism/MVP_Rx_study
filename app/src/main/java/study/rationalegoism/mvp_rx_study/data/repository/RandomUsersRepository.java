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
    public Flowable<List<Person>> loadPersons() {
        return null;
    }

    @Override
    public void addPerson(Person person) {

    }

    @Override
    public void clearData() {

    }
}
