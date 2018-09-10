package study.rationalegoism.mvp_rx_study.data.repository.remote;

import java.util.List;

import io.reactivex.Flowable;
import study.rationalegoism.mvp_rx_study.data.model.Person;
import study.rationalegoism.mvp_rx_study.data.network.RandomUsersService;
import study.rationalegoism.mvp_rx_study.data.repository.RandomUsersStore;

public class RandomUsersStoreRemote implements RandomUsersStore{
    private final RandomUsersService randomUsersService;

    public RandomUsersStoreRemote(RandomUsersService randomUsersService) {
        this.randomUsersService = randomUsersService;
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
