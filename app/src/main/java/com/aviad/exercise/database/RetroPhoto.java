package com.aviad.exercise.database;

import com.google.gson.annotations.SerializedName;

public class RetroPhoto {

    @SerializedName("albumId")
    private Integer albumId;

    @SerializedName("id")
    private Integer id;

    @SerializedName("title")
    private String title;

    @SerializedName("url")
    private String url;

    @SerializedName("thumbnaiUrl")
    private String thumbnaiUrl;

    public RetroPhoto(Integer albumId, Integer id, String title, String url, String thumbnaiUrl) {
        this.albumId = albumId;
        this.id = id;
        this.title = title;
        this.url = url;
        this.thumbnaiUrl = thumbnaiUrl;
    }

    public Integer getAlbumId() {
        return albumId;
    }

    public RetroPhoto setAlbumId(Integer albumId) {
        this.albumId = albumId;
        return this;
    }

    public Integer getId() {
        return id;
    }

    public RetroPhoto setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public RetroPhoto setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getUrl() {
        return url;
    }

    public RetroPhoto setUrl(String url) {
        this.url = url;
        return this;
    }

    public String getThumbnaiUrl() {
        return thumbnaiUrl;
    }

    public RetroPhoto setThumbnaiUrl(String thumbnaiUrl) {
        this.thumbnaiUrl = thumbnaiUrl;
        return this;
    }
}
