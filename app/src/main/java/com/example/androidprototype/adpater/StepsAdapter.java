package com.example.androidprototype.adpater;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidprototype.R;
import com.example.androidprototype.ViewGroupActivity;
import com.example.androidprototype.model.Group;
import com.example.androidprototype.model.RecipeSteps;
import com.example.androidprototype.service.DownloadImageTask;

import java.util.ArrayList;
import java.util.List;

public class StepsAdapter extends
        RecyclerView.Adapter<StepsAdapter.ViewHolder>{

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView instructions;
        public ImageView mainMedia;


        public ViewHolder(View itemView) {
            super(itemView);

            instructions = itemView.findViewById(R.id.steptxt);
            mainMedia = itemView.findViewById(R.id.stepmedia);


        }

        public TextView getInstructions() {
            return instructions;
        }

        public void setInstructions(TextView instructions) {
            this.instructions = instructions;
        }

        public ImageView getMainMedia() {
            return mainMedia;
        }

        public void setMainMedia(ImageView mainMedia) {
            this.mainMedia = mainMedia;
        }
    }

    private List<RecipeSteps> steps;
    private Context context;

    public StepsAdapter(List<RecipeSteps> steps, Context context) {
        this.steps = steps;
        this.context = context;
    }

    @NonNull
    @Override
    public StepsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View groupView = inflater.inflate(R.layout.steps_list, parent, false);

        // Return a new holder instance
        StepsAdapter.ViewHolder viewHolder = new StepsAdapter.ViewHolder(groupView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull StepsAdapter.ViewHolder holder, int position) {

        RecipeSteps step = steps.get(position);
        holder.getInstructions().setText("Step " + Integer.toString(position + 1) + ": "
                + step.getTextInstructions());
        String image_url = step.getMediaFileUrl();

        if (image_url != null) {
            new DownloadImageTask((ImageView) holder.getMainMedia())
                    .execute(image_url);
        }
        else {
            holder.getMainMedia().setVisibility(View.GONE);
        }

    }

    @Override
    public int getItemCount() {
        return steps.size();
    }
}