package com.aviad.exercise.database;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClientInstance {

    private static Retrofit retrofit;
    private static final String URL = "https://jsonplaceholder.typicode.com";

    public static Retrofit getInstance() {
        if (retrofit == null)
            retrofit = new Retrofit.Builder()
                    .baseUrl(URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

        return retrofit;
    }



}
