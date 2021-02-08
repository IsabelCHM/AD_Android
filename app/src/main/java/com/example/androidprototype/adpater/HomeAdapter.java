package com.example.androidprototype.adpater;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidprototype.CreateRecipe;
import com.example.androidprototype.HomeActivity;
import com.example.androidprototype.R;
import com.example.androidprototype.ViewRecipe;
import com.example.androidprototype.model.Recipe;
import com.example.androidprototype.model.RecipeTag;
import com.example.androidprototype.service.DownloadImageTask;

import java.util.ArrayList;
import java.util.List;

public class HomeAdapter extends
        RecyclerView.Adapter<HomeAdapter.ViewHolder>{

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView title;
        public TextView author;
        public ImageView mainMedia;
        public TextView duration;
        public TextView allergens;
        public TextView calories;
        public TextView tags;
        public Recipe recipe;


        public ViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.recipetitlehome);
            author = itemView.findViewById(R.id.recipeownerhome);
            mainMedia = itemView.findViewById(R.id.recipepichome);
            duration = itemView.findViewById(R.id.recipedurationhome);
            allergens = itemView.findViewById(R.id.recipeallergyhome);
            calories = itemView.findViewById(R.id.recipecalorieshome);
            tags = itemView.findViewById(R.id.recipehometags);

            itemView.findViewById(R.id.viewRecipe).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, ViewRecipe.class);
                    int i = getAdapterPosition();
                    intent.putExtra("RecipeId", recipeList.get(i).getId());
                    context.startActivity(intent);
                }
            });
        }

        public TextView getTitle() {
            return title;
        }

        public TextView getAuthor() {
            return author;
        }

        public ImageView getMainMedia() {
            return mainMedia;
        }

        public TextView getDuration() {
            return duration;
        }

        public TextView getAllergens() {
            return allergens;
        }

        public TextView getCalories() {
            return calories;
        }

        public TextView getTags() {
            return tags;
        }
    }

    private ArrayList<Recipe> recipeList;
    private Context context;

    public HomeAdapter(ArrayList<Recipe> recipeList, Context context) {
        this.recipeList = recipeList;
        this.context = context;
    }

    @NonNull
    @Override
    public HomeAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View homeView = inflater.inflate(R.layout.home_one_recipe, parent, false);

        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(homeView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull HomeAdapter.ViewHolder holder, int position) {

        holder.getTitle().setText(recipeList.get(position).getTitle());
        holder.getAuthor().setText("By " + recipeList.get(position).getUser().getUsername());
        holder.getDuration().setText(Integer.toString(recipeList.get(position).getDurationInMins())
                                    + "mins");
        holder.getCalories().setText(Integer.toString(recipeList.get(position).getCalories()) + " kcal");

        new DownloadImageTask((ImageView) holder.getMainMedia())
                .execute(recipeList.get(position).getMainMediaUrl());

        String warnings = "";
        String tags = "";
        List<RecipeTag> retag = recipeList.get(position).getRecipeTags().getRecipeTags();
        for (RecipeTag r : retag)
        {
            tags += "#" + r.getTagXXId().getTagName() + "\t";

            if (r.getAllergenTag())
            {
                warnings += r.getTagXXId().getWarning();
            }
        }
        holder.getTags().setText(tags);

        if (!warnings.isEmpty())
        {
            holder.getAllergens().setText("May cause " + warnings);
        }
        else {
            holder.getAllergens().setText(warnings);
        }

    }

    @Override
    public int getItemCount() {
        return recipeList.size();
    }


}
