package com.example.androidprototype.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RecipeIngredientsList {
    @SerializedName("$id")
    @Expose
    private int id;

    @SerializedName("$values")
    @Expose
    private List<RecipeIngredients> recipeIngredients;

    public RecipeIngredientsList() {
        super();
    }

    public RecipeIngredientsList(int id, List<RecipeIngredients> recipeIngredients) {
        this.id = id;
        this.recipeIngredients = recipeIngredients;
    }

    public void setRecipeIngredients(List<RecipeIngredients> recipeIngredients) {
        this.recipeIngredients = recipeIngredients;
    }

    public int getId() {
        return id;
    }

    public List<RecipeIngredients> getRecipeIngredients() {
        return recipeIngredients;
    }

    public void setId(int id) {
        this.id = id;
    }
}
