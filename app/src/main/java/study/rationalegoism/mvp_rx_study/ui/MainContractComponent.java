package study.rationalegoism.mvp_rx_study.ui;

import dagger.Component;
import study.rationalegoism.mvp_rx_study.AppComponent;
import study.rationalegoism.mvp_rx_study.data.repository.RepositoryModule;
import study.rationalegoism.mvp_rx_study.ui.presenter.MainPresenter;
import study.rationalegoism.mvp_rx_study.util.SchedulerModule;

@ActivityScope
@Component(modules = {SchedulerModule.class, PresenterModule.class},
        dependencies = AppComponent.class)
public interface MainContractComponent {
    MainContract.Presenter providePresenter();
    void inject(MainActivity mainActivity);
}
