package study.rationalegoism.mvp_rx_study.ui.presenter;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.OnLifecycleEvent;

import java.util.List;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import study.rationalegoism.mvp_rx_study.data.database.RandomUsersDao;
import study.rationalegoism.mvp_rx_study.data.model.Person;
import study.rationalegoism.mvp_rx_study.data.api.RandomUsersServiceFactory;
import study.rationalegoism.mvp_rx_study.data.repository.RandomUsersRepository;
import study.rationalegoism.mvp_rx_study.data.repository.local.RandomUsersStoreLocal;
import study.rationalegoism.mvp_rx_study.data.repository.remote.RandomUsersStoreRemote;
import study.rationalegoism.mvp_rx_study.ui.MainContract;

public class MainPresenter implements MainContract.Presenter, LifecycleObserver {
    private final MainContract.View mView;
    private final RandomUsersRepository repository;
    private final Scheduler ioScheduler;
    private final Scheduler uiScheduler;

    //collect our active subscribes
    private CompositeDisposable disposeBag;

    public MainPresenter(MainContract.View mView, RandomUsersRepository repository, Scheduler ioScheduler, Scheduler uiScheduler) {
        this.mView = mView;
        this.repository = repository;
        this.ioScheduler = ioScheduler;
        this.uiScheduler = uiScheduler;
        disposeBag = new CompositeDisposable();

        //Observe mView lifecycle
        if(mView instanceof LifecycleOwner){
            ((LifecycleOwner) mView).getLifecycle().addObserver(this);
        }
    }

    @Override
    public void getRandomUsers(boolean refreshRequired){
        mView.clearRandomUsers();

        Disposable disposable = repository.loadPersons(refreshRequired)
                .subscribeOn(ioScheduler)
                .observeOn(uiScheduler)
                .subscribe(this::handleReturnedData, this::handleErrorMessage, mView::stopLoadingIndicator);
        disposeBag.add(disposable);
    }

    private void handleReturnedData(List<Person> personList){
        mView.stopLoadingIndicator();
        if(personList != null && !personList.isEmpty())
            mView.displayRandomUsers(personList);
        else{
            mView.showNoDataMessage();
        }
    }

    private void handleErrorMessage(Throwable error){
        mView.stopLoadingIndicator();
        mView.showErrorMessage(error.getLocalizedMessage());
    }

    @Override @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    public void onAttach() {
        getRandomUsers(false);
    }

    @Override @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    public void onDetach() {
        //Clean up unnecessary resources
        disposeBag.clear();
    }
}
