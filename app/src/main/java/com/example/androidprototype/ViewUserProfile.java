package com.example.androidprototype;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.androidprototype.APIService;
import com.example.androidprototype.model.Recipe;
import com.example.androidprototype.model.User;

import java.util.ArrayList;
import java.util.Arrays;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ViewUserProfile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_user_profile);

        EditText etUserId = findViewById(R.id.etGetUserProfileId);
        Button btGetUserProfile = findViewById(R.id.btnGetUserProfile);
        TextView tvFullname = findViewById(R.id.tvFullName);
        TextView tvNoOfRecipe = findViewById(R.id.tvNoOfRecipes);

        btGetUserProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int userId = Integer.parseInt(etUserId.getText().toString());

                APIService service = RetrofitClient.getRetrofitInstance().create(com.example.androidprototype.APIService.class);
                Call<User> call = service.getUser(userId);

                call.enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {
                        String firstName = response.body().getFirstName();
                        String lastName = response.body().getLastName();
                        String userName = response.body().getUsername();
                        tvFullname.setText("Name: " + firstName + " " +  lastName);
                        tvFullname.setText("Username: " + userName);

                        ArrayList<Recipe> recipeList = response.body().getRecipes().getRecipelist();
                        int noOfRecipes = response.body().getRecipes().getRecipelist().size();

                        String[] recipesTitle = new String[noOfRecipes];
                        int[] recipesId = new int[noOfRecipes];

                        for (int i = 0; i < noOfRecipes; i++) {
                            Recipe recipe = recipeList.get(i);
                            recipesTitle[i] = recipe.getTitle();
                            recipesId[i] = recipe.getId();

                        }

                        //Test area
                        String test = "";
                        for (int i = 0; i < noOfRecipes; i++) {
                            test = test + " " + recipesTitle[i] + "\n";
                        }

                        tvNoOfRecipe.setText(test);
                    }

                    @Override
                    public void onFailure(Call<User> call, Throwable t) {
                        System.out.println("onFailure");
                    }
                });
            }
        });
    }
}