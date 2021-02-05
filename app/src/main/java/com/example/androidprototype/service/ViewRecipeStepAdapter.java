package com.example.androidprototype.service;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.androidprototype.R;
import com.example.androidprototype.model.RecipeIngredients;
import com.example.androidprototype.model.RecipeSteps;

import java.util.List;

public class ViewRecipeStepAdapter extends ArrayAdapter {

    private final Context context;
    private List<RecipeSteps> steps;

    public ViewRecipeStepAdapter(@NonNull Context context, int resource) {
        super(context, resource);
        this.context = context;
    }

    public void setData(List<RecipeSteps> steps)
    {
        this.steps = steps;

        for (int i = 0; i<steps.size(); i++)
        {
            add(null);
        }
    }

    public View getView(int pos, View view, @NonNull ViewGroup parent) {
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(
                    Activity.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.steps_list, parent, false);
        }

        TextView textView = view.findViewById(R.id.steptxt);
        textView.setText("Step " + Integer.toString(steps.get(pos).getStepNumber()) + ": "
                        + steps.get(pos).getTextInstructions());

        ImageView imageView = view.findViewById(R.id.stepmedia);
        imageView.setImageResource(R.drawable.placeholder);

        return view;
    }
}