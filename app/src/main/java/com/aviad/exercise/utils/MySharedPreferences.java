package com.aviad.exercise.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.internal.Primitives;

public class MySharedPreferences {
    private final String NAME = "MY_SP";
    private SharedPreferences prefs;
    private SharedPreferences.Editor editor;
    private static MySharedPreferences instance;
    private Gson gson;


    private MySharedPreferences(Context context) {
        prefs = context.getSharedPreferences(NAME, Context.MODE_PRIVATE);
        editor = prefs.edit();
        gson = new Gson();
    }

    public static MySharedPreferences initHelper(Context context) {
        if (instance == null)
            instance = new MySharedPreferences(context);
        return instance;
    }

    public static MySharedPreferences getInstance() {
        return instance;
    }

    public void putString(String key, String value) {
        editor.putString(key, value).apply();
    }

    public String getString(String key, String defVal) {
        return prefs.getString(key, defVal);
    }

    public void putObject(String key, Object value) {
        editor.putString(key, gson.toJson(value)).apply();
    }

    public <T> T getObject(String key, Class<T> defVal) {
        Object object = null;
        try {
            object = gson.fromJson(getString(key, ""), defVal);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return Primitives.wrap(defVal).cast(object);
    }

}
