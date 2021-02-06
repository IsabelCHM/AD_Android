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
import com.example.androidprototype.APIService;

import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreateRecipeZ extends AppCompatActivity {
    private APIService service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_recipe_z);

        final EditText titleET = (EditText) findViewById(R.id.recipeTitle);
        final EditText desET = (EditText) findViewById(R.id.description);
        final EditText durationET = (EditText) findViewById(R.id.duration);
        final EditText caloriesET = (EditText) findViewById(R.id.calories);
        final EditText servingSizeET = (EditText) findViewById(R.id.servingSize);
        Button submit = (Button) findViewById(R.id.submit);
        service = RetrofitClient.getRetrofitInstance().create(APIService.class);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = titleET.getText().toString().trim();
                String description = desET.getText().toString().trim();
                int duration = Integer.parseInt(durationET.getText().toString());
                int calories = Integer.parseInt(caloriesET.getText().toString());
                int servingSize = Integer.parseInt(servingSizeET.getText().toString());

                if (!TextUtils.isEmpty(title) && !TextUtils.isEmpty(description))
                    sendRecipe(title, description, duration, calories, servingSize);
            }
        });
    }

    public void sendRecipe(String title, String description, int duration, int calories, int servingSize) {
        Recipe recipe = new Recipe(title, description, new Date(), duration, calories, servingSize);
        Call<Recipe> call = service.saveRecipe(recipe);
        call.enqueue(new Callback<Recipe>() {
            @Override
            public void onResponse(Call<Recipe> call, Response<Recipe> response) {
                if (response.isSuccessful()){
                    Intent intent = new Intent(getApplicationContext(), SuccessPage.class);
                    startActivity(intent);
                }
            }

            @Override
            public void onFailure(Call<Recipe> call, Throwable t) {
                Toast.makeText(CreateRecipeZ.this, "Unable to save recipe", Toast.LENGTH_SHORT).show();
            }
        });
    }


}