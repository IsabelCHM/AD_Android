package com.example.androidprototype.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class GroupTagList {

    @SerializedName("$id")
    @Expose
    private String id;

    @SerializedName("$values")
    @Expose
    private ArrayList<GroupTag> grouptaglist;

    public GroupTagList(String id, ArrayList<GroupTag> grouptaglist) {
        this.id = id;
        this.grouptaglist = grouptaglist;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ArrayList<GroupTag> getGrouptaglist() {
        return grouptaglist;
    }

    public void setGrouptaglist(ArrayList<GroupTag> grouptaglist) {
        this.grouptaglist = grouptaglist;
    }
}
