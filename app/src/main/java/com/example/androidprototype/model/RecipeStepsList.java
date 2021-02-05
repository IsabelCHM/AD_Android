package com.example.androidprototype.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RecipeStepsList {
    @SerializedName("$id")
    @Expose
    private int id;

    @SerializedName("$values")
    @Expose
    private List<RecipeSteps> recipeIngredients;
}
