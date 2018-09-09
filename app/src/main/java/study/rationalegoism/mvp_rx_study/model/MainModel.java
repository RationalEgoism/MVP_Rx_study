package study.rationalegoism.mvp_rx_study.model;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.FutureTarget;

import java.io.File;
import java.util.List;

import retrofit2.Callback;
import study.rationalegoism.mvp_rx_study.AppContextSingleton;
import study.rationalegoism.mvp_rx_study.MainContract;
import study.rationalegoism.mvp_rx_study.model.domain.entity.RandomUsers;
import study.rationalegoism.mvp_rx_study.model.network.RandomUsersService;
import study.rationalegoism.mvp_rx_study.model.network.RandomUsersServiceFactory;

public class MainModel implements MainContract.Model {

    private MainContract.Presenter mPresenter;
    private final RandomUsersService mRandomUsersService;

    public MainModel(MainContract.Presenter mPresenter) {
        this.mPresenter = mPresenter;
        mRandomUsersService = RandomUsersServiceFactory.makeRandomUsersService();
    }

    @Override
    public void getRandomUsersList(Callback<RandomUsers> randomUsersCallback) {
        mRandomUsersService.getRandomUsers(10, "gb")
                .enqueue(randomUsersCallback);
    }

    @Override
    public void saveRandomUsersToDB(RandomUsers randomUsers) {

    }

}
