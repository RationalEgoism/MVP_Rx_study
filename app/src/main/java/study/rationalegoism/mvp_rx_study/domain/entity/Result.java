package study.rationalegoism.mvp_rx_study.domain.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Result {

    @SerializedName("name")
    @Expose
    private Name name;

    @SerializedName("picture")
    @Expose
    private Picture picture;
}
