package study.rationalegoism.mvp_rx_study.model;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.FutureTarget;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.target.Target;
import com.bumptech.glide.request.transition.Transition;
import com.google.gson.Gson;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import study.rationalegoism.mvp_rx_study.AppContextSingleton;
import study.rationalegoism.mvp_rx_study.DownloadImageAsyncTask;
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
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        for(String imageUrl: imagesList){
                    FutureTarget<File> futureTarget =
                            Glide.with(AppContextSingleton.getInstance().getContext())
                            .load(imageUrl)
                            .downloadOnly(100, 100);
                new DownloadImageAsyncTask().execute(futureTarget);

        }
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
