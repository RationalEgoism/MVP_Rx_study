package study.rationalegoism.mvp_rx_study.ui;

import java.util.List;

import study.rationalegoism.mvp_rx_study.data.model.Result;

public interface MainContract {
    interface Presenter{
        void getRandomUsers(boolean isOnline);
    }

    interface View{
        void displayRandomUsers(List<Result> resultList);
        void getRandomUsersListFromActivity();
    }
}
