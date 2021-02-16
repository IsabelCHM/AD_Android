package com.example.androidprototype;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
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
import com.example.androidprototype.model.booleanJson;
import com.example.androidprototype.service.APIService;
import com.example.androidprototype.model.RecipeIngredients;
import com.example.androidprototype.model.RecipeSteps;
import com.example.androidprototype.model.RecipeTag;
import com.example.androidprototype.service.DownloadImageTask;
import com.example.androidprototype.service.ViewRecipeIngredientAdapter;
import com.example.androidprototype.service.ViewRecipeStepAdapter;

import org.w3c.dom.Text;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ViewRecipe extends AppCompatActivity
    implements View.OnClickListener{

    private int rId;
    private Recipe recipe;
    private APIService service;
    private int userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_recipe);

        // Need to change after user login is done
        SharedPreferences pref = getSharedPreferences("user_info", MODE_PRIVATE);
        userId = pref.getInt("UserId", 0);

        Intent intent = getIntent();
        int recipeId = intent.getIntExtra("RecipeId",0);
        rId = recipeId;

        service = RetrofitClient.getRetrofitInstance().create(APIService.class);
        Call<Recipe> call = service.getRecipe(recipeId);

        Button back = findViewById(R.id.back);
        Button edit = findViewById(R.id.edit);
        Button postToGrp = findViewById(R.id.post2Grp);
        Button delete = findViewById(R.id.btnDeleteRecipe);

        back.setOnClickListener(this);
        edit.setOnClickListener(this);
        postToGrp.setOnClickListener(this);
        delete.setOnClickListener(this);

        call.enqueue(new Callback<Recipe>() {
            @Override
            public void onResponse(Call<Recipe> call, Response<Recipe> response) {
                recipe = response.body();
                TextView name = findViewById(R.id.recipeTitle);
                TextView description = findViewById(R.id.recipeDescription);
                TextView calories = findViewById(R.id.recipeCalories);
                TextView datecreated = findViewById(R.id.recipeDateCreated);
                TextView user = findViewById(R.id.recipeUser);
                TextView warning = findViewById(R.id.recipeWarning);
                TextView tag = findViewById(R.id.recipeTags);
                TextView duration = findViewById(R.id.recipeDuration);

                if (recipe.getUserId() != userId)
                {
                    postToGrp.setVisibility(View.GONE);
                }

                name.setText(recipe.getTitle());
                description.setText(recipe.getDescription());
                calories.setText(Integer.toString(recipe.getCalories()) + " kcal");
                datecreated.setText("Created on " + recipe.getDateCreated().toString());
                user.setText("By " + recipe.getUser().getUsername());

                int durationFlag = recipe.getDurationInMins();
                switch (durationFlag) {
                    case 1:
                        duration.setText("15mins");
                        break;
                    case 2:
                        duration.setText("15 ~ 30mins");
                        break;
                    case 3:
                        duration.setText("30 ~ 60mins");
                        break;
                    case 4:
                        duration.setText("> 60mins");
                        break;
                }

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

                if (userId != recipe.getUserId()) {
                    edit.setVisibility(View.GONE);
                    delete.setVisibility(View.GONE);
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
            Intent intent = new Intent(this, HomeActivity.class);
            intent.setAction("nil");
            startActivity(intent);
        }

        if (id == R.id.edit) {
            Intent intent = new Intent(this, EditRecipe.class);
            intent.putExtra("recipeId", recipe.getId());
            startActivity(intent);
        }

        if (id == R.id.post2Grp) {
            Intent intent = new Intent(this, PostRecipeToGroupActivity.class);
            intent.putExtra("recipeId", rId);
            startActivity(intent);
        }

        if (id == R.id.btnDeleteRecipe) {
            int recipeId = recipe.getId();
            int recipeCreatedByUserId = recipe.getUserId();
            if (userId == recipeCreatedByUserId) {
                deleteRecipe(recipeId);
            }
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

    public void deleteRecipe(int recipeId) {
        Call<booleanJson> call = service.deleteRecipe(recipeId);

        call.enqueue(new Callback<booleanJson>() {
            @Override
            public void onResponse(Call<booleanJson> call, Response<booleanJson> response) {
                if (response.isSuccessful()) {
                    Intent intent = new Intent(getApplicationContext(), ViewUserProfile.class);
                    intent.putExtra("userId", userId);
                    startActivity(intent);
                }
            }

            @Override
            public void onFailure(Call<booleanJson> call, Throwable t) {
                System.out.println("Fail to delete");
            }
        });
    }
}