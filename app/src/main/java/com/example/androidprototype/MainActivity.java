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

        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();

        if (id == R.id.createRecipe) {
            Intent intent = new Intent(this, CreateRecipe.class);
            startActivity(intent);
        }

        if (id == R.id.reviewRecipe) {
            Intent intent = new Intent(this, ViewRecipe.class);
            startActivity(intent);
        }
    }
}