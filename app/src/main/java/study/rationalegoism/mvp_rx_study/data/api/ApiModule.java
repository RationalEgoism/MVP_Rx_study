package study.rationalegoism.mvp_rx_study.data.api;

import android.content.Context;

import java.io.File;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import study.rationalegoism.mvp_rx_study.data.database.Config;
import timber.log.Timber;


//TODO NEED CONTEXT
@Module
public class ApiModule {

    @Provides
    @Singleton
    public RandomUsersService provideRandomUsersService(OkHttpClient client){
        return new Retrofit.Builder()
                .client(client)
                .baseUrl(Config.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
                .create(RandomUsersService.class);
    }

    @Provides
    @Singleton
    public OkHttpClient provideOkHttpClient(Cache cacheFile,
                                            HttpLoggingInterceptor httpLoggingInterceptor){
        return new OkHttpClient().newBuilder()
                .cache(cacheFile)
                .addInterceptor(httpLoggingInterceptor)
                .build();
    }

    @Provides
    @Singleton
    public Cache provideCacheFile(Context context){
        File cacheFile = new File(context.getCacheDir(), Config.HTTP_CACHE_DIR);
        return new Cache(cacheFile, Config.HTTP_MAX_CACHE_SIZE);
    }

    @Provides
    @Singleton
    public HttpLoggingInterceptor provideHttpLoggingInterceptor(){
        //Logs request and response lines and their respective headers and bodies
        return new HttpLoggingInterceptor(message -> Timber.i(message))
                .setLevel(HttpLoggingInterceptor.Level.BODY);
    }

}
