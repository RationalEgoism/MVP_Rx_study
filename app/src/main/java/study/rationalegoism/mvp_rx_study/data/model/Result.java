package study.rationalegoism.mvp_rx_study.data.model;

import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Entity;

import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.ToStringBuilder;

@Entity
public class Result {

    @SerializedName("name")
    //signal that nested fields can be referenced directly in the SQL queries.
    @Embedded(prefix = "name_")
    private Name name;

    @SerializedName("picture")
    @Embedded(prefix = "picture_")
    private Picture picture;

    @SerializedName("phone")
    private String phone;

    public Result(Name name, Picture picture, String phone) {
        this.name = name;
        this.picture = picture;
        this.phone = phone;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("name", name)
                .append("picture", picture)
                .append("phone", phone)
                .toString();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
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
}
