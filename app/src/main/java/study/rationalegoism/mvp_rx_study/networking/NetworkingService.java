package study.rationalegoism.mvp_rx_study.networking;

import android.content.Context;

import com.google.gson.Gson;

import java.io.File;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import study.rationalegoism.mvp_rx_study.domain.entity.RandomUsers;
import timber.log.Timber;

public class NetworkingService implements NetworkingServiceApi {
    private Context context;

    public NetworkingService(Context context) {
        this.context = context;
    }

    @Override
    public Call<RandomUsers> getRandomUsers() {
        File cacheFile = new File(context.getCacheDir(), "HttpCache");
        Cache cache = new Cache(cacheFile, 10 * 1024 * 1024);
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                Timber.i(message);
            }
        });
        OkHttpClient okHttpClient = new OkHttpClient().newBuilder()
                .cache(cache)
                .addInterceptor(httpLoggingInterceptor)
                .build();

        Gson gson = new Gson();
        GsonConverterFactory gsonConverterFactory = GsonConverterFactory.create(gson);

        Retrofit retrofit = new Retrofit.Builder()
                .client(okHttpClient)
                .addConverterFactory(gsonConverterFactory)
                .baseUrl("http://randomuser.me/")
                .build();

        RandomUsersRequest request = retrofit.create(RandomUsersRequest.class);
        return request.getRandomUsers(10);
    }
}
