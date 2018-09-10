package study.rationalegoism.mvp_rx_study.data.repository;

import java.util.List;

import io.reactivex.Flowable;
import study.rationalegoism.mvp_rx_study.data.model.Person;

public interface RandomUsersStore {
    Flowable<List<Person>> loadPersons(boolean refreshRequired);

    void addPerson(Person person);

    void clearData();
}
