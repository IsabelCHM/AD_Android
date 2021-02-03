package com.example.androidprototype.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class booleanJson {
    @SerializedName("flag")
    @Expose
    private Boolean flag;

    public booleanJson() {
        super();
    }

    public booleanJson(boolean flag) {
        this.flag = flag;
    }

    public Boolean getFlag() {
        return flag;
    }

    public void setFlag(Boolean flag) {
        this.flag = flag;
    }
}
