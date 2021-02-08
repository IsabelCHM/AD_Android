package com.example.androidprototype.adpater;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
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
import com.example.androidprototype.model.RecipeStepsJson;

import java.util.ArrayList;
import java.util.List;

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

            InstructionTextWather instructionTextWather = new InstructionTextWather(stepInstruction);
            stepInstruction.addTextChangedListener(instructionTextWather);
        }
    }

    private ArrayList<RecipeStepsJson> recipeStepsList;

    public RecipeStepAdapter(ArrayList<RecipeStepsJson> recipeStepsList) {
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
        RecipeStepsJson recipeSteps = recipeStepsList.get(position);

        TextView textView = holder.recipeStep;
        textView.setText("Step " + String.valueOf(recipeSteps.getStepNumber()));

        holder.stepImg.setTag(position);
        holder.stepInstruction.setTag(position);

    }

    @Override
    public int getItemCount() {
        return recipeStepsList.size();
    }

    public void addStep(RecipeStepsJson newRecipeStep) {
        recipeStepsList.add(newRecipeStep);
        notifyItemInserted(recipeStepsList.size()-1);
    }

    public List<RecipeStepsJson> getRecipeStepsList() { return recipeStepsList; }

    class InstructionTextWather implements TextWatcher {

        private EditText editText;

        public InstructionTextWather(EditText editText) { this.editText = editText; }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) { }

        @Override
        public void afterTextChanged(Editable s) {
            int position = (int) editText.getTag();
            recipeStepsList.get(position).setTextInstructions(s.toString());
        }
    }
}
