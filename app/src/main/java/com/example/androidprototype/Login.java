package com.example.androidprototype;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.androidprototype.model.User;
import com.example.androidprototype.model.UserJson;
import com.example.androidprototype.service.APIService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Body;

public class Login extends AppCompatActivity {

    private User user;
    private APIService service;
    private EditText etEmail;
    private EditText etPassword;
    private Button btnLogin;
    private UserJson validate;
    private String password;
    private String email;
    private boolean login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btnLogin = findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (detailsEntered()) {
                    validateUser();
                }
            }
        });
    }

    public boolean detailsEntered() {
        etEmail = findViewById(R.id.etLoginEmail);
        etPassword = findViewById(R.id.etLoginPassword);

        email = etEmail.getText().toString().trim();
        password = etPassword.getText().toString().trim();

        if (email != null && password != null) {
            return true;
        }
        else { return false; }
    }

    public void validateUser() {
        service = RetrofitClient.getRetrofitInstance().create(APIService.class);

        UserJson validate = new UserJson();
        validate.setEmail(email);
        validate.setPassword(password);

        Call<User> call = service.validateUser(validate);

        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful()) {
                    if (response.body().getEmail() != null) {
                        SharedPreferences pref = getSharedPreferences("user_info", MODE_PRIVATE);
                        SharedPreferences.Editor editor = pref.edit();
                        
                        editor.putString("Email", email);
                        editor.putString("Username", response.body().getUsername());
                        editor.putInt("UserId", response.body().getId());
                        editor.commit();

                        finish();
                    }
                    else {
                        Toast.makeText(getApplicationContext(), "Please login again", Toast.LENGTH_LONG);
                    }
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Please login again", Toast.LENGTH_LONG);
            }
        });
    }
}