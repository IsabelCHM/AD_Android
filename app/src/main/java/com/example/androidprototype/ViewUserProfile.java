package com.example.androidprototype;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.androidprototype.service.APIService;
import com.example.androidprototype.adpater.RecipeUserProfileAdaptor;
import com.example.androidprototype.model.Recipe;
import com.example.androidprototype.model.User;
import com.example.androidprototype.service.DownloadImageTask;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ViewUserProfile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_user_profile);

        EditText etUserId = findViewById(R.id.etGetUserProfileId);
        Button btGetUserProfile = findViewById(R.id.btnGetUserProfile);
        TextView tvFullname = findViewById(R.id.tvFullName);
        TextView tvNoOfRecipe = findViewById(R.id.tvNoOfRecipes);

        btGetUserProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int userId = Integer.parseInt(etUserId.getText().toString());

                APIService service = RetrofitClient.getRetrofitInstance().create(APIService.class);
                Call<User> call = service.getUser(userId);

                call.enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {
                        String firstName = response.body().getFirstName();
                        String lastName = response.body().getLastName();
                        String userName = response.body().getUsername();
                        int noOfRecipes = response.body().getRecipes().getRecipelist().size();

                        tvFullname.setText("Name: " + firstName + " " +  lastName);
                        tvFullname.setText("Username: " + userName);
                        tvNoOfRecipe.setText("Recipes Created: " + Integer.toString(noOfRecipes));

                        List<Recipe> recipeList = response.body().getRecipes().getRecipelist();

                        if (recipeList != null) {
                            RecipeUserProfileAdaptor adaptor = new RecipeUserProfileAdaptor(ViewUserProfile.this, 0);
                            adaptor.setData(recipeList);

                            ListView listview = findViewById(R.id.lvRecipes);

                            if (listview != null) {
                                listview.setAdapter(adaptor);
                                listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                    @Override
                                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                        int recipeId = recipeList.get(i).getId();
                                        Intent intent = new Intent(ViewUserProfile.this, ViewRecipe.class);
                                        intent.putExtra("recipeId", recipeId);
                                        startActivity(intent);
                                    }
                                });
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<User> call, Throwable t) {
                        System.out.println("onFailure");
                    }
                });
            }
        });
    }
}