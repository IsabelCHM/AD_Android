package com.example.androidprototype.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


import java.util.Date;
import java.util.List;

import retrofit2.http.HEAD;

public class RecipeJson {

    //@SerializedName("recipeId")
    //private int id;

    @SerializedName("userId")
    @Expose
    private String userId;

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
    private List<RecipeIngredientsJson> recipeIngredientsList;

    @SerializedName("recipeSteps")
    @Expose
    private List<RecipeStepsJson> recipeStepsList;

    @SerializedName("recipeTags")
    @Expose
    private List<RecipeTagJson> recipeTags;

    @SerializedName("comments")
    @Expose
    private CommentList comment;

    @SerializedName("likesDislikes")
    @Expose
    private LikesDislikesList likesDislikes;

    public RecipeJson() {
        super();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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

    public String getMainMediaUrl() {
        return mainMediaUrl;
    }

    public void setMainMediaUrl(String mainMediaUrl) {
        this.mainMediaUrl = mainMediaUrl;
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

    public int getServingSize() {
        return servingSize;
    }

    public void setServingSize(int servingSize) {
        this.servingSize = servingSize;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<RecipeIngredientsJson> getRecipeIngredientsList() {
        return recipeIngredientsList;
    }

    public void setRecipeIngredientsList(List<RecipeIngredientsJson> recipeIngredientsList) {
        this.recipeIngredientsList = recipeIngredientsList;
    }

    public List<RecipeStepsJson> getRecipeStepsList() {
        return recipeStepsList;
    }

    public void setRecipeStepsList(List<RecipeStepsJson> recipeStepsList) {
        this.recipeStepsList = recipeStepsList;
    }

    public List<RecipeTagJson> getRecipeTags() {
        return recipeTags;
    }

    public void setRecipeTags(List<RecipeTagJson> recipeTags) {
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

    public RecipeJson(String title, String description, int duration, int calories, int servingSize) {
        this.title = title;
        this.description = description;
        this.durationInMins = duration;
        this.calories = calories;
        this.servingSize = servingSize;
    }
}
