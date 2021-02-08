package com.example.androidprototype.adpater;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.androidprototype.R;
import com.example.androidprototype.model.Recipe;
import com.example.androidprototype.service.DownloadImageTask;

import java.util.List;

public class RecipeUserProfileAdaptor extends ArrayAdapter {

    private final Context context;
    private List<Recipe> recipes;

    public RecipeUserProfileAdaptor(@NonNull Context context, int resource) {
        super(context, resource);
        this.context = context;
    }

    public void setData(List<Recipe> recipes) {
        this.recipes = recipes;

        for (int i = 0; i < recipes.size(); i++) {
            add(null);
        }
    }

    public View getView(int pos, View view, @NonNull ViewGroup parent) {
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(
                    Activity.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.listview_recipes_user_profile, parent, false);
        }

        TextView tvRecipeName = view.findViewById(R.id.tv_lv_RecipeName);
        TextView tvRecipeDuration = view.findViewById(R.id.tv_lv_RecipeDurtation);
        TextView tvRecipeServingSize = view.findViewById(R.id.tv_lv_RecipeServingSize);
        TextView tvRecipeCalories = view.findViewById(R.id.tv_lv_RecipeCalories);

        if (recipes.get(pos).getMainMediaUrl() == null) {
            ImageView recipeImage = view.findViewById(R.id.tv_iv_RecipeMainImage);
            recipeImage.setImageResource(R.drawable.placeholder);
        }
        else {
            new DownloadImageTask((ImageView) view.findViewById(R.id.tv_iv_RecipeMainImage))
                    .execute(recipes.get(pos).getMainMediaUrl());
        }

        tvRecipeName.setText(recipes.get(pos).getTitle());
        tvRecipeServingSize.setText("Serving Size: " + Integer.toString(recipes.get(pos).getServingSize()) + "  ");
        tvRecipeDuration.setText("Preparation Time: " + Integer.toString(recipes.get(pos).getDurationInMins()));
        tvRecipeCalories.setText("Calories: " + Integer.toString(recipes.get(pos).getCalories()));
        return view;
    }
}
