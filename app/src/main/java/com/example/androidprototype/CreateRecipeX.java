package com.example.androidprototype;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.androidprototype.adpater.RecipeStepAdapter;
import com.example.androidprototype.model.RecipeSteps;

import java.util.ArrayList;
import java.util.List;

public class CreateRecipeX extends AppCompatActivity
    implements View.OnClickListener {
    private Button addStepBtn;
    private ArrayList<RecipeSteps> recipeStepsList;
    RecyclerView rvRecipe;
    RecipeStepAdapter rsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_recipe_x);

        RecipeSteps recipeSteps = new RecipeSteps();
        recipeSteps.setStepNumber(1);
        recipeStepsList= new ArrayList<>();

        recipeStepsList.add(recipeSteps);

        rvRecipe = (RecyclerView) findViewById(R.id.rvRecipe);
        rsAdapter = new RecipeStepAdapter(recipeStepsList);

        rvRecipe.setAdapter(rsAdapter);
        LinearLayoutManager lym = new LinearLayoutManager(this);
        lym.setStackFromEnd(true);
        rvRecipe.setLayoutManager(lym);

        addStepBtn = findViewById(R.id.addStep);
        addStepBtn.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        int id = v.getId();

        if (id == R.id.addStep) {
            RecipeSteps newRecipeStep = new RecipeSteps();
            newRecipeStep.setStepNumber(recipeStepsList.size()+1);
            rsAdapter.addStep(newRecipeStep);
            rvRecipe.scrollToPosition(recipeStepsList.size()-1);
        }
    }
}