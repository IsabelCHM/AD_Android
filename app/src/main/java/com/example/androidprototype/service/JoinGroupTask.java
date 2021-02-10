package com.example.androidprototype.service;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.example.androidprototype.CreateRecipe;
import com.example.androidprototype.RetrofitClient;
import com.example.androidprototype.SuccessPage;
import com.example.androidprototype.model.UserGroup;
import com.example.androidprototype.service.APIService;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class JoinGroupTask {

    public static void JoinGroup(int groupId, int userId, Context context) {
        UserGroup ug = new UserGroup(groupId, userId, false);

        APIService service = RetrofitClient.getRetrofitInstance().create(APIService.class);
        Call<ResponseBody> call = service.joinGroup(ug);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if(response.isSuccessful()) {
                    Toast.makeText(context, "Group joined", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(context, "Unable to join group", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
