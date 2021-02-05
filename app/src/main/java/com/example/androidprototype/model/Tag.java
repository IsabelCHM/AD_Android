package com.example.androidprototype.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Tag {

// oh no
    @SerializedName("tagId")
    @Expose
    private int tagId;

    @SerializedName("tagName")
    @Expose
    private String tagName;

    @SerializedName("warning")
    @Expose
    private String warning;

    //JsonIgnore
    //RK
    @SerializedName("RecipeTags")// Danger
    @Expose
    private List<RecipeTag> recipeTags;

    public int getTagId() {
        return tagId;
    }

    public void setTagId(int tagId) {
        this.tagId = tagId;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public String getWarning() {
        return warning;
    }

    public void setWarning(String warning) {
        this.warning = warning;
    }

    public List<RecipeTag> getRecipeTags() {
        return recipeTags;
    }

    public void setRecipeTags(List<RecipeTag> recipeTags) {
        this.recipeTags = recipeTags;
    }
}
