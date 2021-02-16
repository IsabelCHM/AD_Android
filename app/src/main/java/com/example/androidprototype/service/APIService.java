package com.example.androidprototype.service;

import com.example.androidprototype.model.Group;
import com.example.androidprototype.model.GroupList;
import com.example.androidprototype.model.Recipe;
import com.example.androidprototype.model.RecipeIngredients;
import com.example.androidprototype.model.RecipeIngredientsJson;
import com.example.androidprototype.model.RecipeJson;
import com.example.androidprototype.model.RecipeList;
import com.example.androidprototype.model.RecipeTagList;
import com.example.androidprototype.model.SavedRecipe;
import com.example.androidprototype.model.SavedRecipeJson;
import com.example.androidprototype.model.TagList;
import com.example.androidprototype.model.User;
import com.example.androidprototype.model.UserAllergenList;
import com.example.androidprototype.model.UserGroup;
import com.example.androidprototype.model.UserGroupList;
import com.example.androidprototype.model.UserJson;
import com.example.androidprototype.model.booleanJson;
import com.example.androidprototype.model.groupUserJson;

import java.util.ArrayList;
import java.util.List;

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
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
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

    @POST("api/mUser/validateuser")
    Call<User> validateUser(@Body UserJson userjson);

    @GET("/api/mUserGroup/UserId/{id}")
    Call<UserGroupList> getUserGroupList(@Path("id") int userId);

    @POST("api/mUserGroup/")
    Call<booleanJson> joinGroup(@Body UserGroup usergroup);

    @POST("api/mRecipe/update/{id}")
    Call<booleanJson> updateRecipe(@Body Recipe recipe, @Path("id") int recipeId);

    @GET("/api/mUser/userallergen/{id}")
    Call<UserAllergenList> getUserAllergenList(@Path("id") int userId);

    @POST("/api/mUser/saveuserrecipelist")
    Call<booleanJson> saveRecipeList(@Body SavedRecipeJson savedRecipeJson);

    @GET("/api/mRecipe/getAllRecipes/")
    Call<RecipeList> getAllRecipes();

    @GET("/api/mRecipe/search/{search}")
    Call<RecipeList> searchRecipes(@Path("search") String search);

    @POST("/api/mGroup/getGroup")
    Call<Group> getGroup(@Body UserGroup ug);

    @GET("/api/mGroup/recipeGroups/{id}")
    Call<GroupList> postRecipeToGroups(@Path("id") int id);

    @GET("/api/mGroup/search/{search}")
    Call<GroupList> searchGroups(@Path("search") String search);

    @POST("api/mGroup/")
    Call<Group> saveGroup(@Body groupUserJson guj);

    @POST("api/mGroup/addRtoG/{id}")
    Call<ResponseBody> postRecipe(@Path("id") int id, @Body ArrayList<Group> groups);

    @GET("api/mGroup/getall")
    Call<GroupList> getAllGroups();

    @POST("api/mRecipe/generateATags")
    Call<RecipeTagList> generateATags(@Body List<RecipeIngredientsJson> ingredients);
}
