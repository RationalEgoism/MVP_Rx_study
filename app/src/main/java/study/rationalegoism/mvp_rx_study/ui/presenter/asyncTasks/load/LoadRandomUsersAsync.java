package study.rationalegoism.mvp_rx_study.ui.presenter.asyncTasks.load;

import android.os.AsyncTask;

import java.util.ArrayList;
import java.util.List;

import study.rationalegoism.mvp_rx_study.data.database.RandomUsersDao;
import study.rationalegoism.mvp_rx_study.data.model.Person;

public class LoadRandomUsersAsync extends AsyncTask<RandomUsersDao, Void, List<Person>>{

    private OnDownloadListener onDownloadListener;
    private List<Person> personList = new ArrayList<>();

    public LoadRandomUsersAsync(OnDownloadListener onDownloadListener) {
        this.onDownloadListener = onDownloadListener;
    }

    @Override
    protected List<Person> doInBackground(RandomUsersDao... randomUsersDaos) {
        personList = randomUsersDaos[0].getAllRandomUsers();
        return personList;
    }

    @Override
    protected void onPostExecute(List<Person> personList) {
        super.onPostExecute(personList);
        onDownloadListener.onSuccess(personList);
    }
}
