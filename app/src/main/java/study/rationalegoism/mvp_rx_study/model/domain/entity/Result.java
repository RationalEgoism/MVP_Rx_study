package study.rationalegoism.mvp_rx_study.model.domain.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class Result {

    @SerializedName("name")
    @Expose
    private Name name;

    @SerializedName("picture")
    @Expose
    private Picture picture;

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
