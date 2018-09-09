package study.rationalegoism.mvp_rx_study.ui.presenter.loadRandomUsersAsync;

import java.util.List;

import study.rationalegoism.mvp_rx_study.data.model.Result;

public interface OnDownloadListener {
    public void onSuccess(List<Result> results);
}
