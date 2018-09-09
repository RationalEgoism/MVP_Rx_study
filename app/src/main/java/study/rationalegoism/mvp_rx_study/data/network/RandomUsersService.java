package study.rationalegoism.mvp_rx_study.data.network;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import study.rationalegoism.mvp_rx_study.data.model.RandomUsers;

public interface RandomUsersService {

    @GET("api")
    Call<RandomUsers> getRandomUsers(@Query("results") int size, @Query("nat") String nationality);
}
