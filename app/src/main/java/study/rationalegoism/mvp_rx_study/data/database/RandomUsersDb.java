package study.rationalegoism.mvp_rx_study.data.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import study.rationalegoism.mvp_rx_study.data.model.Person;

@Database(entities = Person.class, version = 1)
public abstract class RandomUsersDb extends RoomDatabase{
    public abstract RandomUsersDao randomUsersDao();
}
