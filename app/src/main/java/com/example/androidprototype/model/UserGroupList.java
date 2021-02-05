package com.example.androidprototype.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class UserGroupList {

    @SerializedName("$id")
    @Expose
    private String id;

    @SerializedName("$values")
    @Expose
    private ArrayList<UserGroup> usergrouplist;

    public UserGroupList(String id, ArrayList<UserGroup> usergrouplist) {
        this.id = id;
        this.usergrouplist = usergrouplist;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ArrayList<UserGroup> getUsergrouplist() {
        return usergrouplist;
    }

    public void setUsergrouplist(ArrayList<UserGroup> usergrouplist) {
        this.usergrouplist = usergrouplist;
    }
}
