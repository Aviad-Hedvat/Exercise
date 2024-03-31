package com.aviad.exercise.network;

import androidx.annotation.NonNull;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetroClient {

    private PhotosAPI service;
    private static Retrofit retrofit;
    private static RetroClient instance;
    private static final String URL = "https://jsonplaceholder.typicode.com";

    private RetroClient() {
        retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        service = retrofit.create(PhotosAPI.class);
    }

    public static void initHelper() {
        if (instance == null)
            instance = new RetroClient();
    }

    public static RetroClient getInstance() {
        return instance;
    }

    public void getAllPhotos(PhotosCallback photosCallback) {
        Call<List<RetroPhoto>> call = service.getAllPhotos();
        call.enqueue(new Callback<List<RetroPhoto>>() {
            @Override
            public void onResponse(@NonNull Call<List<RetroPhoto>> call, @NonNull Response<List<RetroPhoto>> response) {
                if (photosCallback != null && response.isSuccessful() && response.body() != null)
                    photosCallback.onResponse(response.body());
            }

            @Override
            public void onFailure(@NonNull Call<List<RetroPhoto>> call, @NonNull Throwable t) {
                if (photosCallback != null)
                    photosCallback.onFailure(t.getMessage());
            }
        });

    }

    /* Create handle for the RetrofitInstance interface */
    public void getPhotoById(PhotosCallback photosCallback, String id) {
        Call<List<RetroPhoto>> call = service.getPhoto(id);
        call.enqueue(new Callback<List<RetroPhoto>>() {
            @Override
            public void onResponse(Call<List<RetroPhoto>> call, Response<List<RetroPhoto>> response) {
                if (photosCallback != null && response.isSuccessful() && response.body() != null)
                    photosCallback.onResponse(response.body());
            }

            @Override
            public void onFailure(Call<List<RetroPhoto>> call, Throwable t) {
                if (photosCallback != null)
                    photosCallback.onFailure(t.getMessage());
            }
        });
    }

    public interface PhotosCallback {
        void onResponse(List<RetroPhoto> photosList);
        void onFailure(String errorMsg);
    }

}
