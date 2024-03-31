package com.aviad.exercise;

import android.app.Application;

import com.aviad.exercise.utils.MySharedPreferences;
import com.aviad.exercise.network.RetroClient;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        RetroClient.initHelper();
        MySharedPreferences.initHelper(this);
    }
}
