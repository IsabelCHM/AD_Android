package com.example.androidprototype;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.androidprototype.model.Recipe;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ViewRecipe extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_recipe);

        APIService service = RetrofitClient.getRetrofitInstance().create(APIService.class);
        Call<Recipe> call = service.getRecipe(1);

        call.enqueue(new Callback<Recipe>() {
            @Override
            public void onResponse(Call<Recipe> call, Response<Recipe> response) {
                Recipe recipe = response.body();
                TextView name = findViewById(R.id.recipeTitle);
                TextView ingredient = findViewById(R.id.recipeDescription);
                name.setText(recipe.getTitle());
                ingredient.setText(recipe.getDescription());
            }

            @Override
            public void onFailure(Call<Recipe> call, Throwable t) {
                Toast.makeText(ViewRecipe.this, "Unable to load recipe", Toast.LENGTH_SHORT).show();
            }
        });
    }
}