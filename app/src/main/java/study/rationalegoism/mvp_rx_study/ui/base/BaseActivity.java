package study.rationalegoism.mvp_rx_study.ui.base;

import android.support.v7.app.AppCompatActivity;

import study.rationalegoism.mvp_rx_study.App;
import study.rationalegoism.mvp_rx_study.AppComponent;

public class BaseActivity extends AppCompatActivity {

    protected AppComponent getAppComponent(){
        return ((App) getApplication()).getAppComponent();
    }
}
