package study.rationalegoism.mvp_rx_study.networking;

import retrofit2.Call;
import study.rationalegoism.mvp_rx_study.domain.entity.RandomUsers;

public interface NetworkingServiceApi {
    Call<RandomUsers> getRandomUsers();
}
