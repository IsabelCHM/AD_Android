package com.example.androidprototype.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class GroupList {

    @SerializedName("$id")
    @Expose
    private String id;

    @SerializedName("$values")
    @Expose
    private ArrayList<Group> grouplist;

    public GroupList(String id, ArrayList<Group> grouplist) {
        this.id = id;
        this.grouplist = grouplist;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ArrayList<Group> getGrouplist() {
        return grouplist;
    }

    public void setGrouplist(ArrayList<Group> grouplist) {
        this.grouplist = grouplist;
    }
}
