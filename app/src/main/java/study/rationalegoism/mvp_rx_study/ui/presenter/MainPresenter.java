package study.rationalegoism.mvp_rx_study.ui.presenter;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.internal.schedulers.IoScheduler;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import study.rationalegoism.mvp_rx_study.data.database.RandomUsersDao;
import study.rationalegoism.mvp_rx_study.data.model.Person;
import study.rationalegoism.mvp_rx_study.data.model.RandomUsers;
import study.rationalegoism.mvp_rx_study.data.network.RandomUsersService;
import study.rationalegoism.mvp_rx_study.data.network.RandomUsersServiceFactory;
import study.rationalegoism.mvp_rx_study.data.repository.RandomUsersRepository;
import study.rationalegoism.mvp_rx_study.data.repository.local.RandomUsersStoreLocal;
import study.rationalegoism.mvp_rx_study.data.repository.remote.RandomUsersStoreRemote;
import study.rationalegoism.mvp_rx_study.ui.MainContract;
import study.rationalegoism.mvp_rx_study.ui.presenter.asyncTasks.delete.ClearRandomUsersAsync;
import study.rationalegoism.mvp_rx_study.ui.presenter.asyncTasks.insert.InsertRandomUsersAsync;

public class MainPresenter implements MainContract.Presenter {
    private final MainContract.View mView;
    private final RandomUsersDao randomUsersDao;
    private final RandomUsersService mRandomUsersService;
    private final RandomUsersRepository repository;

    //collect our active subscribes
    private CompositeDisposable disposeBag;

    public MainPresenter(MainContract.View mView, RandomUsersDao randomUsersDao) {
        this.mView = mView;
        this.randomUsersDao = randomUsersDao;
        mRandomUsersService = RandomUsersServiceFactory.makeRandomUsersService();
        repository = new RandomUsersRepository(
                new RandomUsersStoreLocal(randomUsersDao),
                new RandomUsersStoreRemote(RandomUsersServiceFactory.makeRandomUsersService()));

        disposeBag = new CompositeDisposable();
    }

    @Override
    public void getRandomUsers(boolean refreshRequired) {
        if(refreshRequired) {
            getRandomUsersOnline();
        }
        else {
            loadRandomUsers();
        }
    }

    private void getRandomUsersOnline() {
        getRandomUsersList(new Callback<RandomUsers>() {
            @Override
            public void onResponse(Call<RandomUsers> call, Response<RandomUsers> response) {
                insertDataToStore(response.body().getPersons());
                loadRandomUsers();
                mView.stopLoadingIndicator();
            }

            @Override
            public void onFailure(Call<RandomUsers> call, Throwable t) {
                //TODO
            }
        });
    }

    private void getRandomUsersList(Callback<RandomUsers> randomUsersCallback) {
        mRandomUsersService.getRandomUsers(10, "gb")
                .enqueue(randomUsersCallback);
    }

    private void loadRandomUsers(boolean refreshRequired){
        Disposable disposable = repository.loadPersons(refreshRequired)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(mView::displayRandomUsers);

        disposeBag.add(disposable);
    }

    private void insertDataToStore(List<Person> personList){
        final List<Person> personListCopy = new ArrayList<>(personList);
        ClearRandomUsersAsync clearRandomUsersAsync = new ClearRandomUsersAsync(() -> {});
        clearRandomUsersAsync.execute(randomUsersDao);
        for (Person person : personListCopy) {
            InsertRandomUsersAsync task = new InsertRandomUsersAsync(person);
            task.execute(randomUsersDao);
        }
    }

    @Override
    public void onAttach() {
        getRandomUsers(false);
    }

    @Override
    public void onDetach() {
        //TODO
    }
}
