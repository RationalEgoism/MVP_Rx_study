package study.rationalegoism.mvp_rx_study.util;

import dagger.Module;
import dagger.Provides;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

import static study.rationalegoism.mvp_rx_study.util.SchedulerType.IO;
import static study.rationalegoism.mvp_rx_study.util.SchedulerType.UI;

@Module
public class SchedulerModule {

    @RunOn(IO)
    @Provides
    public Scheduler provideIoScheduler(){
        return Schedulers.io();
    }

    @RunOn(UI)
    public Scheduler provideUiScheduler(){
        return AndroidSchedulers.mainThread();
    }
}
