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

    public RecipeStepsList(int id, List<RecipeSteps> recipeIngredients) {
        this.id = id;
        this.recipeIngredients = recipeIngredients;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<RecipeSteps> getRecipeIngredients() {
        return recipeIngredients;
    }

    public void setRecipeIngredients(List<RecipeSteps> recipeIngredients) {
        this.recipeIngredients = recipeIngredients;
    }
}
