package study.rationalegoism.mvp_rx_study.ui.presenter.asyncTasks.loadRandomUsersAsync;

import java.util.List;

import study.rationalegoism.mvp_rx_study.data.model.Person;

public interface OnDownloadListener {
    void onSuccess(List<Person> results);
}
