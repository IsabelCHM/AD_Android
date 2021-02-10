package com.example.androidprototype;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity
    implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn1 = findViewById(R.id.createRecipe);
        Button btn2 = findViewById(R.id.reviewRecipe);
        Button btn3 = findViewById(R.id.btnDeleteRecipe1);
        Button btn4 = findViewById(R.id.getUser);
        Button btn5 = findViewById(R.id.btnGetUserAllergen);
        Button btn6 = findViewById(R.id.createRecipeX);
        Button btn7 = findViewById(R.id.btnUserProfile);


        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);
        btn5.setOnClickListener(this);
        btn6.setOnClickListener(this);
        btn7.setOnClickListener(this);
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
            startActivity(intent);
        }

        if (id == R.id.btnUserProfile) {
            Intent intent = new Intent(this, ViewUserProfile.class);
            startActivity(intent);
        }
    }
}