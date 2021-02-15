package com.example.androidprototype.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class TagList {

    @SerializedName("$id")
    @Expose
    private String id;

    @SerializedName("$values")
    @Expose
    private ArrayList<Tag> taglist;

    public TagList(String id, ArrayList<Tag> taglist) {
        this.id = id;
        this.taglist = taglist;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ArrayList<Tag> getTaglist() {
        return taglist;
    }

    public void setTaglist(ArrayList<Tag> taglist) {
        this.taglist = taglist;
    }
}
