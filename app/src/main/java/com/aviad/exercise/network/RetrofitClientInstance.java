package com.aviad.exercise.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClientInstance {

    private static Retrofit retrofit;
    private static RetrofitClientInstance instance;
    private static final String URL = "https://jsonplaceholder.typicode.com";

    private RetrofitClientInstance() {
        retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static RetrofitClientInstance initHelper() {
        if (instance == null)
            instance = new RetrofitClientInstance();
        return instance;
    }

    public static Retrofit getInstance() {
        if (retrofit == null)
            initHelper();
        return retrofit;
    }



}
