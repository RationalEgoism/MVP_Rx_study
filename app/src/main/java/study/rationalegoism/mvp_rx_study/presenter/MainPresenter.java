package study.rationalegoism.mvp_rx_study.presenter;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import study.rationalegoism.mvp_rx_study.MainContract;
import study.rationalegoism.mvp_rx_study.data.database.RandomUsersDao;
import study.rationalegoism.mvp_rx_study.model.MainModel;
import study.rationalegoism.mvp_rx_study.model.domain.entity.RandomUsers;

public class MainPresenter implements MainContract.Presenter {
    private MainContract.View mView;
    private MainContract.Model mModel;
    private RandomUsersDao randomUsersDao;

    public MainPresenter(MainContract.View mView, RandomUsersDao randomUsersDao) {
        this.mView = mView;
        this.randomUsersDao = randomUsersDao;
        initModel();
    }

    private void initModel() {
        mModel = new MainModel(this);
    }


    @Override
    public void getRandomUsers() {
        mModel.getRandomUsersList(new Callback<RandomUsers>() {
            @Override
            public void onResponse(Call<RandomUsers> call, Response<RandomUsers> response) {
                mModel.saveRandomUsersToDB(response.body());
                mView.displayRandomUsers(response.body().getResults());
            }

            @Override
            public void onFailure(Call<RandomUsers> call, Throwable t) {

            }
        });
    }
}
