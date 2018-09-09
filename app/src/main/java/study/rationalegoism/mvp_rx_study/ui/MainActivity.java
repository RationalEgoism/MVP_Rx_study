package study.rationalegoism.mvp_rx_study.ui;

import android.arch.persistence.room.Room;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import study.rationalegoism.mvp_rx_study.AppContextSingleton;
import study.rationalegoism.mvp_rx_study.R;
import study.rationalegoism.mvp_rx_study.data.database.RandomUsersDao;
import study.rationalegoism.mvp_rx_study.data.database.RandomUsersDb;
import study.rationalegoism.mvp_rx_study.model.domain.entity.Result;
import study.rationalegoism.mvp_rx_study.presenter.MainPresenter;
import study.rationalegoism.mvp_rx_study.ui.adapter.RandomUserAdapter;

public class MainActivity extends AppCompatActivity implements MainContract.View {
    @BindView(R.id.rvRandomUsers) RecyclerView mRecycleView;
    private MainContract.Presenter mPresenter;
    private RandomUserAdapter mRandomUserAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initializeAll();
        getRandomUsersListFromActivity();
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


        mPresenter = new MainPresenter(this, randomUsersDao);
    }

    private void initView() {
        mRecycleView.setLayoutManager(
                new LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL,
                false));
        mRandomUserAdapter = new RandomUserAdapter();
        mRecycleView.setAdapter(mRandomUserAdapter);
    }

    @Override
    public void displayRandomUsers(List<Result> resultList) {
        mRandomUserAdapter.setResultList(resultList);
    }

    @Override
    public void getRandomUsersListFromActivity() {
        mPresenter.getRandomUsers(AppContextSingleton.getInstance().
                internetConnectionAvailable(200));
    }


}
