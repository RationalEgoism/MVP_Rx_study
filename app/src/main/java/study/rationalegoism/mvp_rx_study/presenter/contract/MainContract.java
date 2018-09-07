package study.rationalegoism.mvp_rx_study.presenter.contract;

import java.util.List;

import study.rationalegoism.mvp_rx_study.domain.entity.Result;

public interface MainContract {
    interface Presenter{
        void getRandomUsers();
    }

    interface View{
        void displayRandomUsers(List<Result> resultList);
        void getRandomUsersList();
    }
}
