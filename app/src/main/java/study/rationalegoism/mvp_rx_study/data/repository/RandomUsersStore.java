package study.rationalegoism.mvp_rx_study.data.repository;

import java.util.List;

import study.rationalegoism.mvp_rx_study.data.model.Person;

public interface RandomUsersStore {
    List<Person> loadPersons();

    void addPerson(Person person);

    void clearData();
}
