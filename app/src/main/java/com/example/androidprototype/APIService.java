package com.example.androidprototype;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface APIService {
    @GET("/api/recipe/{id}")
    Call<Recipe> getRecipe(@Path("id") int recipeId);

    @POST("/api/recipe")
    Call<Recipe> saveRecipe(@Body Recipe recipe);
}
