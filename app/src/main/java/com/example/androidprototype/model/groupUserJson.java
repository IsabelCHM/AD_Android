package com.example.androidprototype.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class groupUserJson {

    @SerializedName("user")
    @Expose
    private User user;

    @SerializedName("group")
    @Expose
    private Group group;

    @SerializedName("tags")
    @Expose
    private String tags;

    public groupUserJson(User user, Group group, String tags) {
        this.user = user;
        this.group = group;
        this.tags = tags;
    }
}
