package study.rationalegoism.mvp_rx_study.ui;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import study.rationalegoism.mvp_rx_study.R;
import study.rationalegoism.mvp_rx_study.data.model.Person;
import study.rationalegoism.mvp_rx_study.ui.adapter.RandomUserAdapter;
import study.rationalegoism.mvp_rx_study.ui.base.BaseActivity;

import static study.rationalegoism.mvp_rx_study.App.internetConnectionAvailable;

public class MainActivity extends BaseActivity implements MainContract.View {
    @BindView(R.id.rvRandomUsers) RecyclerView mRecycleView;
    @BindView(R.id.refresh) SwipeRefreshLayout refreshLayout;
    @BindView(R.id.tvNotification) TextView tvNotification;

    @Inject MainContract.Presenter mPresenter;
    private RandomUserAdapter mRandomUserAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initializeAll();
    }

    private void initializeAll() {
        initView();
        initPresenter();
    }

    private void initPresenter() {
        DaggerMainContractComponent.builder()
                .appComponent(getAppComponent())
                .presenterModule(new PresenterModule(this))
                .build()
                .inject(this);
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
            mPresenter.getRandomUsers(internetConnectionAvailable());
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
