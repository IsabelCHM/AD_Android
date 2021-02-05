package com.example.androidprototype.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RecipeSteps {

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
    private String mediaFileUrl;

    //JsonIgnore
    //FK
//    @SerializedName("RecipeId")
//    @Expose
//    private Recipe RecipeId;

    public RecipeSteps() {
        super();
    }

    public RecipeSteps(int recipeStepsId, int recipeId, int stepNumber, String textInstructions, String mediaFileUrl) {
        this.recipeStepsId = recipeStepsId;
        this.recipeId = recipeId;
        this.stepNumber = stepNumber;
        this.textInstructions = textInstructions;
        this.mediaFileUrl = mediaFileUrl;
    }

    public int getStepNumber() {
        return stepNumber;
    }

    public String getMediaFileUrl() {
        return mediaFileUrl;
    }

    public String getTextInstructions() {
        return textInstructions;
    }

    public void setMediaFileUrl(String mediaFileUrl) {
        this.mediaFileUrl = mediaFileUrl;
    }

    public void setStepNumber(int stepNumber) {
        this.stepNumber = stepNumber;
    }

    public void setTextInstructions(String textInstructions) {
        this.textInstructions = textInstructions;
    }

}
