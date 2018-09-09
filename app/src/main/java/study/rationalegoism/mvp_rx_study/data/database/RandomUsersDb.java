package study.rationalegoism.mvp_rx_study.data.database;

import android.arch.persistence.room.Database;

import study.rationalegoism.mvp_rx_study.data.classes.Person;

@Database(entities = Person.class, version = 1)
public abstract class RandomUsersDb {
    public abstract RandomUsersDao randomUsersDao();
}
