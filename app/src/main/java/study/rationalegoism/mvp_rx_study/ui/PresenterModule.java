package study.rationalegoism.mvp_rx_study.ui;

import dagger.Module;
import dagger.Provides;
import io.reactivex.Scheduler;
import study.rationalegoism.mvp_rx_study.data.repository.RandomUsersRepository;
import study.rationalegoism.mvp_rx_study.ui.presenter.MainPresenter;
import study.rationalegoism.mvp_rx_study.util.RunOn;
import study.rationalegoism.mvp_rx_study.util.SchedulerModule;

import static study.rationalegoism.mvp_rx_study.util.SchedulerType.IO;
import static study.rationalegoism.mvp_rx_study.util.SchedulerType.UI;

@Module(includes = SchedulerModule.class)
public class PresenterModule {
    MainContract.View mainActivity;

    public PresenterModule(MainContract.View mainActivity) {
        this.mainActivity = mainActivity;
    }

    @Provides
    public MainContract.Presenter provideMainPresenter(MainContract.View view,
                                                       RandomUsersRepository repository,
                                                       @RunOn(IO) Scheduler io,
                                                       @RunOn(UI) Scheduler ui) {
        return new MainPresenter(view, repository, io, ui);
    }

    @Provides
    public MainContract.View provideView(){
        return mainActivity;
    }
}
