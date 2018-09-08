package study.rationalegoism.mvp_rx_study.model;

import android.support.annotation.NonNull;

import com.google.gson.Gson;

import java.util.List;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import study.rationalegoism.mvp_rx_study.domain.entity.RandomUsers;
import study.rationalegoism.mvp_rx_study.domain.entity.Result;
import study.rationalegoism.mvp_rx_study.model.RetrofitRequest.RandomUsersRequest;
import study.rationalegoism.mvp_rx_study.MainContract;
import timber.log.Timber;

public class MainModel implements MainContract.Model {

    private MainContract.Presenter mPresenter;
    private Cache cacheFile;
    private String randomUsersBaseUrl;

    public MainModel(MainContract.Presenter mPresenter, Cache cacheFile, String randomUsersBaseUrl) {
        this.mPresenter = mPresenter;
        this.cacheFile = cacheFile;
        this.randomUsersBaseUrl = randomUsersBaseUrl;
    }

    @Override
    public Call<RandomUsers> getRandomUsersCall() {
        Retrofit retrofit = getRetrofit();
        RandomUsersRequest request = retrofit.create(RandomUsersRequest.class);
        return request.getRandomUsers(10, "gb");
    }

    @Override
    public void saveRandomUsersToDB(RandomUsers randomUsers) {
        saveRandomUsersImagesToStorage(randomUsers.getImagesList());
    }

    private void saveRandomUsersImagesToStorage(List<String> imagesList) {

    }

    @NonNull
    private Retrofit getRetrofit() {
        OkHttpClient okHttpClient = getOkHttpClient();
        GsonConverterFactory gsonConverterFactory = getGsonConverterFactory();

        return new Retrofit.Builder()
                .client(okHttpClient)
                .addConverterFactory(gsonConverterFactory)
                .baseUrl(randomUsersBaseUrl)
                .build();
    }

    @NonNull
    private OkHttpClient getOkHttpClient() {
        Cache cache = cacheFile;
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

    @NonNull
    private GsonConverterFactory getGsonConverterFactory() {
        Gson gson = new Gson();
        return GsonConverterFactory.create(gson);
    }
}
