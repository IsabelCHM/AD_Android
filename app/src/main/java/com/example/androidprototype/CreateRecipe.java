package com.example.androidprototype;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.androidprototype.adpater.RecipeIngredientAdapter;
import com.example.androidprototype.adpater.RecipeStepAdapter;
import com.example.androidprototype.model.RecipeIngredients;
import com.example.androidprototype.model.RecipeSteps;

import java.util.ArrayList;

public class CreateRecipe extends AppCompatActivity
    implements View.OnClickListener {
    private Button addStepBtn;
    private Button addIngredientBtn;
    //private Button deleteIngredientBtn;
    private ArrayList<RecipeSteps> recipeStepsList;
    private ArrayList<RecipeIngredients> recipeIngredientsList;
    private RecyclerView rvRecipeStep;
    private RecyclerView rvRecipeIngredient;
    private RecipeStepAdapter rsAdapter;
    private RecipeIngredientAdapter riAdapter;

    private ImageView imgView;
    private EditText titleET;
    private EditText desET;
    private EditText servingSizeET;
    private EditText durationET;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_recipe);

        imgView = (ImageView) findViewById(R.id.recipeCover);
        titleET = (EditText) findViewById(R.id.recipeTitle);
        desET = (EditText) findViewById(R.id.description);
        servingSizeET = (EditText) findViewById(R.id.servingSize);
        durationET = (EditText) findViewById(R.id.duration);

        // Initiate a new list for recipe steps
        RecipeSteps recipeSteps = new RecipeSteps();
        recipeSteps.setStepNumber(1);
        recipeStepsList= new ArrayList<>();

        recipeStepsList.add(recipeSteps);

        // Initiate a new list for recipe ingredients
        RecipeIngredients recipeIngredients = new RecipeIngredients();
        recipeIngredients.setRecipeIngredientsId(1);
        recipeIngredientsList = new ArrayList<>();

        recipeIngredientsList.add(recipeIngredients);

        // binding adpater and layout manager with steps recyclerview
        rvRecipeStep = (RecyclerView) findViewById(R.id.rvRecipeStep);
        rsAdapter = new RecipeStepAdapter(recipeStepsList);

        rvRecipeStep.setAdapter(rsAdapter);
        LinearLayoutManager lym_rs = new LinearLayoutManager(this);
        lym_rs.setStackFromEnd(true);
        rvRecipeStep.setLayoutManager(lym_rs);

        // binding adapter and layout manager with ingredients recyclerview
        rvRecipeIngredient = (RecyclerView) findViewById(R.id.rvIngredient);
        riAdapter = new RecipeIngredientAdapter(recipeIngredientsList);

        rvRecipeIngredient.setAdapter(riAdapter);
        LinearLayoutManager lym_ri = new LinearLayoutManager(this);
        lym_ri.setStackFromEnd(true);
        rvRecipeIngredient.setLayoutManager(lym_ri);

        addStepBtn = findViewById(R.id.addStep);
        addIngredientBtn = findViewById(R.id.addIngredient);
        //deleteIngredientBtn = findViewById(R.id.deleteIngredient);

        addStepBtn.setOnClickListener(this);
        addIngredientBtn.setOnClickListener(this);
        //deleteIngredientBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();

        if (id == R.id.addStep) {
            RecipeSteps newRecipeStep = new RecipeSteps();
            newRecipeStep.setStepNumber(recipeStepsList.size()+1);
            rsAdapter.addStep(newRecipeStep);
            rvRecipeStep.scrollToPosition(recipeStepsList.size()-1);
        }

        if (id == R.id.addIngredient) {
            RecipeIngredients newRecipeIngredient = new RecipeIngredients();
            newRecipeIngredient.setRecipeIngredientsId(recipeIngredientsList.size()+1);
            riAdapter.addStep(newRecipeIngredient);
            rvRecipeIngredient.scrollToPosition(recipeStepsList.size()-1);
        }

    }
}