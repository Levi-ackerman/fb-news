package com.project.levi.news;

import android.app.Application;

import com.facebook.FacebookSdk;
import com.project.levi.news.manager.PreferenceManager;

/**
 * Created by Levi on 7/3/2016.
 */

public class App extends Application {
    private static App mInstance;
    private PreferenceManager mPreferenceManager;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        FacebookSdk.sdkInitialize(getApplicationContext());
    }

    public static App getInstance(){
        return mInstance;
    }

    public PreferenceManager getPreferenceManager(){
        if(mPreferenceManager == null){
            mPreferenceManager = new PreferenceManager(getBaseContext());
        }
        return mPreferenceManager;
    }
}
