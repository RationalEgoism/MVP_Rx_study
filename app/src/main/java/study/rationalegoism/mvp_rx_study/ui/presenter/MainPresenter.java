package study.rationalegoism.mvp_rx_study.ui.presenter;

import android.os.AsyncTask;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import study.rationalegoism.mvp_rx_study.data.network.RandomUsersService;
import study.rationalegoism.mvp_rx_study.data.network.RandomUsersServiceFactory;
import study.rationalegoism.mvp_rx_study.ui.MainContract;
import study.rationalegoism.mvp_rx_study.data.classes.Person;
import study.rationalegoism.mvp_rx_study.data.database.RandomUsersDao;
import study.rationalegoism.mvp_rx_study.data.model.RandomUsers;
import study.rationalegoism.mvp_rx_study.data.model.Result;
import study.rationalegoism.mvp_rx_study.ui.presenter.loadRandomUsersAsync.LoadRandomUsersAsync;
import study.rationalegoism.mvp_rx_study.ui.presenter.loadRandomUsersAsync.OnDownloadListener;

public class MainPresenter implements MainContract.Presenter {
    private final MainContract.View mView;
    private final RandomUsersDao randomUsersDao;
    private final RandomUsersService mRandomUsersService;


    public MainPresenter(MainContract.View mView, RandomUsersDao randomUsersDao) {
        this.mView = mView;
        this.randomUsersDao = randomUsersDao;
        mRandomUsersService = RandomUsersServiceFactory.makeRandomUsersService();
    }

    @Override
    public void getRandomUsers(boolean isOnline) {
        if(isOnline) {
            getRandomUsersOnline();
        }
        else {
            loadRandomUsers();
        }
    }

    private void getRandomUsersOnline() {
        getRandomUsersList(new Callback<RandomUsers>() {
            @Override
            public void onResponse(Call<RandomUsers> call, Response<RandomUsers> response) {
                insertDataToStore(response.body().getResults());
                loadRandomUsers();
            }

            @Override
            public void onFailure(Call<RandomUsers> call, Throwable t) {

            }
        });
    }

    private void getRandomUsersList(Callback<RandomUsers> randomUsersCallback) {
        mRandomUsersService.getRandomUsers(10, "gb")
                .enqueue(randomUsersCallback);
    }

    private void loadRandomUsers(){
        LoadRandomUsersAsync task = new LoadRandomUsersAsync(new OnDownloadListener() {
            @Override
            public void onSuccess(List<Result> results) {
                mView.displayRandomUsers(results);
            }
        });
        task.execute(randomUsersDao);
    }

    private void insertDataToStore(List<Result> results){
        //TODO FIX IT
        int id = 0;
        for(Result result: results){
            Person person = new Person(id++, result.getPicture().getMedium(), result.getName().getFirst(), result.getPhone());
            new AsyncTask<Person, Void, Void>() {
                @Override
                protected Void doInBackground(Person... people) {
                    randomUsersDao.insert(people[0]);
                    return null;
                }
            }.execute(person);

        }
    }
}
