package com.example.androidprototype;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.androidprototype.adpater.HomeAdapter;
import com.example.androidprototype.model.Group;
import com.example.androidprototype.model.Recipe;
import com.example.androidprototype.model.RecipeGroup;
import com.example.androidprototype.model.RecipeIngredients;
import com.example.androidprototype.model.RecipeSteps;
import com.example.androidprototype.model.RecipeTag;
import com.example.androidprototype.model.User;
import com.example.androidprototype.model.UserGroup;
import com.example.androidprototype.service.APIService;
import com.example.androidprototype.service.DownloadImageTask;
import com.example.androidprototype.service.JoinGroupTask;
import com.example.androidprototype.service.ViewRecipeIngredientAdapter;
import com.example.androidprototype.service.ViewRecipeStepAdapter;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ViewGroupActivity extends AppCompatActivity
            implements View.OnClickListener{

    private Group group;
    private ArrayList<Recipe> recipes;
    private RecyclerView rvHome;
    private HomeAdapter homeAdapter;
    private Button jG;
    private User user;
    private SharedPreferences pref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_group);

        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.action_bar);

        jG = findViewById(R.id.joinGroup);
        jG.setOnClickListener(this);

        pref = getSharedPreferences("user_info", MODE_PRIVATE);
        int userId = pref.getInt("UserId", 0);

        if (userId != 0) {
            APIService service = RetrofitClient.getRetrofitInstance().create(APIService.class);
            Call<User> call1 = service.getUser(userId);
            call1.enqueue(new Callback<User>() {
                @Override
                public void onResponse(Call<User> call, Response<User> response) {
                    if (response.isSuccessful()) {
                        user = response.body();
                    }
                }

                @Override
                public void onFailure(Call<User> call, Throwable t) {
                    System.out.println("Fail to get user. redirect to login");
                }
            });
        }

        Intent intent = getIntent();
        int groupId = intent.getIntExtra("GroupId",1);

        UserGroup ug = new UserGroup();
        ug.setGroupId(groupId);

        ug.setUserId(userId);

        APIService service = RetrofitClient.getRetrofitInstance().create(APIService.class);
        Call<Group> call = service.getGroup(ug);



        call.enqueue(new Callback<Group>() {
            @Override
            public void onResponse(Call<Group> call, Response<Group> response) {
                group = response.body();

                TextView groupName = findViewById(R.id.viewgroupName);
                TextView groupDesc = findViewById(R.id.viewgroupDesc);
                TextView groupDate = findViewById(R.id.viewgroupDate);
                TextView noRecipeMsg = findViewById(R.id.noRecipeMsg);

                groupName.setText(group.getGroupName());
                groupDesc.setText(group.getDescription());
                groupDate.setText("Created on " + group.getDateCreated().toString());

                if (group.isJoined())
                {
                    jG.setText("Leave Group");
                }

                new DownloadImageTask((ImageView) findViewById(R.id.viewgroupImage))
                        .execute(group.getGroupPhoto());

                List<RecipeGroup> rg = group.getRecipeGroupList().getRecipegrouplist();
                if (rg.size() > 0)
                {
                    noRecipeMsg.setText("");
                    recipes = new ArrayList<>();
                    for (RecipeGroup rgg : rg)
                    {
                        recipes.add(rgg.getRecipe());
                    }
                    // binding adpater and layout manager with steps recyclerview
                    rvHome = (RecyclerView) findViewById(R.id.ViewGroupRecycler);
                    homeAdapter = new HomeAdapter(recipes, ViewGroupActivity.this, user);

                    rvHome.setAdapter(homeAdapter);
                    LinearLayoutManager lym_rs = new LinearLayoutManager(ViewGroupActivity.this);
                    lym_rs.setStackFromEnd(false);
                    rvHome.setLayoutManager(lym_rs);
                    rvHome.addItemDecoration(new DividerItemDecoration(ViewGroupActivity.this, DividerItemDecoration.VERTICAL));

                }
                else
                {
                    noRecipeMsg.setText("Nothing to show at this moment");
                }

            }

            @Override
            public void onFailure(Call<Group> call, Throwable t) {
                System.out.println(t.getMessage());
                Toast.makeText(ViewGroupActivity.this, "Unable to load recipes", Toast.LENGTH_SHORT).show();
            }
        });



        /*Button test = findViewById(R.id.test);
        test.setOnClickListener(this);*/

        ImageButton home = findViewById(R.id.refreshHome);
        home.setOnClickListener(this);

        ImageButton groups = findViewById(R.id.groups);
        groups.setOnClickListener(this);

        ImageButton myProfile = findViewById(R.id.myProfile);
        myProfile.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        int id = view.getId();

        if (id == R.id.joinGroup) {
            if (pref.getInt("UserId", 0) != 0) {
                JoinGroupTask.JoinGroup(group.getGroupId(), user.getId(), this, jG);
            }
            else {
                Toast.makeText(getApplicationContext(), "Need to login to join group", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(this, Login.class);
                startActivity(intent);
            }
        }

        /*if (id == R.id.test) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }*/

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

        if (id == R.id.myProfile) {
            if (pref.getInt("UserId", 0) == 0) {
                Intent intent = new Intent(this, Login.class);
                startActivity(intent);
            }
            else {
                Intent intent = new Intent(this, ViewUserProfile.class);
                startActivity(intent);
            }
        }
    }
}