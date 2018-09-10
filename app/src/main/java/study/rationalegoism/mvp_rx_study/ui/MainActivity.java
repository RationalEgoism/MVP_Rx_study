package study.rationalegoism.mvp_rx_study.ui;

import android.arch.persistence.room.Room;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import study.rationalegoism.mvp_rx_study.AppContextSingleton;
import study.rationalegoism.mvp_rx_study.R;
import study.rationalegoism.mvp_rx_study.data.database.RandomUsersDao;
import study.rationalegoism.mvp_rx_study.data.database.RandomUsersDb;
import study.rationalegoism.mvp_rx_study.data.model.Person;
import study.rationalegoism.mvp_rx_study.ui.presenter.MainPresenter;
import study.rationalegoism.mvp_rx_study.ui.adapter.RandomUserAdapter;

public class MainActivity extends AppCompatActivity implements MainContract.View {
    @BindView(R.id.rvRandomUsers) RecyclerView mRecycleView;
    @BindView(R.id.refresh) SwipeRefreshLayout refreshLayout;
    @BindView(R.id.tvNotification) TextView tvNotification;

    private MainContract.Presenter mPresenter;
    private RandomUserAdapter mRandomUserAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initializeAll();
    }

    private void initializeAll() {
        AppContextSingleton.getInstance().init(this);
        initView();
        initPresenter();
    }

    private void initPresenter() {
        RandomUsersDb randomUsersDb = Room.databaseBuilder(AppContextSingleton.getInstance().getContext(),
                RandomUsersDb.class, "random-users-database").build();
        RandomUsersDao randomUsersDao = randomUsersDb.randomUsersDao();
        Scheduler ioScheduler = Schedulers.io();
        Scheduler uiScheduler = AndroidSchedulers.mainThread();


        mPresenter = new MainPresenter(this, randomUsersDao, ioScheduler, uiScheduler);
    }

    private void initView() {
        mRecycleView.setLayoutManager(
                new LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL,
                false));
        mRandomUserAdapter = new RandomUserAdapter();
        mRecycleView.setAdapter(mRandomUserAdapter);

        refreshLayout.setOnRefreshListener(() -> {
            //if we have internet connection -> refresh, else -> display old data
            mPresenter.getRandomUsers(
                    AppContextSingleton.getInstance().
                    internetConnectionAvailable());
        });

        tvNotification.setVisibility(View.GONE);
    }

    @Override
    public void displayRandomUsers(List<Person> personList) {
        tvNotification.setVisibility(View.GONE);
        mRandomUserAdapter.setPersonList(personList);
    }

    @Override public void stopLoadingIndicator() {
        if (refreshLayout.isRefreshing()) {
            refreshLayout.setRefreshing(false);
        }
    }

    @Override
    public void clearRandomUsers(){
        mRandomUserAdapter.clearData();
    }

    @Override public void showNoDataMessage() {
        showNotification(getString(R.string.msg_no_data));
    }

    @Override public void showErrorMessage(String error) {
        showNotification(error);
    }

    private void showNotification(String message) {
        tvNotification.setVisibility(View.VISIBLE);
        tvNotification.setText(message);
    }
}
