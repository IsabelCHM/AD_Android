package com.example.androidprototype.adpater;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidprototype.R;
import com.example.androidprototype.model.RecipeSteps;

import java.util.ArrayList;

// Create the basic adapter extending from RecyclerView.Adapter
// Note that we specify the custom ViewHolder which gives us access to our views
public class RecipeStepAdapter extends
        RecyclerView.Adapter<RecipeStepAdapter.ViewHolder> {

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView recipeStep;
        public ImageView stepImg;
        public EditText stepInstruction;

        public ViewHolder(View itemView) {
            super(itemView);
            recipeStep = (TextView) itemView.findViewById(R.id.recipeStep);
            stepImg = (ImageView) itemView.findViewById(R.id.stepImg);
            stepInstruction = (EditText) itemView.findViewById(R.id.stepInstruction);
        }
    }

    private ArrayList<RecipeSteps> recipeStepsList;

    public RecipeStepAdapter(ArrayList<RecipeSteps> recipeStepsList) {
        this.recipeStepsList = recipeStepsList;
    }

    @NonNull
    @Override
    public RecipeStepAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View recipeStepView = inflater.inflate(R.layout.recipe_step, parent, false);

        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(recipeStepView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecipeStepAdapter.ViewHolder holder, int position) {
        RecipeSteps recipeSteps = recipeStepsList.get(position);

        TextView textView = holder.recipeStep;
        textView.setText("Step " + String.valueOf(recipeSteps.getStepNumber()));

    }

    @Override
    public int getItemCount() {
        return recipeStepsList.size();
    }

    public void addStep(RecipeSteps newRecipeStep) {
        recipeStepsList.add(newRecipeStep);
        notifyItemInserted(recipeStepsList.size()-1);
    }
}
