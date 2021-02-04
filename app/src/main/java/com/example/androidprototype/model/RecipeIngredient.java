package com.example.androidprototype.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RecipeIngredient {

    @SerializedName("recipeIngredientsId")
    @Expose
    private int recipeIngredientsId;

    @SerializedName("recipeId")
    @Expose
    private int recipeId;

    @SerializedName("ingredient")
    @Expose
    private String ingredient;

    @SerializedName("Quantity")
    @Expose
    private double quantity;

    @SerializedName("UnitOfMeasurement")
    @Expose
    private String unitOfMeasurement;

    //JsonIgnore
    //FK
    @SerializedName("RecipeXXId")
    @Expose
    private Recipe recipe;
}
