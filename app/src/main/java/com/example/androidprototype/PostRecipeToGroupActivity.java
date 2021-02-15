package com.example.androidprototype;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.androidprototype.adpater.GroupAdapter;
import com.example.androidprototype.adpater.SelectGroupAdapter;
import com.example.androidprototype.model.Group;
import com.example.androidprototype.model.GroupList;
import com.example.androidprototype.service.APIService;

import java.util.ArrayList;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PostRecipeToGroupActivity extends AppCompatActivity
    implements View.OnClickListener{

    private ArrayList<Group> myGroups;
    private RecyclerView rvGroup;
    private SelectGroupAdapter groupAdapter;
    private int rId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_recipe_to_group);

        APIService service = RetrofitClient.getRetrofitInstance().create(APIService.class);
        Intent intent = getIntent();
        int recipeId = intent.getIntExtra("recipeId",1);
        rId = recipeId;

        Call<GroupList> call = service.postRecipeToGroups(recipeId);


        call.enqueue(new Callback<GroupList>() {

            @Override
            public void onResponse(Call<GroupList> call, Response<GroupList> response) {

                GroupList groups = response.body();
                if (groups != null) {
                    myGroups = groups.getGrouplist();
                }

                if (myGroups.size() > 0) {
                    // binding adpater and layout manager with steps recyclerview
                    rvGroup = (RecyclerView) findViewById(R.id.GroupRecycler);
                    groupAdapter = new SelectGroupAdapter(myGroups, PostRecipeToGroupActivity.this);

                    rvGroup.setAdapter(groupAdapter);
                    LinearLayoutManager lym_rs = new LinearLayoutManager(PostRecipeToGroupActivity.this);
                    lym_rs.setStackFromEnd(false);
                    rvGroup.setLayoutManager(lym_rs);
                    rvGroup.addItemDecoration(new DividerItemDecoration(PostRecipeToGroupActivity.this, DividerItemDecoration.VERTICAL));
                }
            }

            @Override
            public void onFailure(Call<GroupList> call, Throwable t) {
                System.out.println(t.getMessage());
                Toast.makeText(PostRecipeToGroupActivity.this, "No groups to show", Toast.LENGTH_SHORT).show();
            }
        });

        Button submit = findViewById(R.id.postSubmit);
        submit.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {

        int id = view.getId();

        if (id == R.id.postSubmit) {

            ArrayList<Group> allGrps = groupAdapter.getGroupList();
            ArrayList<Group> toAdd = new ArrayList<>();

            for (Group g : allGrps)
            {
                if (g.isSelected())
                {
                    toAdd.add(g);
                }
            }

            APIService service = RetrofitClient.getRetrofitInstance().create(APIService.class);
            Call<ResponseBody> call = service.postRecipe(rId, toAdd);
            call.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    if(response.isSuccessful()) {
                        Toast.makeText(PostRecipeToGroupActivity.this, "Recipe posted", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    Toast.makeText(PostRecipeToGroupActivity.this, "Unable to post recipe", Toast.LENGTH_SHORT).show();
                }
            });

            Intent intent = new Intent(this, ListGroupActivity.class);
            intent.setAction("nil");
            startActivity(intent);
        }

    }
}