package study.rationalegoism.mvp_rx_study.presenter.loadRandomUsersAsync;

import java.util.List;

import study.rationalegoism.mvp_rx_study.model.domain.entity.Result;

public interface OnDownloadListener {
    public void onSuccess(List<Result> results);
}
