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
import com.example.androidprototype.model.RecipeIngredients;
import com.example.androidprototype.model.RecipeSteps;

import java.util.ArrayList;

// Create the basic adapter extending from RecyclerView.Adapter
// Note that we specify the custom ViewHolder which gives us access to our views
public class RecipeIngredientAdapter extends
        RecyclerView.Adapter<RecipeIngredientAdapter.ViewHolder> {

    public class ViewHolder extends RecyclerView.ViewHolder {

        public EditText material;
        public EditText qty;

        public ViewHolder(View itemView) {
            super(itemView);
            material = itemView.findViewById(R.id.recipeIngredient);
            qty = itemView.findViewById(R.id.recipeQty);


        }
    }

    private ArrayList<RecipeIngredients> recipeIngredientList;

    public RecipeIngredientAdapter(ArrayList<RecipeIngredients> recipeIngredientList) {
        this.recipeIngredientList = recipeIngredientList;
    }

    @NonNull
    @Override
    public RecipeIngredientAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View recipeIngredientView = inflater.inflate(R.layout.recipe_ingredient, parent, false);

        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(recipeIngredientView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecipeIngredientAdapter.ViewHolder holder, int position) {
        holder.material.setTag(position);
        holder.qty.setTag(position);
    }

    @Override
    public int getItemCount() {
        return recipeIngredientList.size();
    }

    public void addStep(RecipeIngredients newRecipeIngredient) {
        recipeIngredientList.add(newRecipeIngredient);
        notifyItemInserted(recipeIngredientList.size()-1);
    }
}
