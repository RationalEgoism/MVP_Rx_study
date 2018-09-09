package study.rationalegoism.mvp_rx_study.model.domain.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class Name {

    @SerializedName("first")
    @Expose
    private String first;

    @SerializedName("second")
    @Expose
    private String second;

    public String getFirst() {
        return first;
    }

    public void setFirst(String first) {
        this.first = first;
    }

    public String getSecond() {
        return second;
    }

    public void setSecond(String second) {
        this.second = second;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("first", first)
                .append("second", second)
                .toString();
    }
}
