package com.example.androidprototype.service;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.androidprototype.R;
import com.example.androidprototype.model.RecipeIngredients;

import java.util.ArrayList;
import java.util.List;

public class ViewRecipeIngredientAdapter extends ArrayAdapter {

    private final Context context;
    private List<RecipeIngredients> ingredients;

    public ViewRecipeIngredientAdapter(@NonNull Context context, int resource) {
        super(context, resource);
        this.context = context;
    }

    public void setData(List<RecipeIngredients> ingredients)
    {
        this.ingredients = ingredients;

        for (int i = 0; i<ingredients.size(); i++)
        {
            add(null);
        }
    }

    public View getView(int pos, View view, @NonNull ViewGroup parent) {
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(
                    Activity.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.ingredient_list, parent, false);
        }

        TextView textView = view.findViewById(R.id.ingredtxt);
        textView.setText(Double.toString(ingredients.get(pos).getQuantity()) + " "
                + ingredients.get(pos).getUnitOfMeasurement() +  " of " +
                ingredients.get(pos).getIngredient());

        return view;
    }
}
