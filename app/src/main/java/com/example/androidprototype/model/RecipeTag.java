package com.example.androidprototype.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RecipeTag {
    @SerializedName("recipeTagId")
    @Expose
    private int recipeTagId;

    @SerializedName("recipeId")
    @Expose
    private int recipeId;

    @SerializedName("tagId")
    @Expose
    private int tagId;

    @SerializedName("isAllergenTag")
    @Expose
    private Boolean isAllergenTag;

    //JsonIgnore
    //fk
    @SerializedName("Recipe")
    @Expose
    private Recipe recipeXXId;

    //FK
    @SerializedName("tag")
    @Expose
    private Tag tagXXId;

    public int getRecipeTagId() {
        return recipeTagId;
    }

    public void setRecipeTagId(int recipeTagId) {
        this.recipeTagId = recipeTagId;
    }

    public int getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(int recipeId) {
        this.recipeId = recipeId;
    }

    public int getTagId() {
        return tagId;
    }

    public void setTagId(int tagId) {
        this.tagId = tagId;
    }

    public Boolean getAllergenTag() {
        return isAllergenTag;
    }

    public void setAllergenTag(Boolean allergenTag) {
        isAllergenTag = allergenTag;
    }

    public Recipe getRecipeXXId() {
        return recipeXXId;
    }

    public void setRecipeXXId(Recipe recipeXXId) {
        this.recipeXXId = recipeXXId;
    }

    public Tag getTagXXId() {
        return tagXXId;
    }

    public void setTagXXId(Tag tagXXId) {
        this.tagXXId = tagXXId;
    }
}
