package study.rationalegoism.mvp_rx_study;

import android.content.Context;

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

    //package-level just for MainActivity
    void init(Context context){
        this.context = context.getApplicationContext();
    }

    public Context getContext(){
        return context;
    }
}
