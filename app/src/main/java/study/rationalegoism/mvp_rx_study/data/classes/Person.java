package study.rationalegoism.mvp_rx_study.data.classes;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

//Table
@Entity
public class Person {
    @PrimaryKey
    private long id;

    private String name;

    private String phone;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
