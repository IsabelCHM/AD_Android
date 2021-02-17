package com.example.androidprototype.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RecipePlusTags {

    @SerializedName("recipe")
    @Expose
    private RecipeJson recipe;

    /*@SerializedName("tags")
    @Expose
    private List<RecipeTagJson> recipeTagList;*/

    @SerializedName("tags")
    @Expose
    private String recipeTagString;

    /*public RecipePlusTags(RecipeJson recipeJson, List<RecipeTagJson> recipeTagList) {
        this.recipe = recipeJson;
        this.recipeTagList = recipeTagList;
    }*/

    public RecipePlusTags(RecipeJson recipe, String recipeTagString) {
        this.recipe = recipe;
        this.recipeTagString = recipeTagString;
    }
}
