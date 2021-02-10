package com.example.androidprototype.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SavedRecipe {

    @SerializedName("user")
    @Expose
    private User user;

    @SerializedName("recipe")
    @Expose
    private Recipe recipe;

    @SerializedName("userId")
    @Expose
    private int userId;

    @SerializedName("recipeId")
    @Expose
    private int recipeId;

    public SavedRecipe() {
        super();
    }

    public SavedRecipe(User user, Recipe recipe) {
        this.user = user;
        this.recipe = recipe;
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

    public int getUserId() {
        return userId;
    }

    public int getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(int recipeId) {
        this.recipeId = recipeId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
