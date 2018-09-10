package study.rationalegoism.mvp_rx_study.ui;

import dagger.Module;
import dagger.Provides;

@Module
public class PresenterModule {
    private MainContract.View view;

    public PresenterModule(MainContract.View view) {
        this.view = view;
    }

    @Provides
    public MainContract.View proideView(){
        return view;
    }
}
