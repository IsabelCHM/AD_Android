package com.example.androidprototype;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.Toast;

import com.example.androidprototype.adpater.RecipeIngredientAdapter;
import com.example.androidprototype.adpater.RecipeStepAdapter;
import com.example.androidprototype.model.RecipeIngredients;
import com.example.androidprototype.model.RecipeIngredientsJson;
import com.example.androidprototype.model.RecipeJson;
import com.example.androidprototype.model.RecipeSteps;

import com.example.androidprototype.model.RecipeStepsJson;
import com.example.androidprototype.service.APIService;

import java.util.ArrayList;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreateRecipe extends AppCompatActivity
    implements View.OnClickListener {
    private Button addStepBtn;
    private Button addIngredientBtn;
    private Button createRecipeBtn;
    private Button mins15;
    private Button mins15_30;
    private Button mins30_60;
    private Button mins60Plus;
    //private Button deleteIngredientBtn;
    private ArrayList<RecipeStepsJson> recipeStepsList;
    private ArrayList<RecipeIngredientsJson> recipeIngredientsList;
    private RecyclerView rvRecipeStep;
    private RecyclerView rvRecipeIngredient;
    private RecipeStepAdapter rsAdapter;
    private RecipeIngredientAdapter riAdapter;

    private ImageView imgView;
    private EditText titleET;
    private EditText desET;
    private EditText servingSizeET;
    private EditText durationET;

    private APIService service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_recipe);

        // Retrofit Service
        service = RetrofitClient.getRetrofitInstance().create(APIService.class);

        imgView = (ImageView) findViewById(R.id.recipeCover);
        titleET = (EditText) findViewById(R.id.recipeTitle);
        desET = (EditText) findViewById(R.id.description);
        servingSizeET = (EditText) findViewById(R.id.servingSize);
        durationET = (EditText) findViewById(R.id.duration);

        mins15 = (Button) findViewById(R.id.mins15);
        mins15_30 = (Button) findViewById(R.id.mins15_30);
        mins30_60 = (Button) findViewById(R.id.mins30_60);
        mins60Plus = (Button) findViewById(R.id.mins60plus);


        // Initiate a new list for recipe steps
        RecipeStepsJson recipeSteps = new RecipeStepsJson();
        recipeSteps.setStepNumber(1);
        recipeStepsList= new ArrayList<>();

        recipeStepsList.add(recipeSteps);

        // Initiate a new list for recipe ingredients
        RecipeIngredientsJson recipeIngredientsJson = new RecipeIngredientsJson();
        //recipeIngredients.setRecipeIngredientsId(1);
        recipeIngredientsList = new ArrayList<>();

        recipeIngredientsList.add(recipeIngredientsJson);

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
        createRecipeBtn = findViewById(R.id.createRecipe);
        //deleteIngredientBtn = findViewById(R.id.deleteIngredient);

        addStepBtn.setOnClickListener(this);
        addIngredientBtn.setOnClickListener(this);
        createRecipeBtn.setOnClickListener(this);
        //deleteIngredientBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();

        switch (id) {
            case R.id.addStep:
                RecipeStepsJson newRecipeStep = new RecipeStepsJson();
                newRecipeStep.setStepNumber(recipeStepsList.size()+1);
                rsAdapter.addStep(newRecipeStep);
                rvRecipeStep.scrollToPosition(recipeStepsList.size()-1);
                break;
            case R.id.addIngredient:
                RecipeIngredientsJson newRecipeIngredient = new RecipeIngredientsJson();
                //newRecipeIngredient.setRecipeIngredientsId(recipeIngredientsList.size()+1);
                riAdapter.addStep(newRecipeIngredient);
                rvRecipeIngredient.scrollToPosition(recipeStepsList.size()-1);
                break;
            case R.id.createRecipe:
                RecipeJson newRecipe = new RecipeJson();
                newRecipe.setTitle(titleET.getText().toString());
                newRecipe.setDescription(desET.getText().toString());
                newRecipe.setRecipeIngredientsList(riAdapter.getRecipeIngredientList());
                newRecipe.setRecipeStepsList(rsAdapter.getRecipeStepsList());
                newRecipe.setServingSize(Integer.parseInt(servingSizeET.getText().toString()));

                Call<ResponseBody> call = service.saveRecipe(newRecipe);
                call.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if(response.isSuccessful()) {
                            Intent intent = new Intent(getApplicationContext(), SuccessPage.class);
                            startActivity(intent);
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Toast.makeText(CreateRecipe.this, "Unable to save recipe", Toast.LENGTH_SHORT).show();
                    }
                });
                break;
        }

    }

}