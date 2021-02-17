package com.example.androidprototype.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TagJson {
    @SerializedName("tagName")
    @Expose
    private String tagName;

    @SerializedName("warning")
    @Expose
    private String warning;

    public TagJson() { super(); }

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
}
