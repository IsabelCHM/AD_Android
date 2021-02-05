package com.example.androidprototype;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.example.androidprototype.model.User;
import com.example.androidprototype.model.Recipe;
import com.example.androidprototype.model.RecipeList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ViewUser extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_user);

        APIService service = RetrofitClient.getRetrofitInstance().create(APIService.class);
        Call<User> call = service.getUser(1);

        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                User user = response.body();
                System.out.println(user.getRecipes().getRecipelist().get(0).getTitle());

            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                System.out.println(t.getMessage());
                Toast.makeText(ViewUser.this, "Unable to load recipe", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
