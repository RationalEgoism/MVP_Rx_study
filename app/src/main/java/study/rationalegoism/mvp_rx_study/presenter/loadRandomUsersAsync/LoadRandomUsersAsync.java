package study.rationalegoism.mvp_rx_study.presenter.loadRandomUsersAsync;

import android.os.AsyncTask;

import java.util.ArrayList;
import java.util.List;

import study.rationalegoism.mvp_rx_study.data.classes.Person;
import study.rationalegoism.mvp_rx_study.data.database.RandomUsersDao;
import study.rationalegoism.mvp_rx_study.model.domain.entity.Name;
import study.rationalegoism.mvp_rx_study.model.domain.entity.Picture;
import study.rationalegoism.mvp_rx_study.model.domain.entity.Result;

public class LoadRandomUsersAsync extends AsyncTask<RandomUsersDao, Void, List<Result>>{

    private OnDownloadListener onDownloadListener;
    private List<Result> results = new ArrayList<>();
    private List<Person> personList = new ArrayList<>();

    public LoadRandomUsersAsync(OnDownloadListener onDownloadListener) {
        this.onDownloadListener = onDownloadListener;
    }

    @Override
    protected List<Result> doInBackground(RandomUsersDao... randomUsersDaos) {
        personList = randomUsersDaos[0].getAllRandomUsers();
        for(Person person: personList){
            Result result = new Result(new Name(person.getName()), new Picture(person.getImage()), person.getPhone());
            results.add(result);
        }
        return results;
    }

    @Override
    protected void onPostExecute(List<Result> results) {
        super.onPostExecute(results);
        onDownloadListener.onSuccess(results);
    }
}
