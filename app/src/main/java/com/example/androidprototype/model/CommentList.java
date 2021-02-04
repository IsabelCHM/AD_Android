package com.example.androidprototype.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class CommentList {

    @SerializedName("$id")
    @Expose
    private String id;

    @SerializedName("$values")
    @Expose
    private ArrayList<Comment> commentlist;

    public CommentList(String id, ArrayList<Comment> commentlist) {
        this.id = id;
        this.commentlist = commentlist;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ArrayList<Comment> getCommentlist() {
        return commentlist;
    }

    public void setCommentlist(ArrayList<Comment> commentlist) {
        this.commentlist = commentlist;
    }
}
