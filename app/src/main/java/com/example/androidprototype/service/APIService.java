package com.example.androidprototype.service;

import com.example.androidprototype.model.Recipe;
import com.example.androidprototype.model.RecipeJson;
import com.example.androidprototype.model.RecipeList;
import com.example.androidprototype.model.User;
import com.example.androidprototype.model.UserAllergenList;
import com.example.androidprototype.model.UserGroup;
import com.example.androidprototype.model.UserGroupList;
import com.example.androidprototype.model.booleanJson;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Body;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface APIService {
    @GET("/api/mRecipe/{id}")
    Call<Recipe> getRecipe(@Path("id") int recipeId);

    @POST("api/mRecipe/")
    //Call<Recipe> saveRecipe(@Body Recipe recipe);
    Call<ResponseBody> saveRecipe(@Body RecipeJson recipeJson);

    @DELETE("api/mRecipe/delete/{id}")
    Call<booleanJson> deleteRecipe(@Path("id") int recipeId);

    @GET("/api/mUser/{id}")
    Call<User> getUser(@Path("id") int userId);

    @GET("/api/mUserGroup/UserId/{id}")
    Call<UserGroupList> getUserGroupList(@Path("id") int userId);

    @POST("api/mRecipe/update/{id}")
    Call<booleanJson> updateRecipe(@Body Recipe recipe, @Path("id") int recipeId);

    @GET("/api/mUser/userallergen/{id}")
    Call<UserAllergenList> getUserAllergenList(@Path("id") int userId);

    @GET("/api/mRecipe/getAllRecipes/")
    Call<RecipeList> getAllRecipes();

    @GET("/api/mRecipe/search/{search}")
    Call<RecipeList> searchRecipes(@Path("search") String search);
}
