package com.example.androidprototype.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class RecipeList {

    @SerializedName("$id")
    @Expose
    private String id;

    @SerializedName("$values")
    @Expose
    private ArrayList<Recipe> recipelist;

    public RecipeList(String id, ArrayList<Recipe> recipelist) {
        this.id = id;
        this.recipelist = recipelist;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ArrayList<Recipe> getRecipelist() {
        return recipelist;
    }

    public void setRecipelist(ArrayList<Recipe> recipelist) {
        this.recipelist = recipelist;
    }
}
