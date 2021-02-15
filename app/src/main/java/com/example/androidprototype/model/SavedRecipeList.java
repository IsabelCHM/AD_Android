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
    private ArrayList<SavedRecipe> savedRecipe;

    public SavedRecipeList(String id, ArrayList<SavedRecipe> savedRecipe) {
        this.id = id;
        this.savedRecipe = savedRecipe;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ArrayList<SavedRecipe> getsavedRecipe() {
        return savedRecipe;
    }

    public void setSavedRecipe(ArrayList<SavedRecipe> savedRecipe) {
        this.savedRecipe = savedRecipe;
    }
}
