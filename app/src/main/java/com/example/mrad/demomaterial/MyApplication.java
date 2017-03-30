package com.example.mrad.demomaterial;

import android.app.Application;
import android.content.Context;

/**
 * Created by MR.AD on 029-Mar-3-29-2017.
 */

public class MyApplication extends Application {

private static MyApplication sInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        sInstance=this;
    }

    public static MyApplication getsInstance()
    {
        return sInstance;
    }

    public static Context getAppContext()
    {
        return sInstance.getApplicationContext();
    }
}
