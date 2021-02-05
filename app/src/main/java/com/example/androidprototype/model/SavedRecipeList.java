package com.example.androidprototype.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class SavedRecipeList {

    @SerializedName("$id")
    @Expose
    private String id;

    @SerializedName("$values")
    @Expose
    private ArrayList<SavedRecipe> savedrecipelist;

    public SavedRecipeList(String id, ArrayList<SavedRecipe> savedrecipelist) {
        this.id = id;
        this.savedrecipelist = savedrecipelist;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ArrayList<SavedRecipe> getSavedrecipelist() {
        return savedrecipelist;
    }

    public void setSavedrecipelist(ArrayList<SavedRecipe> savedrecipelist) {
        this.savedrecipelist = savedrecipelist;
    }
}
