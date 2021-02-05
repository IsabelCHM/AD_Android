package com.example.androidprototype.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GroupTag {

    @SerializedName("group")
    @Expose
    private Group group;

    @SerializedName("tag")
    @Expose
    private Tag tag;

    public GroupTag() {
        super();
    }

    public GroupTag(Group group, Tag tag) {
        this.group = group;
        this.tag = tag;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public Tag getTag() {
        return tag;
    }

    public void setTag(Tag tag) {
        this.tag = tag;
    }
}
