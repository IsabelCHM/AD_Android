package com.example.androidprototype.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RecipeIngredientsJson {
    @SerializedName("ingredient")
    @Expose
    private String ingredient;

    @SerializedName("Quantity")
    @Expose
    private double quantity;

    @SerializedName("UnitOfMeasurement")
    @Expose
    private String unitOfMeasurement;

    public RecipeIngredientsJson() {
        super();
    }

    public String getIngredient() {
        return ingredient;
    }

    public void setIngredient(String ingredient) {
        this.ingredient = ingredient;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public String getUnitOfMeasurement() {
        return unitOfMeasurement;
    }

    public void setUnitOfMeasurement(String unitOfMeasurement) {
        this.unitOfMeasurement = unitOfMeasurement;
    }
}
