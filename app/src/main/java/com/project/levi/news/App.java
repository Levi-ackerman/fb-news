package com.project.levi.news;

import android.app.Application;
import android.text.TextUtils;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.facebook.FacebookSdk;
import com.project.levi.news.manager.PreferenceManager;

/**
 * Created by Levi on 7/3/2016.
 */

public class App extends Application {
    private static final String TAG = "App";

    private static App mInstance;
    private PreferenceManager mPreferenceManager;
    private RequestQueue mRequestQueue;

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

    public RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
            mRequestQueue = Volley.newRequestQueue(getApplicationContext());
        }

        return mRequestQueue;
    }

    public <T> void addToRequestQueue(Request<T> req, String tag) {
        req.setTag(TextUtils.isEmpty(tag) ? TAG : tag);
        getRequestQueue().add(req);
    }

    public <T> void addToRequestQueue(Request<T> req) {
        req.setTag(TAG);
        getRequestQueue().add(req);
    }

    public void cancelPendingRequests(Object tag) {
        if (mRequestQueue != null) {
            mRequestQueue.cancelAll(tag);
        }
    }
}
