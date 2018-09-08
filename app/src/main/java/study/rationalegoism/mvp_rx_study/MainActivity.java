package study.rationalegoism.mvp_rx_study;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.io.File;
import java.util.List;

import okhttp3.Cache;
import study.rationalegoism.mvp_rx_study.domain.entity.Result;
import study.rationalegoism.mvp_rx_study.presenter.MainPresenter;
import study.rationalegoism.mvp_rx_study.presenter.contract.MainContract;
import study.rationalegoism.mvp_rx_study.view.adapter.RandomUserAdapter;

public class MainActivity extends AppCompatActivity implements MainContract.View {
    private MainContract.Presenter mPresenter;
    private RecyclerView mRecycleView;
    private final RandomUserAdapter mRandomUserAdapter = new RandomUserAdapter(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initPresenter();
        getRandomUsersList();
    }

    private void initPresenter() {
        mPresenter = new MainPresenter(this, getCacheFile(), getRandomUsersBaseUrl());
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
    public void getRandomUsersList() {
        mPresenter.getRandomUsers();
    }

    private Cache getCacheFile(){
        File cacheFile = new File(this.getCacheDir(), "HttpCache");
        return new Cache(cacheFile, 10 * 1024 * 1024);
    }

    private String getRandomUsersBaseUrl(){
        return this.getResources().getString(R.string.randomUserBaseUrl);
    }
}
