package com.example.androidprototype.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RecipeStep {
    @SerializedName("recipeStepsId")
    @Expose
    private int recipeStepsId;

    @SerializedName("recipeId")
    @Expose
    private int recipeId;

    @SerializedName("stepNumber")
    @Expose
    private int stepNumber;

    @SerializedName("textInstructions")
    @Expose
    private String textInstructions;

    @SerializedName("MediaFileUrl")
    @Expose
    private String MediaFileUrl;

    //JsonIgnore
    //FK
    @SerializedName("RecipeId")
    @Expose
    private Recipe RecipeId;
}
