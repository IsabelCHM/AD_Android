package com.example.androidprototype.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserGroup {

    @SerializedName("groupId")
    @Expose
    private int groupId;

    @SerializedName("userId")
    @Expose
    private int userId;

    @SerializedName("isMod")
    @Expose
    private boolean isMod;

    @SerializedName("group")
    @Expose
    private Group group;

    @SerializedName("user")
    @Expose
    private User user;

    public UserGroup() {
        super();
    }

    public UserGroup(boolean isMod) {
        this.isMod = isMod;
    }

    public UserGroup(boolean isMod, Group group, User user) {
        this.isMod = isMod;
        this.group = group;
        this.user = user;
    }

    public UserGroup(int groupId, int userId, boolean isMod) {
        this.groupId = groupId;
        this.userId = userId;
        this.isMod = isMod;
    }

    public boolean isMod() {
        return isMod;
    }

    public void setMod(boolean mod) {
        isMod = mod;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
