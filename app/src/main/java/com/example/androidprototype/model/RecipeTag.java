package com.example.androidprototype.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
    //Need to update the fields to match the returning json object
public class RecipeTag {
    @SerializedName("RecipeTagId")
    @Expose
    private int recipeTagId;

    @SerializedName("RecipeId")
    @Expose
    private int recipeId;

    @SerializedName("TagId")
    @Expose
    private int tagId;

    @SerializedName("IsAllergenTag")
    @Expose
    private Boolean isAllergenTag;

    //JsonIgnore
    //fk
    @SerializedName("RecipeXXId")
    @Expose
    private Recipe recipeXXId;

    //FK
    @SerializedName("TagXXId")
    @Expose
    private Tag tagXXId;

}
