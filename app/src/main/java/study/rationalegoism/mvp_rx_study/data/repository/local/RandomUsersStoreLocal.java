package study.rationalegoism.mvp_rx_study.data.repository.local;

import java.util.List;

import study.rationalegoism.mvp_rx_study.data.database.RandomUsersDao;
import study.rationalegoism.mvp_rx_study.data.model.Person;
import study.rationalegoism.mvp_rx_study.data.repository.RandomUsersStore;

public class RandomUsersStoreLocal implements RandomUsersStore {
    private RandomUsersDao randomUsersDao;

    public RandomUsersStoreLocal(RandomUsersDao randomUsersDao) {
        this.randomUsersDao = randomUsersDao;
    }

    @Override
    public List<Person> loadPersons() {
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
