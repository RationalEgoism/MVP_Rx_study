package study.rationalegoism.mvp_rx_study.presenter;

import okhttp3.Cache;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import study.rationalegoism.mvp_rx_study.domain.entity.RandomUsers;
import study.rationalegoism.mvp_rx_study.model.MainModel;
import study.rationalegoism.mvp_rx_study.presenter.contract.MainContract;
import timber.log.Timber;

public class MainPresenter implements MainContract.Presenter {
    private MainContract.View mView;
    private MainContract.Model mModel;

    public MainPresenter(MainContract.View mView, Cache cacheFile, String randomUsersBaseUrl) {
        this.mView = mView;
        initModel(cacheFile, randomUsersBaseUrl);
    }

    private void initModel(Cache cacheFile, String randomUsersBaseUrl) {
        mModel = new MainModel(this, cacheFile, randomUsersBaseUrl);
    }


    @Override
    public void getRandomUsers() {
        Call<RandomUsers> randomUsersCall = mModel.getRandomUsersCall();
        randomUsersCall.enqueue(new Callback<RandomUsers>() {
            @Override
            public void onResponse(Call<RandomUsers> call, Response<RandomUsers> response) {
                if(response.isSuccessful()){
                    mView.displayRandomUsers(response.body().getResults());
                }
            }

            //TODO make some notification to user
            @Override
            public void onFailure(Call<RandomUsers> call, Throwable t) {
                Timber.i(t.getMessage());
            }
        });
    }
}
