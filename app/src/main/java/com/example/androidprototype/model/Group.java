package com.example.androidprototype.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class Group {

    @SerializedName("groupName")
    @Expose
    private String groupName;

    @SerializedName("groupPhoto")
    @Expose
    private String groupPhoto;

    @SerializedName("description")
    @Expose
    private String description;

    @SerializedName("dateCreated")
    private Date dateCreated;

    @SerializedName("isPublished")
    @Expose
    private boolean isPublished;

    public Group() {
        super();
    }

    public Group(String groupName, String groupPhoto, String description, Date dateCreated, boolean isPublished) {
        this.groupName = groupName;
        this.groupPhoto = groupPhoto;
        this.description = description;
        this.dateCreated = dateCreated;
        this.isPublished = isPublished;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getGroupPhoto() {
        return groupPhoto;
    }

    public void setGroupPhoto(String groupPhoto) {
        this.groupPhoto = groupPhoto;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public boolean isPublished() {
        return isPublished;
    }

    public void setPublished(boolean published) {
        isPublished = published;
    }
}
