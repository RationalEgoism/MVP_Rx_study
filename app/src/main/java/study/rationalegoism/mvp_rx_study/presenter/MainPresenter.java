package study.rationalegoism.mvp_rx_study.presenter;

import study.rationalegoism.mvp_rx_study.networking.NetworkingServiceApi;
import study.rationalegoism.mvp_rx_study.presenter.contract.MainContract;

public class MainPresenter implements MainContract.Presenter {
    private MainContract.View mView;
    private NetworkingServiceApi networkingService;

    public MainPresenter(MainContract.View mView, NetworkingServiceApi networkingService) {
        this.mView = mView;
        this.networkingService = networkingService;
    }

    @Override
    public void getRandomUsers() {

    }
}
