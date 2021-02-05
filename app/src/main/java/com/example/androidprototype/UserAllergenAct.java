package com.example.androidprototype;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.androidprototype.APIService;
import com.example.androidprototype.model.User;
import com.example.androidprototype.model.UserAllergenList;
import com.example.androidprototype.model.UserGroupList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserAllergenAct extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_allergen);

        EditText etUserId = findViewById(R.id.etUserAllergenGetId);
        Button btnGetUserAllergen = findViewById(R.id.btnGetUserAllergenFromDB);

        btnGetUserAllergen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int userId = Integer.parseInt(etUserId.getText().toString());

                APIService service = RetrofitClient.getRetrofitInstance().create(com.example.androidprototype.APIService.class);
                Call<UserAllergenList> call = service.getUserAllergenList(userId);

                call.enqueue(new Callback<UserAllergenList>() {
                    @Override
                    public void onResponse(Call<UserAllergenList> call, Response<UserAllergenList> response) {
                        System.out.println("In response");
                    }

                    @Override
                    public void onFailure(Call<UserAllergenList> call, Throwable t) {
                        System.out.println("In OnFailure");
                    }
                });
            }
        });
    }
}