package com.example.androidprototype.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RecipeIngredients {

    @SerializedName("recipeIngredientsId")
    @Expose
    private int recipeIngredientsId;

    @SerializedName("recipeId")
    @Expose
    private int recipeId;

    @SerializedName("ingredient")
    @Expose
    private String ingredient;

    @SerializedName("quantity")
    @Expose
    private double quantity;

    @SerializedName("unitOfMeasurement")
    @Expose
    private String unitOfMeasurement;

    //JsonIgnore
    //FK
//    @SerializedName("RecipeXXId")
//    @Expose
//    private Recipe recipe;

    public RecipeIngredients() {
        super();
    }

    public RecipeIngredients(int recipeIngredientsId, int recipeId, String ingredient, double quantity, String unitOfMeasurement) {
        this.recipeIngredientsId = recipeIngredientsId;
        this.recipeId = recipeId;
        this.ingredient = ingredient;
        this.quantity = quantity;
        this.unitOfMeasurement = unitOfMeasurement;
    }

    public double getQuantity() {
        return quantity;
    }

    public int getRecipeId() {
        return recipeId;
    }

    public int getRecipeIngredientsId() {
        return recipeIngredientsId;
    }

    public String getIngredient() {
        return ingredient;
    }

    public String getUnitOfMeasurement() {
        return unitOfMeasurement;
    }

    public void setIngredient(String ingredient) {
        this.ingredient = ingredient;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public void setRecipeId(int recipeId) {
        this.recipeId = recipeId;
    }

    public void setRecipeIngredientsId(int recipeIngredientsId) {
        this.recipeIngredientsId = recipeIngredientsId;
    }

    public void setUnitOfMeasurement(String unitOfMeasurement) {
        this.unitOfMeasurement = unitOfMeasurement;
    }
}
