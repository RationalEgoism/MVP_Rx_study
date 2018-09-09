package study.rationalegoism.mvp_rx_study;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import study.rationalegoism.mvp_rx_study.model.domain.entity.RandomUsers;
import study.rationalegoism.mvp_rx_study.model.domain.entity.Result;

public interface MainContract {
    interface Presenter{
        void getRandomUsers();
    }

    interface View{
        void displayRandomUsers(List<Result> resultList);
        void getRandomUsersListFromActivity();
    }

    interface Model{
        void getRandomUsersList(Callback<RandomUsers> randomUsersCallback);
        void saveRandomUsersToDB(RandomUsers randomUsers);
    }
}
