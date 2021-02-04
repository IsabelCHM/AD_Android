package com.example.androidprototype;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.androidprototype.model.Recipe;
import com.example.androidprototype.service.APIService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ViewRecipe extends AppCompatActivity
    implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_recipe);

        APIService service = RetrofitClient.getRetrofitInstance().create(APIService.class);
        Call<Recipe> call = service.getRecipe(1);

        Button back = findViewById(R.id.back);
        Button edit = findViewById(R.id.edit);

        back.setOnClickListener(this);
        edit.setOnClickListener(this);


        call.enqueue(new Callback<Recipe>() {
            @Override
            public void onResponse(Call<Recipe> call, Response<Recipe> response) {
                Recipe recipe = response.body();
                TextView name = findViewById(R.id.recipeTitle);
                TextView description = findViewById(R.id.recipeDescription);
                TextView calories = findViewById(R.id.recipeCalories);
                TextView datecreated = findViewById(R.id.recipeDateCreated);
                TextView user = findViewById(R.id.recipeUser);

                name.setText(recipe.getTitle());
                description.setText(recipe.getDescription());
                calories.setText(Integer.toString(recipe.getCalories()));
                datecreated.setText(recipe.getDateCreated().toString());
                user.setText(recipe.getUser().getFirstName());
            }

            @Override
            public void onFailure(Call<Recipe> call, Throwable t) {
                System.out.println(t.getMessage());
                Toast.makeText(ViewRecipe.this, "Unable to load recipe", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.back) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }

        if (id == R.id.edit) {
            Intent intent = new Intent(this, EditRecipe.class);
            startActivity(intent);
        }
    }
}