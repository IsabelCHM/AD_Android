package com.example.androidprototype.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class LikesDislikesList {

    @SerializedName("$id")
    @Expose
    private String id;

    @SerializedName("$values")
    @Expose
    private ArrayList<LikesDislikes> likesdislikeslist;

    public LikesDislikesList(String id, ArrayList<LikesDislikes> likesdislikeslist) {
        this.id = id;
        this.likesdislikeslist = likesdislikeslist;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ArrayList<LikesDislikes> getLikesdislikeslist() {
        return likesdislikeslist;
    }

    public void setLikesdislikeslist(ArrayList<LikesDislikes> likesdislikeslist) {
        this.likesdislikeslist = likesdislikeslist;
    }
}
