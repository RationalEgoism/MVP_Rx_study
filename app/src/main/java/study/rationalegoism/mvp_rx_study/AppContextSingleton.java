package study.rationalegoism.mvp_rx_study;

import android.content.Context;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import timber.log.Timber;

public class AppContextSingleton{
    private static AppContextSingleton instance = null;
    private Context context;


    private AppContextSingleton() { }

    public static AppContextSingleton getInstance(){
        if(instance == null){
            instance = new AppContextSingleton();
        }
        return instance;
    }

    public void init(Context context){
        this.context = context.getApplicationContext();
    }

    //get Application context
    public Context getContext(){
        return context;
    }

    //Check if we have internet
    public boolean internetConnectionAvailable() {
        int timeOut = 200;
        InetAddress inetAddress = null;
        try {
            Future<InetAddress> future = Executors.newSingleThreadExecutor().submit(() -> {
                try {
                    return InetAddress.getByName("google.com");
                } catch (UnknownHostException e) {
                    return null;
                }
            });
            inetAddress = future.get(timeOut, TimeUnit.MILLISECONDS);
            future.cancel(true);
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            Timber.i(e.getMessage());
        }
        return inetAddress!=null && !inetAddress.equals("");
    }
}
