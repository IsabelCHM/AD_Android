package com.example.androidprototype.adpater;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
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
import com.example.androidprototype.service.DownloadImageTask;

import java.util.ArrayList;

public class SelectGroupAdapter extends
        RecyclerView.Adapter<SelectGroupAdapter.ViewHolder>{

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView groupName;
        public TextView createdOn;
        public ImageView mainMedia;
        public TextView groupDesc;
        public View view;


        public ViewHolder(View itemView) {
            super(itemView);
            view = itemView;
            groupName = itemView.findViewById(R.id.groupName);
            createdOn = itemView.findViewById(R.id.groupDate);
            mainMedia = itemView.findViewById(R.id.groupImage);
            groupDesc = itemView.findViewById(R.id.groupDesc);

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

        public View getView() {
            return view;
        }

        public void setView(View view) {
            this.view = view;
        }
    }

    private ArrayList<Group> groupList;
    private Context context;

    public SelectGroupAdapter(ArrayList<Group> groupList, Context context) {
        this.groupList = groupList;
        this.context = context;
    }

    public ArrayList<Group> getGroupList() {
        return groupList;
    }

    @NonNull
    @Override
    public SelectGroupAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View groupView = inflater.inflate(R.layout.group_recycler, parent, false);

        // Return a new holder instance
        SelectGroupAdapter.ViewHolder viewHolder = new SelectGroupAdapter.ViewHolder(groupView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull SelectGroupAdapter.ViewHolder holder, int position) {

        Group group = groupList.get(position);
        new DownloadImageTask((ImageView) holder.getMainMedia())
                .execute(group.getGroupPhoto());
        holder.getGroupName().setText(group.getGroupName());
        holder.getGroupDesc().setText(group.getDescription());
        holder.getCreatedOn().setText("Created on " + group.getDateCreated().toString());
        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                group.setSelected(!group.isSelected());
                holder.view.setBackgroundColor(group.isSelected() ? Color.CYAN : Color.WHITE);

            }
        });


    }

    @Override
    public int getItemCount() {
        return groupList.size();
    }


}
