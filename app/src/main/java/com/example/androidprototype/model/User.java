package com.example.androidprototype.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class User<T> {
    @SerializedName("$id")
    @Expose
    private String id;

    @SerializedName("firstName")
    @Expose
    private String firstName;

    @SerializedName("lastName")
    @Expose
    private String lastName;

    @SerializedName("username")
    @Expose
    private String username;

    @SerializedName("password")
    @Expose
    private String password;

    @SerializedName("email")
    @Expose
    private String email;

    @SerializedName("isAdmin")
    @Expose
    private boolean isAdmin;

    @SerializedName("comments")
    @Expose
    private CommentList comment;

    @SerializedName("likesDislikes")
    @Expose
    private LikesDislikesList likesDislikes;

    @SerializedName("recipes")
    @Expose
    private RecipeList recipes;

    @SerializedName("usersGroups")
    @Expose
    private UserGroupList groups;

    public User() {
        super();
    }

    public User(String firstName, String lastName, String username, String password, String email, boolean isAdmin) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.email = email;
        this.isAdmin = isAdmin;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    public RecipeList getRecipes() {
        return recipes;
    }

    public void setRecipes(RecipeList recipes) {
        this.recipes = recipes;
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

    public UserGroupList getGroups() {
        return groups;
    }

    public void setGroups(UserGroupList groups) {
        this.groups = groups;
    }
}
