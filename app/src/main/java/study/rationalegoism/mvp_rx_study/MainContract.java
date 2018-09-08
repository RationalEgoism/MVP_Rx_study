package study.rationalegoism.mvp_rx_study;

import java.util.List;

import retrofit2.Call;
import study.rationalegoism.mvp_rx_study.domain.entity.RandomUsers;
import study.rationalegoism.mvp_rx_study.domain.entity.Result;

public interface MainContract {
    interface Presenter{
        void getRandomUsers();
    }

    interface View{
        void displayRandomUsers(List<Result> resultList);
        void getRandomUsersList();
    }

    interface Model{
        Call<RandomUsers> getRandomUsersCall();
        void saveRandomUsersToDB(RandomUsers randomUsers);
    }
}
