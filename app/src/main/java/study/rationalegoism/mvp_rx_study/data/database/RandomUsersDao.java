package study.rationalegoism.mvp_rx_study.data.database;

import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

import study.rationalegoism.mvp_rx_study.data.classes.Person;

public interface RandomUsersDao {

    @Query("SELECT * FROM person")
    List<Person> getAllRandomUsers();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Person person);

    @Query("DELETE FROM person")
    void deleteAll();
}
