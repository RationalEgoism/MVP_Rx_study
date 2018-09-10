package study.rationalegoism.mvp_rx_study.data.repository;

import javax.inject.Singleton;
import dagger.Module;
import dagger.Provides;
import study.rationalegoism.mvp_rx_study.data.api.ApiModule;
import study.rationalegoism.mvp_rx_study.data.api.RandomUsersService;
import study.rationalegoism.mvp_rx_study.data.database.DatabaseModule;
import study.rationalegoism.mvp_rx_study.data.database.RandomUsersDao;
import study.rationalegoism.mvp_rx_study.data.repository.local.Local;
import study.rationalegoism.mvp_rx_study.data.repository.local.RandomUsersSourceLocal;
import study.rationalegoism.mvp_rx_study.data.repository.remote.RandomUsersSourceRemote;
import study.rationalegoism.mvp_rx_study.data.repository.remote.Remote;

//DatabaseModule provides RandomUsersDao, ApiModule provides RandomUsersService
@Module(includes = {DatabaseModule.class, ApiModule.class})
public class RepositoryModule {
    @Provides
    @Singleton
    public RandomUsersRepository provideRandomUsersRepository(@Local RandomUsersSource local,
                                                             @Remote RandomUsersSource remote){
        return new RandomUsersRepository(local, remote);
    }

    @Provides
    @Singleton
    @Local
    public RandomUsersSource provideRandomUsersSourceLocal(RandomUsersDao randomUsersDao){
        return new RandomUsersSourceLocal(randomUsersDao);
    }

    @Provides
    @Singleton
    @Remote
    public RandomUsersSource provideRandomUsersSourceRemote(RandomUsersService randomUsersService){
        return new RandomUsersSourceRemote(randomUsersService);
    }
}
