package com.example.androidprototype.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class RecipeGroupList {

    @SerializedName("$id")
    @Expose
    private String id;

    @SerializedName("$values")
    @Expose
    private ArrayList<RecipeGroup> recipegrouplist;

    public RecipeGroupList(String id, ArrayList<RecipeGroup> recipegrouplist) {
        this.id = id;
        this.recipegrouplist = recipegrouplist;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ArrayList<RecipeGroup> getRecipegrouplist() {
        return recipegrouplist;
    }

    public void setRecipegrouplist(ArrayList<RecipeGroup> recipegrouplist) {
        this.recipegrouplist = recipegrouplist;
    }
}
