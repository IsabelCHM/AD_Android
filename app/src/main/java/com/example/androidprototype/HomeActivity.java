package com.example.androidprototype;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidprototype.model.Recipe;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.androidprototype.adpater.HomeAdapter;
import com.example.androidprototype.model.RecipeList;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import com.example.androidprototype.service.APIService;

public class HomeActivity extends AppCompatActivity
        implements View.OnClickListener{

    private RecyclerView rvHome;
    private HomeAdapter homeAdapter;
    private ArrayList<Recipe> recipeList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        APIService service = RetrofitClient.getRetrofitInstance().create(APIService.class);
        Call<RecipeList> call = service.getAllRecipes();



        call.enqueue(new Callback<RecipeList>() {

            @Override
            public void onResponse(Call<RecipeList> call, Response<RecipeList> response) {

                RecipeList recipes = response.body();
                if (recipes != null) {
                    recipeList = recipes.getRecipelist();
                }


                // binding adpater and layout manager with steps recyclerview
                rvHome = (RecyclerView) findViewById(R.id.HomeRecycler);
                homeAdapter = new HomeAdapter(recipeList, HomeActivity.this);

                rvHome.setAdapter(homeAdapter);
                LinearLayoutManager lym_rs = new LinearLayoutManager(HomeActivity.this);
                lym_rs.setStackFromEnd(false);
                rvHome.setLayoutManager(lym_rs);
                rvHome.addItemDecoration(new DividerItemDecoration(HomeActivity.this, DividerItemDecoration.VERTICAL));

            }

            @Override
            public void onFailure(Call<RecipeList> call, Throwable t) {
                System.out.println(t.getMessage());
                Toast.makeText(HomeActivity.this, "No recipes to show", Toast.LENGTH_SHORT).show();
            }
        });

        FloatingActionButton fab = findViewById(R.id.fabCreate);
        fab.setOnClickListener(this);

        Button test = findViewById(R.id.test);
        test.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        int id = view.getId();
        if (id == R.id.fabCreate){
            Intent intent = new Intent(this, CreateRecipe.class);
            startActivity(intent);
        }

        if (id == R.id.test) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }


    }
}