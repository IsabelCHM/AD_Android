package com.example.androidprototype.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RecipeTagList {
    @SerializedName("$id")
    @Expose
    private String id;

    @SerializedName("$values")
    @Expose
    private List<RecipeTag> recipeIngredientsList;

    public RecipeTagList() {
        super();
    }

    public RecipeTagList(String id, List<RecipeTag> recipeIngredientsList){
        this.id = id;
        this.recipeIngredientsList = recipeIngredientsList;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public List<RecipeTag> getRecipeIngredientsList() {
        return recipeIngredientsList;
    }

    public void setRecipeIngredientsList(List<RecipeTag> recipeIngredientsList) {
        this.recipeIngredientsList = recipeIngredientsList;
    }
}
