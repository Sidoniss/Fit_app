package com.example.fit_app_bachelor.auth.Service;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.fit_app_bachelor.MyApp;

public class PreferencesHelper {

    private static final String PREFERENCES_FILE = "com.myapp.preferences";
    private static final String IS_LOGGED_IN = "is_logged_in";

    private static PreferencesHelper instance;
    private SharedPreferences sharedPreferences;

    private PreferencesHelper() {
        sharedPreferences = MyApp.getInstance().getSharedPreferences(PREFERENCES_FILE, Context.MODE_PRIVATE);
    }

    public static synchronized PreferencesHelper getInstance() {
        if (instance == null) {
            instance = new PreferencesHelper();
        }
        return instance;
    }

    public void setLoggedIn(boolean loggedIn) {
        sharedPreferences.edit().putBoolean(IS_LOGGED_IN, loggedIn).apply();
    }

    public boolean isLoggedIn() {
        return sharedPreferences.getBoolean(IS_LOGGED_IN, false);
    }
}
