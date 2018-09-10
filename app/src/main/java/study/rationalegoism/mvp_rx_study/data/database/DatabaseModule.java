package study.rationalegoism.mvp_rx_study.data.database;

import android.arch.persistence.room.Room;
import android.content.Context;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import study.rationalegoism.mvp_rx_study.AppModule;

//AppModule provides Context
@Module(includes = AppModule.class)
public class DatabaseModule {
    @Provides
    @Singleton
    public RandomUsersDao provideRandomUsersDao(Context context){
        return Room.databaseBuilder(context,
                RandomUsersDb.class,
                Config.DATABASE_NAME)
                .build()
                .randomUsersDao();
    }
}
