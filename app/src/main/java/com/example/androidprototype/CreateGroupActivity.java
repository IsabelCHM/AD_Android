package com.example.androidprototype;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.androidprototype.model.Group;
import com.example.androidprototype.model.Tag;
import com.example.androidprototype.model.User;
import com.example.androidprototype.model.groupUserJson;
import com.example.androidprototype.service.APIService;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreateGroupActivity extends AppCompatActivity
    implements View.OnClickListener{

    private EditText nameET;
    private EditText descET;
    private ImageView photoIV;
    private EditText tagsET;

    private ArrayList<Tag> tags;

    private APIService service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_group);

        //titleET = (EditText) findViewById(R.id.recipeTitle);

        nameET = (EditText) findViewById(R.id.cgrpName);
        descET = (EditText) findViewById(R.id.cgrpDesc);
        photoIV = (ImageView) findViewById(R.id.cgrpPhoto);
        tagsET = (EditText) findViewById(R.id.cgrpTags);

        Button submit = findViewById(R.id.cGrpSubmit);
        submit.setOnClickListener(this);

        Button test = findViewById(R.id.test);
        test.setOnClickListener(this);

        Button home = findViewById(R.id.refreshHome);
        home.setOnClickListener(this);

        Button groups = findViewById(R.id.groups);
        groups.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {

        int id = view.getId();

        if (id == R.id.test) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }

        if (id == R.id.refreshHome) {
            Intent intent = new Intent(this, HomeActivity.class);
            intent.setAction("REFRESH");
            startActivity(intent);
        }

        if (id == R.id.groups) {
            Intent intent = new Intent(this, ListGroupActivity.class);
            intent.setAction("view");
            startActivity(intent);
        }

        if (id == R.id.cGrpSubmit) {
            CreateGroup();
        }

    }

    public void CreateGroup() {
        APIService service = RetrofitClient.getRetrofitInstance().create(APIService.class);

        Group newGroup = new Group();
        String grpName = nameET.getText().toString();
        if (grpName.trim().isEmpty())
        {
            Toast.makeText(CreateGroupActivity.this, "Group name cannot be blank", Toast.LENGTH_SHORT).show();
            return;
        }
        newGroup.setGroupName(grpName);
        newGroup.setDescription(descET.getText().toString());

        String tags = tagsET.getText().toString();

        SharedPreferences pref = getSharedPreferences("user_info", MODE_PRIVATE);
        int userId = pref.getInt("UserId", 0);

        User u = new User();
        u.setIdJson(userId);

        Call<Group> call = service.saveGroup(new groupUserJson(u, newGroup, tags));
        call.enqueue(new Callback<Group>() {
            @Override
            public void onResponse(Call<Group> call, Response<Group> response) {
                if(response.isSuccessful()) {
                    Group rg = response.body();
                    Toast.makeText(CreateGroupActivity.this, "Group created successfully", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(CreateGroupActivity.this, ViewGroupActivity.class);
                    intent.putExtra("GroupId",rg.getGroupId());
                    startActivity(intent);
                }
            }

            @Override
            public void onFailure(Call<Group> call, Throwable t) {
                Toast.makeText(CreateGroupActivity.this, "Unable to create group", Toast.LENGTH_SHORT).show();
            }
        });


    }
}