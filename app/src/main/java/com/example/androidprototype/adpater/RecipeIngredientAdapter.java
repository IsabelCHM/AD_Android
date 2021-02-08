package com.example.androidprototype.adpater;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidprototype.R;
import com.example.androidprototype.model.RecipeIngredientsJson;

import java.util.ArrayList;
import java.util.List;

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

            IngredientTextWatcher ingredientTextWatcher = new IngredientTextWatcher(material);
            material.addTextChangedListener(ingredientTextWatcher);

            QtyTextWatcher qtyTextWatcher = new QtyTextWatcher(qty);
            qty.addTextChangedListener(qtyTextWatcher);
        }
    }

    private ArrayList<RecipeIngredientsJson> recipeIngredientList;

    public RecipeIngredientAdapter(ArrayList<RecipeIngredientsJson> recipeIngredientList) {
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

    public void addStep(RecipeIngredientsJson newRecipeIngredientJson) {
        recipeIngredientList.add(newRecipeIngredientJson);
        notifyItemInserted(recipeIngredientList.size()-1);
    }

    public List<RecipeIngredientsJson> getRecipeIngredientList() {
        return recipeIngredientList;
    }

    public class IngredientTextWatcher implements TextWatcher {

        private EditText editText;

        public IngredientTextWatcher(EditText editText) { this.editText = editText; }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) { }

        @Override
        public void afterTextChanged(Editable s) {
            int position = (int) editText.getTag();
            recipeIngredientList.get(position).setIngredient(s.toString());
        }
    }

    public class QtyTextWatcher implements TextWatcher {

        private EditText editText;

        public QtyTextWatcher(EditText editText) { this.editText = editText; }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) { }

        @Override
        public void afterTextChanged(Editable s) {
            int position = (int) editText.getTag();
            recipeIngredientList.get(position).setQuantity(Double.parseDouble(s.toString()));
        }
    }
}
