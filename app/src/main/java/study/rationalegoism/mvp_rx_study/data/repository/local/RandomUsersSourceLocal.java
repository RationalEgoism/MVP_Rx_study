package study.rationalegoism.mvp_rx_study.data.repository.local;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Flowable;
import study.rationalegoism.mvp_rx_study.data.database.RandomUsersDao;
import study.rationalegoism.mvp_rx_study.data.model.Person;
import study.rationalegoism.mvp_rx_study.data.repository.RandomUsersSource;

public class RandomUsersSourceLocal implements RandomUsersSource {
    private RandomUsersDao randomUsersDao;

    @Inject
    public RandomUsersSourceLocal(RandomUsersDao randomUsersDao) {
        this.randomUsersDao = randomUsersDao;
    }

    @Override
    public Flowable<List<Person>> loadPersons(boolean refreshRequired) {
        return randomUsersDao.getAllRandomUsers();
    }

    @Override
    public void addPerson(Person person) {
        randomUsersDao.insert(person);
    }

    @Override
    public void clearData() {
        randomUsersDao.deleteAll();
    }
}
