package study.rationalegoism.mvp_rx_study.model.network;

import java.io.File;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import study.rationalegoism.mvp_rx_study.AppContextSingleton;
import timber.log.Timber;

public class RandomUsersServiceFactory {
    private static final String BASE_URL = "https://randomuser.me/";

    public static RandomUsersService makeRandomUsersService(){
        return makeRandomUsersService(makeOkHttpClient());
    }

    private static RandomUsersService makeRandomUsersService(OkHttpClient okHttpClient){
        return new Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(RandomUsersService.class);
    }

    private static OkHttpClient makeOkHttpClient() {
        Cache cache = getCacheFile();
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                Timber.i(message);
            }
        });
        return new OkHttpClient().newBuilder()
                .cache(cache)
                .addInterceptor(httpLoggingInterceptor)
                .build();
    }

    private static Cache getCacheFile(){
        File cacheFile = new File(AppContextSingleton.getInstance().getContext().getCacheDir(), "HttpCache");
        return new Cache(cacheFile, 10 * 1024 * 1024);
    }
}
