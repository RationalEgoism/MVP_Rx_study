package study.rationalegoism.mvp_rx_study.ui;

import dagger.Component;
import study.rationalegoism.mvp_rx_study.util.SchedulerModule;

@Component(modules = {SchedulerModule.class})
public interface MainContractComponent {
    void inject(MainActivity mainActivity);
}