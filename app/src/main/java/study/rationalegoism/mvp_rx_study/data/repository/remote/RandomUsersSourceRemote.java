package study.rationalegoism.mvp_rx_study.data.repository.remote;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Flowable;
import study.rationalegoism.mvp_rx_study.data.model.Person;
import study.rationalegoism.mvp_rx_study.data.api.RandomUsers;
import study.rationalegoism.mvp_rx_study.data.api.RandomUsersService;
import study.rationalegoism.mvp_rx_study.data.repository.RandomUsersSource;

public class RandomUsersSourceRemote implements RandomUsersSource {
    private final RandomUsersService randomUsersService;

    public RandomUsersSourceRemote(RandomUsersService randomUsersService) {
        this.randomUsersService = randomUsersService;
    }

    @Override
    public Flowable<List<Person>> loadPersons(boolean refreshRequired) {
        // Get data from Api, serialize JSON to RandomUsers by ConvertFactory,
        // and here .map() convert Flowable<RansomUsers> to Flowable<List<Person>>
        return randomUsersService.getRandomUsers(10, "gb")
                .map(RandomUsers::getPersons);
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
