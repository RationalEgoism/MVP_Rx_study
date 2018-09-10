package study.rationalegoism.mvp_rx_study;

import android.app.Application;

import timber.log.Timber;

public class App extends Application{

    private AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        initializeDependencies();
        Timber.plant(new Timber.DebugTree());
    }

    private void initializeDependencies() {
        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }
}
