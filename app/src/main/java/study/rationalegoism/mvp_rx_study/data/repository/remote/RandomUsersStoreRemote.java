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
        //Get data from Api, serialize JSON to RandomUsers by ConvertFactory,
        // here we get List of person and return it as Flowable
        return randomUsersService.getRandomUsers(10, "gb").map(randomUsers -> randomUsers.getPersons());
    }

    @Override
    public void addPerson(Person person) {
        throw new UnsupportedOperationException("Unsupported Operation");
    }

    @Override
    public void clearData() {
        throw new UnsupportedOperationException("Unsupported Operation");
    }
}
