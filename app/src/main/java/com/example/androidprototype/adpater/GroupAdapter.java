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
import com.example.androidprototype.ViewRecipe;
import com.example.androidprototype.model.Group;
import com.example.androidprototype.model.Recipe;
import com.example.androidprototype.service.DownloadImageTask;

import java.util.ArrayList;

public class GroupAdapter extends
        RecyclerView.Adapter<GroupAdapter.ViewHolder>{

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView groupName;
        public TextView createdOn;
        public ImageView mainMedia;
        public TextView groupDesc;


        public ViewHolder(View itemView) {
            super(itemView);
            groupName = itemView.findViewById(R.id.groupName);
            createdOn = itemView.findViewById(R.id.groupDate);
            mainMedia = itemView.findViewById(R.id.groupImage);
            groupDesc = itemView.findViewById(R.id.groupDesc);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, ViewGroupActivity.class);
                    int i = getAdapterPosition();
                    System.out.println(groupList.get(i).getGroupName());
                    intent.setAction("VIEW_MY_GROUPS");
                    intent.putExtra("GroupId", groupList.get(i).getGroupId());
                    context.startActivity(intent);
                }
            });

            /*itemView.findViewById(R.id.viewRecipe).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, ViewRecipe.class);
                    int i = getAdapterPosition();
                    intent.putExtra("RecipeId", recipeList.get(i).getId());
                    context.startActivity(intent);
                }
            });*/
        }

        public TextView getGroupName() {
            return groupName;
        }

        public void setGroupName(TextView groupName) {
            this.groupName = groupName;
        }

        public TextView getCreatedOn() {
            return createdOn;
        }

        public void setCreatedOn(TextView createdOn) {
            this.createdOn = createdOn;
        }

        public ImageView getMainMedia() {
            return mainMedia;
        }

        public void setMainMedia(ImageView mainMedia) {
            this.mainMedia = mainMedia;
        }

        public TextView getGroupDesc() {
            return groupDesc;
        }

        public void setGroupDesc(TextView groupDesc) {
            this.groupDesc = groupDesc;
        }
    }

    private ArrayList<Group> groupList;
    private Context context;

    public GroupAdapter(ArrayList<Group> groupList, Context context) {
        this.groupList = groupList;
        this.context = context;
    }

    @NonNull
    @Override
    public GroupAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View groupView = inflater.inflate(R.layout.group_recycler, parent, false);

        // Return a new holder instance
        GroupAdapter.ViewHolder viewHolder = new GroupAdapter.ViewHolder(groupView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull GroupAdapter.ViewHolder holder, int position) {

        Group group = groupList.get(position);
        holder.getGroupName().setText(group.getGroupName());
        holder.getGroupDesc().setText(group.getDescription());
        holder.getCreatedOn().setText("Created on " + group.getDateCreated().toString());
        new DownloadImageTask((ImageView) holder.getMainMedia())
                .execute(group.getGroupPhoto());

    }

    @Override
    public int getItemCount() {
        return groupList.size();
    }
}
