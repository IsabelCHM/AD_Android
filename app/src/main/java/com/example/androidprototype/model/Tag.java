package com.example.androidprototype.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Tag {

// oh no
    @SerializedName("TagId")
    @Expose
    private int tagId;

    @SerializedName("TagName")
    @Expose
    private String tagName;

    @SerializedName("Warning")
    @Expose
    private String warning;

    //JsonIgnore
    //RK
    @SerializedName("RecipeTags")// Danger
    @Expose
    private List<RecipeTag> recipeTags;
}
