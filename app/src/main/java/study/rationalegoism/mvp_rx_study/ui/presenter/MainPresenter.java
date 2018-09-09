package study.rationalegoism.mvp_rx_study.ui.presenter;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import study.rationalegoism.mvp_rx_study.data.network.RandomUsersService;
import study.rationalegoism.mvp_rx_study.data.network.RandomUsersServiceFactory;
import study.rationalegoism.mvp_rx_study.ui.MainContract;
import study.rationalegoism.mvp_rx_study.data.database.RandomUsersDao;
import study.rationalegoism.mvp_rx_study.data.model.RandomUsers;
import study.rationalegoism.mvp_rx_study.data.model.Person;
import study.rationalegoism.mvp_rx_study.ui.presenter.asyncTasks.delete.ClearRandomUsersAsync;
import study.rationalegoism.mvp_rx_study.ui.presenter.asyncTasks.delete.OnDeleteListener;
import study.rationalegoism.mvp_rx_study.ui.presenter.asyncTasks.insert.InsertRandomUsersAsync;
import study.rationalegoism.mvp_rx_study.ui.presenter.asyncTasks.load.LoadRandomUsersAsync;
import study.rationalegoism.mvp_rx_study.ui.presenter.asyncTasks.load.OnDownloadListener;

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
    public void getRandomUsers(boolean refresh) {
        if(refresh) {
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
                insertDataToStore(response.body().getPersons());
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
            public void onSuccess(List<Person> personList) {
                mView.displayRandomUsers(personList);
            }
        });
        task.execute(randomUsersDao);
    }

    private void insertDataToStore(List<Person> personList){
        final List<Person> personListCopy = new ArrayList<>(personList);
        ClearRandomUsersAsync clearRandomUsersAsync = new ClearRandomUsersAsync(new OnDeleteListener() {
            @Override
            public void onSuccess() {
                }
        });
        clearRandomUsersAsync.execute(randomUsersDao);
        for (Person person : personListCopy) {
            InsertRandomUsersAsync task = new InsertRandomUsersAsync(person);
            task.execute(randomUsersDao);
        }
    }

    @Override
    public void onAttach() {
        getRandomUsers(false);
    }

    @Override
    public void onDetach() {
        //TODO
    }
}
