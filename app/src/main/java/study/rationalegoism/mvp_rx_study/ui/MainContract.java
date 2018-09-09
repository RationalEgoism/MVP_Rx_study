package study.rationalegoism.mvp_rx_study.ui;

import java.util.List;

import study.rationalegoism.mvp_rx_study.data.model.Person;
import study.rationalegoism.mvp_rx_study.ui.base.BasePresenter;

public interface MainContract {
    interface View{
        void displayRandomUsers(List<Person> personList);
        void stopLoadingIndicator();
    }

    interface Presenter extends BasePresenter{
        void getRandomUsers(boolean refreshRequired);
    }

}
