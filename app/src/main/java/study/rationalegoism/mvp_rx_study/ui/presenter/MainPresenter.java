package study.rationalegoism.mvp_rx_study.ui.presenter;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import study.rationalegoism.mvp_rx_study.data.database.RandomUsersDao;
import study.rationalegoism.mvp_rx_study.data.network.RandomUsersServiceFactory;
import study.rationalegoism.mvp_rx_study.data.repository.RandomUsersRepository;
import study.rationalegoism.mvp_rx_study.data.repository.local.RandomUsersStoreLocal;
import study.rationalegoism.mvp_rx_study.data.repository.remote.RandomUsersStoreRemote;
import study.rationalegoism.mvp_rx_study.ui.MainContract;

public class MainPresenter implements MainContract.Presenter {
    private final MainContract.View mView;
    private final RandomUsersRepository repository;

    //collect our active subscribes
    private CompositeDisposable disposeBag;

    public MainPresenter(MainContract.View mView, RandomUsersDao randomUsersDao) {
        this.mView = mView;
        repository = new RandomUsersRepository(
                new RandomUsersStoreLocal(randomUsersDao),
                new RandomUsersStoreRemote(RandomUsersServiceFactory.makeRandomUsersService()));

        disposeBag = new CompositeDisposable();
    }

    @Override
    public void getRandomUsers(boolean refreshRequired) {
        loadRandomUsers(refreshRequired);
    }

    private void loadRandomUsers(boolean refreshRequired){
        Disposable disposable = repository.loadPersons(refreshRequired)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(mView::displayRandomUsers);
        disposeBag.add(disposable);
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
