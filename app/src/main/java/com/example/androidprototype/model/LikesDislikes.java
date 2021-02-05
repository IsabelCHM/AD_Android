package com.example.androidprototype.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LikesDislikes {

    @SerializedName("isLiked")
    @Expose
    private boolean isLiked;

    @SerializedName("user")
    @Expose
    private User user;

    @SerializedName("recipe")
    @Expose
    private Recipe recipe;

    public LikesDislikes() {
        super();
    }

    public LikesDislikes(boolean isLiked) {
        this.isLiked = isLiked;
    }

    public boolean isLiked() {
        return isLiked;
    }

    public void setLiked(boolean liked) {
        isLiked = liked;
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
}
