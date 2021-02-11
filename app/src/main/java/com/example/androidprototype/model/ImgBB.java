package com.example.androidprototype.model;

import com.google.gson.annotations.SerializedName;

public class ImgBB {
    @SerializedName("data")
    private ImgBBData imgBBData;

    public ImgBB() {
        super();
    }

    public ImgBBData getImgBBData() {
        return imgBBData;
    }

    public void setImgBBData(ImgBBData imgBBData) {
        this.imgBBData = imgBBData;
    }
}
