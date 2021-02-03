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

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreateRecipe extends AppCompatActivity {
    private APIService service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_recipe);

        final EditText titleET = (EditText) findViewById(R.id.recipeTitle);
        final EditText desET = (EditText) findViewById(R.id.description);
        final EditText durationET = (EditText) findViewById(R.id.duration);
        final EditText caloriesET = (EditText) findViewById(R.id.calories);
        Button submit = (Button) findViewById(R.id.submit);
        service = RetrofitClient.getRetrofitInstance().create(APIService.class);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = titleET.getText().toString().trim();
                String description = desET.getText().toString().trim();
                int duration = Integer.parseInt(durationET.getText().toString());
                int calories = Integer.parseInt(caloriesET.getText().toString());

                if (!TextUtils.isEmpty(title) && !TextUtils.isEmpty(description))
                    sendRecipe(title, description, duration, calories);
            }
        });
    }

    public void sendRecipe(String name, String description, int duration, int calories) {
        Recipe recipe = new Recipe(name, description, duration, calories);
        Call<Recipe> call = service.saveRecipe(recipe);
        call.enqueue(new Callback<Recipe>() {
            @Override
            public void onResponse(Call<Recipe> call, Response<Recipe> response) {

                    Intent intent = new Intent(getApplicationContext(), SuccessPage.class);
                    startActivity(intent);
            }

            @Override
            public void onFailure(Call<Recipe> call, Throwable t) {
                Toast.makeText(CreateRecipe.this, "Unable to save recipe", Toast.LENGTH_SHORT).show();
            }
        });
    }


}