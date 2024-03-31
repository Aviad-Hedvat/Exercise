package com.aviad.exercise.network;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface PhotosAPI {

    @GET("/photos")
    Call<List<RetroPhoto>> getAllPhotos();

    @GET("/photos")
    Call<List<RetroPhoto>> getPhoto(@Query("id") String id);
}
