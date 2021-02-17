package com.example.androidprototype.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RecipeTagJson {
    @SerializedName("isAllergenTag")
    @Expose
    private Boolean isAllergenTag;

    @SerializedName("tag")
    @Expose
    private TagJson tagXXId;

    public RecipeTagJson() { super(); }

    public Boolean getAllergenTag() {
        return isAllergenTag;
    }

    public void setAllergenTag(Boolean allergenTag) {
        isAllergenTag = allergenTag;
    }

    public TagJson getTagXXId() {
        return tagXXId;
    }

    public void setTagXXId(TagJson tagXXId) {
        this.tagXXId = tagXXId;
    }
}
