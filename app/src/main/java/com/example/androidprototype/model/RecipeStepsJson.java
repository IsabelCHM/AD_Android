package com.example.androidprototype.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RecipeStepsJson {
    @SerializedName("stepNumber")
    @Expose
    private int stepNumber;

    @SerializedName("textInstructions")
    @Expose
    private String textInstructions;

    @SerializedName("mediaFileUrl")
    @Expose
    private String mediaFileUrl;

    private boolean isChanged;

    public RecipeStepsJson() {
        super();
        this.isChanged = false;
    }

    public int getStepNumber() {
        return stepNumber;
    }

    public void setStepNumber(int stepNumber) {
        this.stepNumber = stepNumber;
    }

    public String getTextInstructions() {
        return textInstructions;
    }

    public void setTextInstructions(String textInstructions) {
        this.textInstructions = textInstructions;
    }

    public String getMediaFileUrl() {
        return mediaFileUrl;
    }

    public void setMediaFileUrl(String mediaFileUrl) {
        this.mediaFileUrl = mediaFileUrl;
    }

    public boolean isChanged() {
        return isChanged;
    }

    public void setChanged(boolean changed) {
        isChanged = changed;
    }
}
