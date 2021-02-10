package com.example.androidprototype.model;

public class SavedRecipeJson {
    private int userId;
    private int recipeId;

    public SavedRecipeJson() {}
    public SavedRecipeJson(int userId, int recipeId) {
        this.userId = userId;
        this.recipeId = recipeId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setRecipeId(int recipeId) {
        this.recipeId = recipeId;
    }

    public int getRecipeId() {
        return recipeId;
    }

    public int getUserId() {
        return userId;
    }
}
