package study.rationalegoism.mvp_rx_study.util;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Qualifier;

@Qualifier
@Retention(RetentionPolicy.RUNTIME)
public @interface RunOn {
    SchedulerType value();
}
