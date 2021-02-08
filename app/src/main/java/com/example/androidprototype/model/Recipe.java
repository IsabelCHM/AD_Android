package com.example.androidprototype.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


import java.util.Date;
import java.util.List;

import retrofit2.http.HEAD;

public class Recipe {

    @SerializedName("recipeId")
    @Expose
    private int id;

    @SerializedName("title")
    @Expose
    private String title;

    @SerializedName("description")
    @Expose
    private String description;

    @SerializedName("mainMediaUrl")
    @Expose
    private String mainMediaUrl;

    @SerializedName("dateCreated")
    private Date dateCreated;

    @SerializedName("durationInMins")
    @Expose
    private int durationInMins;

    @SerializedName("calories")
    @Expose
    private int calories;

    @SerializedName("servingSize")
    @Expose
    private int servingSize;

    @SerializedName("user")
    @Expose
    private User user;

    @SerializedName("recipeIngredients")
    @Expose
    private RecipeIngredientsList recipeIngredients;

    @SerializedName("recipeSteps")
    @Expose
    private RecipeStepsList recipeSteps;

    @SerializedName("recipeTags")
    @Expose
    private RecipeTagList recipeTags;

    @SerializedName("comments")
    @Expose
    private CommentList comment;

    @SerializedName("likesDislikes")
    @Expose
    private LikesDislikesList likesDislikes;

    public Recipe() {
        super();
    }

    public Recipe(String title, String description, Date dateCreated, int durationInMins, int calories, int servingSize) {
        this.title = title;
        this.description = description;
        this.dateCreated = dateCreated;
        this.durationInMins = durationInMins;
        this.calories = calories;
        this.servingSize = servingSize;
    }

    public Recipe(String title, String description, int durationInMins, int calories, int servingSize) {
        this.title = title;
        this.description = description;
        this.dateCreated = dateCreated;
        this.durationInMins = durationInMins;
        this.calories = calories;
        this.servingSize = servingSize;
    }

    public Recipe(String title, String description, Date dateCreated, int durationInMins, int calories, RecipeIngredientsList recipeIngredients, RecipeStepsList recipeSteps, RecipeTagList recipeTags) {
        this.title = title;
        this.description = description;
        this.dateCreated = dateCreated;
        this.durationInMins = durationInMins;
        this.calories = calories;
        this.recipeIngredients = recipeIngredients;
        this.recipeSteps = recipeSteps;
        this.recipeTags = recipeTags;
    }



    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public int getDurationInMins() {
        return durationInMins;
    }

    public void setDurationInMins(int durationInMins) {
        this.durationInMins = durationInMins;
    }

    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getServingSize() {
        return servingSize;
    }

    public void setServingSize(int servingSize) {
        this.servingSize = servingSize;
    }

    public void setRecipeSteps(RecipeStepsList recipeSteps) {
        this.recipeSteps = recipeSteps;
    }

    public void setRecipeIngredients(RecipeIngredientsList recipeIngredients) {
        this.recipeIngredients = recipeIngredients;
    }

    public RecipeIngredientsList getRecipeIngredients() {
        return recipeIngredients;
    }

    public RecipeStepsList getRecipeSteps() {
        return recipeSteps;
    }

    public RecipeTagList getRecipeTags() {
        return recipeTags;
    }

    public void setRecipeTags(RecipeTagList recipeTags) {
        this.recipeTags = recipeTags;
    }

    public CommentList getComment() {
        return comment;
    }

    public void setComment(CommentList comment) {
        this.comment = comment;
    }

    public LikesDislikesList getLikesDislikes() {
        return likesDislikes;
    }

    public void setLikesDislikes(LikesDislikesList likesDislikes) {
        this.likesDislikes = likesDislikes;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getMainMediaUrl() {
        return mainMediaUrl;
    }

    public void setMainMediaUrl(String mainMediaUrl) {
        this.mainMediaUrl = mainMediaUrl;
    }
}
