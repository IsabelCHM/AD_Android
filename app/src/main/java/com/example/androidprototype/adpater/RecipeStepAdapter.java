package com.example.androidprototype.adpater;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidprototype.R;
import com.example.androidprototype.model.RecipeIngredientsJson;
import com.example.androidprototype.model.RecipeStepsJson;
import com.example.androidprototype.service.DownloadImageTask;
import com.example.androidprototype.service.ListItemClickListener;

import org.w3c.dom.Text;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

// Create the basic adapter extending from RecyclerView.Adapter
// Note that we specify the custom ViewHolder which gives us access to our views
public class RecipeStepAdapter extends
        RecyclerView.Adapter<RecipeStepAdapter.ViewHolder> {

    final private ListItemClickListener mOnClickListener;
    private int selectedPos = -1;

    RecipeStepsJson mRecentlyDeletedItem;
    int mRecentlyDeletedItemPos;

    private ArrayList<RecipeStepsJson> recipeStepsList;
    private ArrayList<Bitmap> stepImg;
    private ArrayList<String> instructions;

    private Bitmap result;
    private int imgPos;

    public void deleteItem(int position) {
        mRecentlyDeletedItem = recipeStepsList.get(position);
        mRecentlyDeletedItemPos = position;
        recipeStepsList.remove(position);
        stepImg.remove(mRecentlyDeletedItemPos);
        instructions.remove(mRecentlyDeletedItemPos);
        notifyItemRemoved(position);

        for (int i = 0; i < recipeStepsList.size(); i++) {
            recipeStepsList.get(i).setStepNumber(i+1);
            notifyItemChanged(i);
        }
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView recipeStep;
        public ImageView stepImgView;
        public EditText stepInstruction;
        public TextView stepText;

        public ViewHolder(View itemView) {
            super(itemView);
            recipeStep = (TextView) itemView.findViewById(R.id.recipeStep);
            stepImgView = (ImageView) itemView.findViewById(R.id.stepImg);
            stepInstruction = (EditText) itemView.findViewById(R.id.stepInstruction);
            stepText = (TextView) itemView.findViewById(R.id.stepText);

            InstructionTextWatcher instructionTextWatcher = new InstructionTextWatcher(stepInstruction);
            stepInstruction.addTextChangedListener(instructionTextWatcher);

            stepImgView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            selectedPos = getAdapterPosition();
            mOnClickListener.onListItemClick(selectedPos);
            stepText.setText(" ");
        }
    }



    public RecipeStepAdapter(ListItemClickListener onClickListener, ArrayList<RecipeStepsJson> recipeStepsList) {
        this.recipeStepsList = recipeStepsList;
        this.mOnClickListener = onClickListener;
        this.stepImg = new ArrayList<>();
        this.instructions = new ArrayList<>();

        for (int i = 0; i < recipeStepsList.size(); i++) {
            stepImg.add(null);
            instructions.add(" ");
        }

        imgPos = recipeStepsList.size()-1;
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
        holder.stepImgView.setTag(position);
        holder.stepInstruction.setTag(position);

        RecipeStepsJson recipeSteps = recipeStepsList.get(position);

        TextView textView = holder.recipeStep;
        textView.setText("Step " + String.valueOf(recipeSteps.getStepNumber()));


        if (!recipeSteps.isChanged()) {
            if (recipeSteps.getMediaFileUrl() != null) {
                new GetBitmap(holder.stepImgView)
                        .execute(recipeSteps.getMediaFileUrl());
            }

            recipeSteps.setChanged(true);
        }

        holder.stepInstruction.setText(recipeSteps.getTextInstructions());
        holder.stepImgView.setImageBitmap(stepImg.get(position));
    }



    private class GetBitmap extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;

        public GetBitmap(ImageView bmImage) {
            this.bmImage = bmImage;
        }

        @Override
        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap mIcon11 = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                mIcon11 = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return mIcon11;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            //  return the bitmap by doInBackground and store in result
            result = bitmap;
            stepImg.set(imgPos, result);
            bmImage.setImageBitmap(result);
            imgPos -= 1;
        }
    }

    @Override
    public int getItemCount() {
        return recipeStepsList.size();
    }

    public void addStep(RecipeStepsJson newRecipeStep) {
        recipeStepsList.add(newRecipeStep);
        stepImg.add(null);
        instructions.add(" ");
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
            if (!s.toString().trim().equals("")) {
                recipeStepsList.get(position).setTextInstructions(s.toString());
                instructions.set(position, s.toString());
            }

        }
    }


}
