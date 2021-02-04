package com.example.androidprototype.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class Comment {

    @SerializedName("commentsId")
    @Expose
    private String commentsId;

    @SerializedName("dateposted")
    private Date datePosted;

    @SerializedName("comment")
    @Expose
    private String comment;

    @SerializedName("user")
    @Expose
    private User user;

    @SerializedName("recipe")
    @Expose
    private Recipe recipe;

    public Comment() {
        super();
    }

    public Comment(String commentsId, Date datePosted, String comment) {
        this.commentsId = commentsId;
        this.datePosted = datePosted;
        this.comment = comment;
    }

    public String getCommentsId() {
        return commentsId;
    }

    public void setCommentsId(String commentsId) {
        this.commentsId = commentsId;
    }

    public Date getDatePosted() {
        return datePosted;
    }

    public void setDatePosted(Date datePosted) {
        this.datePosted = datePosted;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }
}
