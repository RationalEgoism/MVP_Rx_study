package study.rationalegoism.mvp_rx_study;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Component;
import study.rationalegoism.mvp_rx_study.data.repository.RandomUsersRepository;
import study.rationalegoism.mvp_rx_study.data.repository.RepositoryModule;

@Component(modules = {AppModule.class, RepositoryModule.class})
@Singleton
public interface AppComponent {
    Context context();
    RandomUsersRepository randomUsersRepository();
}
