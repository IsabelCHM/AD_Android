package com.example.androidprototype.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class FollowUserList {

    @SerializedName("$id")
    @Expose
    private String id;

    @SerializedName("$values")
    @Expose
    private ArrayList<FollowUser> followuserlist;

    public FollowUserList(String id, ArrayList<FollowUser> followuserlist) {
        this.id = id;
        this.followuserlist = followuserlist;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ArrayList<FollowUser> getFollowuserlist() {
        return followuserlist;
    }

    public void setFollowuserlist(ArrayList<FollowUser> followuserlist) {
        this.followuserlist = followuserlist;
    }
}
