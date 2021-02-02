package com.example.androidprototype;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreateRecipe extends AppCompatActivity {
    private APIService service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_recipe);

        final EditText nameET = (EditText) findViewById(R.id.recipeName);
        final EditText ingreET = (EditText) findViewById(R.id.recipeIngredient);
        Button submit = (Button) findViewById(R.id.submit);
        service = RetrofitClient.getRetrofitInstance().create(APIService.class);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = nameET.getText().toString().trim();
                String ingredient = ingreET.getText().toString().trim();

                if (!TextUtils.isEmpty(name) && !TextUtils.isEmpty(ingredient))
                    sendRecipe(name, ingredient);
            }
        });
    }

    public void sendRecipe(String name, String ingredient) {
        Recipe recipe = new Recipe(name, ingredient);
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