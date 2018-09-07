package study.rationalegoism.mvp_rx_study.presenter;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import study.rationalegoism.mvp_rx_study.domain.entity.RandomUsers;
import study.rationalegoism.mvp_rx_study.networking.NetworkingServiceApi;
import study.rationalegoism.mvp_rx_study.presenter.contract.MainContract;
import timber.log.Timber;

public class MainPresenter implements MainContract.Presenter {
    private MainContract.View mView;
    private NetworkingServiceApi networkingService;

    public MainPresenter(MainContract.View mView, NetworkingServiceApi networkingService) {
        this.mView = mView;
        this.networkingService = networkingService;
    }

    @Override
    public void getRandomUsers() {
        Call<RandomUsers> randomUsersCall = networkingService.getRandomUsers();
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
