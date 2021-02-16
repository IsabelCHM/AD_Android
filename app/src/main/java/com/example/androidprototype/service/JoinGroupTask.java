package com.example.androidprototype.service;

import android.content.Context;
import android.content.Intent;
import android.widget.Button;
import android.widget.Toast;

import com.example.androidprototype.CreateRecipe;
import com.example.androidprototype.RetrofitClient;
import com.example.androidprototype.SuccessPage;
import com.example.androidprototype.model.UserGroup;
import com.example.androidprototype.model.booleanJson;
import com.example.androidprototype.service.APIService;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class JoinGroupTask {

    public static void JoinGroup(int groupId, int userId, Context context, Button jG) {
        UserGroup ug = new UserGroup(groupId, userId, false);

        APIService service = RetrofitClient.getRetrofitInstance().create(APIService.class);
        Call<booleanJson> call = service.joinGroup(ug);
        call.enqueue(new Callback<booleanJson>() {
            @Override
            public void onResponse(Call<booleanJson> call, Response<booleanJson> response) {
                if(response.isSuccessful()) {
                    booleanJson isJoinedd = response.body();
                    boolean isJoined = isJoinedd.getFlag();
                    if (isJoined)
                    {
                        jG.setText("Leave Group");
                        Toast.makeText(context, "Group joined", Toast.LENGTH_SHORT).show();
                    }
                    else if (!isJoined)
                    {
                        jG.setText("Join Group");
                        Toast.makeText(context, "Group left", Toast.LENGTH_SHORT).show();
                    }

                }
            }

            @Override
            public void onFailure(Call<booleanJson> call, Throwable t) {
                Toast.makeText(context, "Unable to join group", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
