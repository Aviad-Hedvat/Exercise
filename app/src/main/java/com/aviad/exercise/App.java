package com.aviad.exercise;

import android.app.Application;

import com.aviad.exercise.utils.MySharedPreferences;
import com.aviad.exercise.network.RetrofitClientInstance;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        RetrofitClientInstance.initHelper();
        MySharedPreferences.initHelper(this);
    }
}
