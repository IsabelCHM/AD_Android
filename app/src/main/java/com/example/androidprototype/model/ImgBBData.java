package com.example.androidprototype.model;

import com.google.gson.annotations.SerializedName;

public class ImgBBData {
    @SerializedName("url")
    private String url;

    public ImgBBData() {
        super();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
