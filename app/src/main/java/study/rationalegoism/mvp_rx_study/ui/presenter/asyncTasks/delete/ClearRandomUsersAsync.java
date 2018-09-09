package study.rationalegoism.mvp_rx_study.ui.presenter.asyncTasks.delete;

import android.os.AsyncTask;

import study.rationalegoism.mvp_rx_study.data.database.RandomUsersDao;

public class ClearRandomUsersAsync extends AsyncTask<RandomUsersDao, Void, Void>{
    private OnDeleteListener onDeleteListener;

    public ClearRandomUsersAsync(OnDeleteListener onDeleteListener) {
        this.onDeleteListener = onDeleteListener;
    }

    @Override
    protected Void doInBackground(RandomUsersDao... randomUsersDaos) {
        randomUsersDaos[0].deleteAll();

        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);

        onDeleteListener.onSuccess();
    }
}
