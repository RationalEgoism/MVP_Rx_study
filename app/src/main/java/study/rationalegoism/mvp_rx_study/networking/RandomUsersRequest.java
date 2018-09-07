package study.rationalegoism.mvp_rx_study.networking;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import study.rationalegoism.mvp_rx_study.domain.entity.RandomUsers;

public interface RandomUsersRequest {

    @GET("api")
    Call<RandomUsers> getRandomUsers(@Query("results") int size);
}
