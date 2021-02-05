package com.example.androidprototype.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RecipeTagList {
    @SerializedName("$id")
    @Expose
    private int id;

    @SerializedName("$values")
    @Expose
    private List<RecipeTag> recipeTags;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<RecipeTag> getRecipeTags() {
        return recipeTags;
    }

    public void setRecipeTags(List<RecipeTag> recipeTags) {
        this.recipeTags = recipeTags;
    }
}
