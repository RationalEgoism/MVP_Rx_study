package study.rationalegoism.mvp_rx_study.presenter;

import android.os.AsyncTask;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import study.rationalegoism.mvp_rx_study.MainContract;
import study.rationalegoism.mvp_rx_study.data.classes.Person;
import study.rationalegoism.mvp_rx_study.data.database.RandomUsersDao;
import study.rationalegoism.mvp_rx_study.model.MainModel;
import study.rationalegoism.mvp_rx_study.model.domain.entity.Name;
import study.rationalegoism.mvp_rx_study.model.domain.entity.Picture;
import study.rationalegoism.mvp_rx_study.model.domain.entity.RandomUsers;
import study.rationalegoism.mvp_rx_study.model.domain.entity.Result;
import study.rationalegoism.mvp_rx_study.presenter.loadRandomUsersAsync.LoadRandomUsersAsync;
import study.rationalegoism.mvp_rx_study.presenter.loadRandomUsersAsync.OnDownloadListener;

public class MainPresenter implements MainContract.Presenter {
    private final MainContract.View mView;
    private MainContract.Model mModel;
    private final RandomUsersDao randomUsersDao;

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
                insertDataToStore(response.body().getResults());
                loadRandomUsers();
            }

            @Override
            public void onFailure(Call<RandomUsers> call, Throwable t) {

            }
        });
    }

    private void loadRandomUsers(){
        LoadRandomUsersAsync task = new LoadRandomUsersAsync(new OnDownloadListener() {
            @Override
            public void onSuccess(List<Result> results) {
                mView.displayRandomUsers(results);
            }
        });
        task.execute(randomUsersDao);

//        new AsyncTask<Void, Void, Void>() {
//            List<Result> results = new ArrayList<>();
//            List<Person> personList = new ArrayList<>();
//
//            @Override
//            protected Void doInBackground(Void... voids) {
//                personList = randomUsersDao.getAllRandomUsers();
//                for(Person person: personList){
//                    Result result = new Result(new Name(person.getName()), new Picture(person.getImage()), person.getPhone());
//                    results.add(result);
//                }
//                return null;
//            }
//
//            @Override
//            protected void onPostExecute(Void aVoid) {
//                super.onPostExecute(aVoid);
//                users.addAll(results);
//            }
//        }.execute();
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
