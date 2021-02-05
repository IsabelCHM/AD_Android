package com.example.androidprototype.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RecipeGroup {

    @SerializedName("group")
    @Expose
    private Group group;

    @SerializedName("recipe")
    @Expose
    private Recipe recipe;

    public RecipeGroup() {
        super();
    }

    public RecipeGroup(Group group, Recipe recipe) {
        this.group = group;
        this.recipe = recipe;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }
}
