package study.rationalegoism.mvp_rx_study;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import study.rationalegoism.mvp_rx_study.presenter.MainPresenter;
import study.rationalegoism.mvp_rx_study.presenter.contract.MainContract;

public class MainActivity extends AppCompatActivity implements MainContract.View {
    private MainContract.Presenter mPresenter = new MainPresenter(this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
