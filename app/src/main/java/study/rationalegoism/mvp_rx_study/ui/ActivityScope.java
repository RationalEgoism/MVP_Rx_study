package study.rationalegoism.mvp_rx_study.ui;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Qualifier;
import javax.inject.Scope;

@Scope
@Qualifier
@Retention(RetentionPolicy.RUNTIME)
public @interface ActivityScope {
}
