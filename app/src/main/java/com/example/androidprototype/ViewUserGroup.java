package com.example.androidprototype;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.example.androidprototype.model.User;
import com.example.androidprototype.model.UserGroupList;
import com.example.androidprototype.APIService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ViewUserGroup extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_user_group);

        APIService service = RetrofitClient.getRetrofitInstance().create(APIService.class);
        Call<UserGroupList> call = service.getUserGroupList(1);

        call.enqueue(new Callback<UserGroupList>() {
            @Override
            public void onResponse(Call<UserGroupList> call, Response<UserGroupList> response) {
                UserGroupList user = response.body();
                System.out.println(user.getUsergrouplist().get(0).getUser().getFirstName());

            }

            @Override
            public void onFailure(Call<UserGroupList> call, Throwable t) {
                System.out.println(t.getMessage());
                Toast.makeText(ViewUserGroup.this, "Unable to load recipe", Toast.LENGTH_SHORT).show();
            }
        });
    }
}