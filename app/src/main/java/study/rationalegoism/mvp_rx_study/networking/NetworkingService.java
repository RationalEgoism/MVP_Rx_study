package study.rationalegoism.mvp_rx_study.networking;

import java.io.File;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import study.rationalegoism.mvp_rx_study.domain.entity.RandomUsers;

public class NetworkingService implements NetworkingServiceApi {
    @Override
    public Call<RandomUsers> getRandomUsers() {
        return null;
    }
}
