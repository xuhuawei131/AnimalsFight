package com.xuhuawei.animalsfight;

import android.app.Application;
import android.content.Context;

public class BaseApp extends Application {
    public static Context context;
    @Override
    public void onCreate() {
        super.onCreate();
        context=this.getApplicationContext();
    }

    public static Context getAppContext() {
        return context;
    }
}
