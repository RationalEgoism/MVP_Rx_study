package study.rationalegoism.mvp_rx_study.ui.presenter.asyncTasks.insertRandomUsersAsync;

import android.os.AsyncTask;

import study.rationalegoism.mvp_rx_study.data.database.RandomUsersDao;
import study.rationalegoism.mvp_rx_study.data.model.Person;

public class InsertRandomUsersAsync extends AsyncTask<RandomUsersDao, Void, Void> {
    private Person person;

    public InsertRandomUsersAsync(Person person) {
        this.person = person;
    }

    @Override
    protected Void doInBackground(RandomUsersDao... randomUsersDaos) {
        randomUsersDaos[0].insert(person);
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
    }
}
