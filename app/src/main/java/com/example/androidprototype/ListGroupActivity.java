package com.example.androidprototype;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.androidprototype.adpater.GroupAdapter;
import com.example.androidprototype.adpater.HomeAdapter;
import com.example.androidprototype.model.Group;
import com.example.androidprototype.model.GroupList;
import com.example.androidprototype.model.RecipeList;
import com.example.androidprototype.model.User;
import com.example.androidprototype.model.UserGroup;
import com.example.androidprototype.model.UserGroupList;
import com.example.androidprototype.service.APIService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListGroupActivity extends AppCompatActivity
        implements View.OnClickListener{

    private ArrayList<Group> myGroups;
    private RecyclerView rvGroup;
    private GroupAdapter groupAdapter;
    private int userId; // get from intent when coming in from userprofile
    private APIService service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_group);

        userId = getIntent().getIntExtra("userId", 0);
        service = RetrofitClient.getRetrofitInstance().create(APIService.class);

        SearchView simpleSearchView = (SearchView) findViewById(R.id.simpleSearchView);
        simpleSearchView.setIconifiedByDefault(true);
        simpleSearchView.setQueryHint("Search groups");

        simpleSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                // do something on text submit
                Intent intent1 = new Intent(ListGroupActivity.this, ListGroupActivity.class);
                intent1.setAction("SEARCH");
                intent1.putExtra("query", query);
                startActivity(intent1);

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
// do something when text changes
                return false;
            }
        });

        if (userId != 0) {
            getUserGroup(userId);
        }
        else {
            Intent intent = getIntent();
            String search = intent.getAction();
            if (search.equals("SEARCH")) {

                String query = intent.getStringExtra("query");
                System.out.println(query);

                Call<GroupList> call = service.searchGroups(query);

                TextView mG = findViewById(R.id.mG);
                mG.setText("Results");

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
                            groupAdapter = new GroupAdapter(myGroups, ListGroupActivity.this);

                            rvGroup.setAdapter(groupAdapter);
                            LinearLayoutManager lym_rs = new LinearLayoutManager(ListGroupActivity.this);
                            lym_rs.setStackFromEnd(false);
                            rvGroup.setLayoutManager(lym_rs);
                            rvGroup.addItemDecoration(new DividerItemDecoration(ListGroupActivity.this, DividerItemDecoration.VERTICAL));
                        }
                    }

                    @Override
                    public void onFailure(Call<GroupList> call, Throwable t) {
                        System.out.println(t.getMessage());
                        Toast.makeText(ListGroupActivity.this, "No recipes to show", Toast.LENGTH_SHORT).show();
                    }
                });



            }
            else {
                getUserGroup(userId);
            }
        }


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

    }

    public void getUserGroup(int userId) {
        if (userId == 0) {
            userId = 1; // change to intent for login when login is done
        }
        Call<User> call = service.getUser(userId);

        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                User user = response.body();

                List<UserGroup> ug = user.getGroups().getUsergrouplist();
                if (ug.size() > 0) {
                    myGroups = new ArrayList<>();

                    for (UserGroup u : ug) {
                        myGroups.add(u.getGroup());
                    }

                    // binding adpater and layout manager with steps recyclerview
                    rvGroup = (RecyclerView) findViewById(R.id.GroupRecycler);
                    groupAdapter = new GroupAdapter(myGroups, ListGroupActivity.this);

                    rvGroup.setAdapter(groupAdapter);
                    LinearLayoutManager lym_rs = new LinearLayoutManager(ListGroupActivity.this);
                    lym_rs.setStackFromEnd(false);
                    rvGroup.setLayoutManager(lym_rs);
                    rvGroup.addItemDecoration(new DividerItemDecoration(ListGroupActivity.this, DividerItemDecoration.VERTICAL));
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                System.out.println(t.getMessage());
                Toast.makeText(ListGroupActivity.this, "No groups to show", Toast.LENGTH_SHORT).show();
            }
        });
    }

}