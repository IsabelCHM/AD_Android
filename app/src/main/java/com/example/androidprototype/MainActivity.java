package com.example.androidprototype;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
    implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.action_bar);

        Button btn1 = findViewById(R.id.createRecipe);
        Button btn2 = findViewById(R.id.reviewRecipe);
        Button btn3 = findViewById(R.id.btnDeleteRecipe1);
        Button btn4 = findViewById(R.id.getUser);
        Button btn5 = findViewById(R.id.btnGetUserAllergen);
        Button btn6 = findViewById(R.id.createRecipeX);
        Button btn7 = findViewById(R.id.btnUserProfile);
        Button btn8 = findViewById(R.id.btnLoginMain);
        Button btn9 = findViewById(R.id.btnCheckLogIn);
        Button btn10 = findViewById(R.id.btnCheckLogOut);


        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);
        btn5.setOnClickListener(this);
        btn6.setOnClickListener(this);
        btn7.setOnClickListener(this);
        btn8.setOnClickListener(this);
        btn9.setOnClickListener(this);
        btn10.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();

        if (id == R.id.createRecipe) {
            Intent intent = new Intent(this, CreateRecipeZ.class);
            startActivity(intent);
        }

        if (id == R.id.reviewRecipe) {
            Intent intent = new Intent(this, ViewRecipe.class);
            startActivity(intent);
        }

        if (id == R.id.btnDeleteRecipe1) {
            Intent intent = new Intent(this, DeleteRecipe.class);
            startActivity(intent);
        }

        if (id == R.id.getUser) {
            Intent intent = new Intent(this, ViewUserGroup.class);
            startActivity(intent);
        }

        if (id == R.id.btnGetUserAllergen) {
            Intent intent = new Intent(this, UserAllergenAct.class);
            startActivity(intent);
        }

        if (id == R.id.createRecipeX) {
            Intent intent = new Intent(this, CreateRecipe.class);
            intent.setAction("CREATE_RECIPE");
            startActivity(intent);
        }

        if (id == R.id.btnUserProfile) {
            Intent intent = new Intent(this, ViewUserProfile.class);
            startActivity(intent);
        }

        if (id == R.id.btnLoginMain) {
            Intent intent = new Intent(this, Login.class);
            startActivity(intent);
        }

        if (id == R.id.btnCheckLogIn) {
            SharedPreferences pref = getSharedPreferences("user_info", MODE_PRIVATE);
            String test = "userId is: " + pref.getInt("UserId", 0);
            Toast.makeText(this, test, Toast.LENGTH_LONG).show();
        }

        if (id == R.id.btnCheckLogOut) {
            SharedPreferences pref = getSharedPreferences("user_info", MODE_PRIVATE);
            SharedPreferences.Editor editor = pref.edit();
            editor.clear();
            editor.commit();
            String test = "userId is: " + pref.getInt("UserId", 0);
            Toast.makeText(this, test, Toast.LENGTH_LONG).show();
        }
    }
}