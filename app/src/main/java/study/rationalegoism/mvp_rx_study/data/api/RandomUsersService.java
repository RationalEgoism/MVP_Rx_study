package study.rationalegoism.mvp_rx_study.data.api;

import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RandomUsersService {

    @GET("api")
    Flowable<RandomUsers> getRandomUsers(@Query("results") int size, @Query("nat") String nationality);
}
