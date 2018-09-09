package study.rationalegoism.mvp_rx_study;

import android.arch.persistence.room.Room;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.List;

import study.rationalegoism.mvp_rx_study.data.database.RandomUsersDao;
import study.rationalegoism.mvp_rx_study.data.database.RandomUsersDb;
import study.rationalegoism.mvp_rx_study.model.domain.entity.Result;
import study.rationalegoism.mvp_rx_study.presenter.MainPresenter;
import study.rationalegoism.mvp_rx_study.view.adapter.RandomUserAdapter;

//This file should be in the same package as the AppContextSingleton
public class MainActivity extends AppCompatActivity implements MainContract.View {
    private MainContract.Presenter mPresenter;
    private RecyclerView mRecycleView;
    private final RandomUserAdapter mRandomUserAdapter = new RandomUserAdapter(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
        mRecycleView = findViewById(R.id.rvRandomUsers);
        mRecycleView.setLayoutManager(
                new LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL,
                false));
        mRecycleView.setAdapter(mRandomUserAdapter);
    }

    @Override
    public void displayRandomUsers(List<Result> resultList) {
        mRandomUserAdapter.setResultList(resultList);
    }

    @Override
    public void getRandomUsersListFromActivity() {
        mPresenter.getRandomUsers();
    }
}
