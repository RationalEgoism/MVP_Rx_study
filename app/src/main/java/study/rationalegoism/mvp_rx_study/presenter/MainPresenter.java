package study.rationalegoism.mvp_rx_study.presenter;

import study.rationalegoism.mvp_rx_study.presenter.contract.MainContract;

public class MainPresenter implements MainContract.Presenter {
    private  MainContract.View mView;

    public MainPresenter(MainContract.View mView) {
        this.mView = mView;
    }
}
