package com.example.androidprototype;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.androidprototype.model.Recipe;
import com.example.androidprototype.model.RecipeJson;
import com.example.androidprototype.model.booleanJson;
import com.example.androidprototype.service.APIService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditRecipeZ extends AppCompatActivity
    implements View.OnClickListener {
    private APIService apiService;
    EditText titleET;
    EditText descriptionET;
    EditText durationET;
    EditText caloriesET;
    EditText servingSizeET;
    Button update;
    Button back;
    private int RecipeId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_recipe_z);

        RecipeId = getIntent().getIntExtra("recipeId", 0);

        apiService = RetrofitClient.getRetrofitInstance().create(APIService.class);
        // hard coded recipe Id, need to change later
        Intent intent = getIntent();
        RecipeId = intent.getIntExtra("RecipeId", 0);
        Call<Recipe> call = apiService.getRecipe(RecipeId);

        titleET = findViewById(R.id.recipeTitle);
        descriptionET = findViewById(R.id.description);
        durationET = findViewById(R.id.duration);
        caloriesET = findViewById(R.id.calories);
        servingSizeET = findViewById(R.id.servingSize);
        update = findViewById(R.id.update);
        back = findViewById(R.id.back);

        update.setOnClickListener(this);
        back.setOnClickListener(this);

        call.enqueue(new Callback<Recipe>() {
            @Override
            public void onResponse(Call<Recipe> call, Response<Recipe> response) {
                Recipe recipe = response.body();

                titleET.setText(recipe.getTitle());
                descriptionET.setText(recipe.getDescription());
                durationET.setText(Integer.toString(recipe.getDurationInMins()));
                caloriesET.setText(Integer.toString(recipe.getCalories()));
                servingSizeET.setText(Integer.toString(recipe.getServingSize()));
            }

            @Override
            public void onFailure(Call<Recipe> call, Throwable t) {
                System.out.println(t.getMessage());
                Toast.makeText(EditRecipeZ.this, "Unable to load recipe", Toast.LENGTH_SHORT).show();
            }
        });

    }



    public void sendRecipe(String title, String description, int duration,
                           int calories, int servingSize) {
        RecipeJson recipe = new RecipeJson(title, description, duration, calories, servingSize);
        Call<booleanJson> call = apiService.updateRecipe(recipe, RecipeId);

        call.enqueue(new Callback<booleanJson>() {
            @Override
            public void onResponse(Call<booleanJson> call, Response<booleanJson> response) {
                if (response.isSuccessful()) {
                    if (response.body().getFlag() == true){
                        Toast.makeText(getApplicationContext(), "Recipe has been updated successfully!", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), EditRecipeZ.class);
                        intent.putExtra("recipeId", RecipeId);
                        startActivity(intent);
                    }
                }
            }

            @Override
            public void onFailure(Call<booleanJson> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Unable to save recipe", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.update) {
            String title = titleET.getText().toString().trim();
            String description = descriptionET.getText().toString().trim();
            int duration = Integer.parseInt(durationET.getText().toString());
            int calories = Integer.parseInt(caloriesET.getText().toString());
            int servingSize = Integer.parseInt(servingSizeET.getText().toString());

            if (!TextUtils.isEmpty(title) && !TextUtils.isEmpty(description))
                sendRecipe(title, description, duration, calories, servingSize);
        }

        if (id == R.id.back) {
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
        }
    }
}