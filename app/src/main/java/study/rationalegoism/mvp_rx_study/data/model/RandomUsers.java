package study.rationalegoism.mvp_rx_study.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.ArrayList;
import java.util.List;

public class RandomUsers {

    @SerializedName("results")
    @Expose
    private List<Person> persons;

    public List<Person> getPersons() {
        return persons;
    }

    public void setPersons(List<Person> persons) {
        this.persons = persons;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("persons", persons)
                .toString();
    }
}
