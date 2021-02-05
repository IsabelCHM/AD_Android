package com.example.androidprototype.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FollowUser {

    @SerializedName("followedUser")
    @Expose
    private User followedUser;

    @SerializedName("followingUser")
    @Expose
    private User followingUser;

    public FollowUser() {
        super();
    }

    public FollowUser(User followedUser, User followingUser) {
        this.followedUser = followedUser;
        this.followingUser = followingUser;
    }

    public User getFollowedUser() {
        return followedUser;
    }

    public void setFollowedUser(User followedUser) {
        this.followedUser = followedUser;
    }

    public User getFollowingUser() {
        return followingUser;
    }

    public void setFollowingUser(User followingUser) {
        this.followingUser = followingUser;
    }
}
