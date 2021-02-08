package com.example.androidprototype;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.androidprototype.model.Recipe;
import com.example.androidprototype.APIService;
import com.example.androidprototype.model.RecipeIngredients;
import com.example.androidprototype.model.RecipeSteps;
import com.example.androidprototype.model.RecipeTag;
import com.example.androidprototype.service.DownloadImageTask;
import com.example.androidprototype.service.ViewRecipeIngredientAdapter;
import com.example.androidprototype.service.ViewRecipeStepAdapter;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ViewRecipe extends AppCompatActivity
    implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_recipe);

        Intent intent = getIntent();
        int recipeId = intent.getIntExtra("RecipeId",1);

        APIService service = RetrofitClient.getRetrofitInstance().create(APIService.class);
        Call<Recipe> call = service.getRecipe(recipeId);

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
                TextView warning = findViewById(R.id.recipeWarning);
                TextView tag = findViewById(R.id.recipeTags);

                name.setText(recipe.getTitle());
                description.setText(recipe.getDescription());
                calories.setText(Integer.toString(recipe.getCalories()) + " kcal");
                datecreated.setText("Created on " + recipe.getDateCreated().toString());
                user.setText("By " + recipe.getUser().getUsername());

                new DownloadImageTask((ImageView) findViewById(R.id.recipeImage))
                        .execute(recipe.getMainMediaUrl());

                String warnings = "";
                String tags = "";
                List<RecipeTag> retag = recipe.getRecipeTags().getRecipeTags();
                for (RecipeTag r : retag)
                {
                    tags += "#" + r.getTagXXId().getTagName() + "\t";

                    if (r.getAllergenTag())
                    {
                        warnings += r.getTagXXId().getWarning();
                    }
                }
                tag.setText(tags);
                if (!warnings.isEmpty())
                {
                    warning.setText("May cause " + warnings);
                }
                else {
                    warning.setText(warnings);
                }




                List<RecipeIngredients> ingredients = recipe.getRecipeIngredients().getRecipeIngredients();

                if (ingredients != null)
                {
                    ViewRecipeIngredientAdapter adapter = new ViewRecipeIngredientAdapter(ViewRecipe.this, 0);
                    adapter.setData(ingredients);

                    ListView ingredlist = findViewById(R.id.ingredientList);
                    if (ingredlist != null)
                    {
                        ingredlist.setAdapter(adapter);
                        setListViewHeightBasedOnChildren(ingredlist);
                        ingredlist.setDivider(null);
                    }
                }

                List<RecipeSteps> steps = recipe.getRecipeSteps().getRecipeIngredients();

                if (steps != null)
                {
                    ViewRecipeStepAdapter adapter1 = new ViewRecipeStepAdapter(ViewRecipe.this, 0);
                    adapter1.setData(steps);

                    ListView steplist = findViewById(R.id.stepList);
                    if (steplist != null)
                    {
                        steplist.setAdapter(adapter1);
                        setListViewHeightBasedOnChildren(steplist);
                    }
                }
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

    public static void setListViewHeightBasedOnChildren(ListView listView)
    {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null)
            return;

        int desiredWidth = View.MeasureSpec.makeMeasureSpec(listView.getWidth(), View.MeasureSpec.UNSPECIFIED);
        int totalHeight=0;
        View view = null;

        for (int i = 0; i < listAdapter.getCount(); i++)
        {
            view = listAdapter.getView(i, view, listView);

            if (i == 0)
                view.setLayoutParams(new ViewGroup.LayoutParams(desiredWidth,
                        ViewGroup.LayoutParams.MATCH_PARENT));

            view.measure(desiredWidth, View.MeasureSpec.UNSPECIFIED);
            totalHeight += view.getMeasuredHeight();

        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + ((listView.getDividerHeight()) * (listAdapter.getCount()));

        listView.setLayoutParams(params);
        listView.requestLayout();

    }
}