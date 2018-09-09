package study.rationalegoism.mvp_rx_study.data.model;

import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.ToStringBuilder;

@Entity
public class Person {

    @SerializedName("name")
    //signal that nested fields can be referenced directly in the SQL queries.
    @Embedded(prefix = "name_")
    @Expose
    private Name name;

    @SerializedName("picture")
    @Embedded(prefix = "picture_")
    @Expose
    private Picture picture;

    @PrimaryKey
    @NonNull
    @SerializedName("phone")
    @Expose
    private String phone;

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("name", name)
                .append("picture", picture)
                .append("phone", phone)
                .toString();
    }

    public Person(Name name, Picture picture, @NonNull String phone) {
        this.name = name;
        this.picture = picture;
        this.phone = phone;
    }

    public Name getName() {
        return name;
    }

    public void setName(Name name) {
        this.name = name;
    }

    public Picture getPicture() {
        return picture;
    }

    public void setPicture(Picture picture) {
        this.picture = picture;
    }

    @NonNull
    public String getPhone() {
        return phone;
    }

    public void setPhone(@NonNull String phone) {
        this.phone = phone;
    }
}
