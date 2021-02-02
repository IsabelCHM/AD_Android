package com.example.androidprototype;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

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
                TextView name = findViewById(R.id.recipeName);
                TextView ingredient = findViewById(R.id.recipeIngredient);
                name.setText(recipe.getName());
                ingredient.setText(recipe.getIngredient());
            }

            @Override
            public void onFailure(Call<Recipe> call, Throwable t) {
                System.out.println(t.getMessage());
                Toast.makeText(ViewRecipe.this, "Unable to load recipe", Toast.LENGTH_SHORT).show();
            }
        });
    }
}