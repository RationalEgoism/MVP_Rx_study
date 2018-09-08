package study.rationalegoism.mvp_rx_study.domain.entity;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.ArrayList;
import java.util.List;

public class RandomUsers {

    private List<Result> results = null;

    public List<Result> getResults() {
        return results;
    }

    public void setResults(List<Result> results) {
        this.results = results;
    }

    public RandomUsers(List<Result> results) {
        this.results = results;
    }

    public List<String> getImagesList(){
        List<String> imagesList = new ArrayList<>();
        for(Result result: results)
            imagesList.add(result.getPicture().getMedium());
        return imagesList;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("results", results)
                .toString();
    }
}
