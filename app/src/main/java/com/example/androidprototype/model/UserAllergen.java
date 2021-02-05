package com.example.androidprototype.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserAllergen {
    @SerializedName("$id")
    @Expose
    private String id;

    @SerializedName("userId")
    @Expose
    private String userId;

    @SerializedName("tagId")
    @Expose
    private String tagId;

    @SerializedName("tag")
    @Expose
    private Tag tag;

    @SerializedName("user")
    @Expose
    private User user;

    public UserAllergen() {
        super();
    }

    public UserAllergen(String id, String userid, String tagId, Tag tag, User user) {
        this.id = id;
        this.userId = userid;
        this.tagId = tagId;
        this.tag = tag;
        this.user = user;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTagId() {
        return tagId;
    }

    public String getUserId() {
        return userId;
    }

    public Tag getTag() {
        return tag;
    }

    public User getUser() {
        return user;
    }

    public void setTag(Tag tag) {
        this.tag = tag;
    }

    public void setTagId(String tagId) {
        this.tagId = tagId;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
