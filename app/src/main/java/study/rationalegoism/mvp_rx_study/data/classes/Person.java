package study.rationalegoism.mvp_rx_study.data.classes;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

//Table
@Entity
public class Person {
    @PrimaryKey
    private long id;

    private String image;

    private String name;

    private String phone;

    public Person(long id, String image, String name, String phone) {
        this.id = id;
        this.image = image;
        this.name = name;
        this.phone = phone;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
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
