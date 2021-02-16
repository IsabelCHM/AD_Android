package com.example.androidprototype.adpater;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.provider.MediaStore;
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
import com.example.androidprototype.model.RecipeStepsJson;
import com.example.androidprototype.service.ListItemClickListener;

import java.util.ArrayList;
import java.util.List;

// Create the basic adapter extending from RecyclerView.Adapter
// Note that we specify the custom ViewHolder which gives us access to our views
public class RecipeStepAdapter extends
        RecyclerView.Adapter<RecipeStepAdapter.ViewHolder> {

    final private ListItemClickListener mOnClickListener;
    private int selectedPos = -1;

    private ArrayList<RecipeStepsJson> recipeStepsList;
    private ArrayList<Bitmap> stepImg;

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView recipeStep;
        public ImageView stepImg;
        public EditText stepInstruction;

        public ViewHolder(View itemView) {
            super(itemView);
            recipeStep = (TextView) itemView.findViewById(R.id.recipeStep);
            stepImg = (ImageView) itemView.findViewById(R.id.stepImg);
            stepInstruction = (EditText) itemView.findViewById(R.id.stepInstruction);

            InstructionTextWatcher instructionTextWatcher = new InstructionTextWatcher(stepInstruction);
            stepInstruction.addTextChangedListener(instructionTextWatcher);

            stepImg.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            selectedPos = getAdapterPosition();
            mOnClickListener.onListItemClick(selectedPos);
        }
    }



    public RecipeStepAdapter(ListItemClickListener onClickListener, ArrayList<RecipeStepsJson> recipeStepsList) {
        this.recipeStepsList = recipeStepsList;
        this.mOnClickListener = onClickListener;
        this.stepImg = new ArrayList<>();
        stepImg.add(null);
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

        holder.stepImg.setImageBitmap(stepImg.get(position));

    }

    @Override
    public int getItemCount() {
        return recipeStepsList.size();
    }

    public void addStep(RecipeStepsJson newRecipeStep) {
        recipeStepsList.add(newRecipeStep);
        stepImg.add(null);
        notifyItemInserted(recipeStepsList.size()-1);
    }

    public void setStepImg(Bitmap img, int position) {
        stepImg.set(position, img);
    }

    public void setStepImgUrl(String imgUrl, int position) {
        recipeStepsList.get(position).setMediaFileUrl(imgUrl);
    }

    public List<RecipeStepsJson> getRecipeStepsList() { return recipeStepsList; }

    class InstructionTextWatcher implements TextWatcher {

        private EditText editText;

        public InstructionTextWatcher(EditText editText) { this.editText = editText; }

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
