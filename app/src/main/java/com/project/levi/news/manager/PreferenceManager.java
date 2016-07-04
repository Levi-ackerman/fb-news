package com.project.levi.news.manager;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Levi on 7/3/2016.
 */

public class PreferenceManager {

    private static final String APP_PRE = "app-news";
    private static final String KEY_PREF_TOKEN = "token";

    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;

    public PreferenceManager(Context context) {
        mSharedPreferences = context.getSharedPreferences(APP_PRE, Context.MODE_PRIVATE);
        mEditor = mSharedPreferences.edit();
    }

    public void saveToken(String token){
        mEditor.putString(KEY_PREF_TOKEN, token).commit();
    }

    public String getToken(){
        return mSharedPreferences.getString(KEY_PREF_TOKEN, null);
    }
}
