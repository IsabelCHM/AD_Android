package com.example.androidprototype;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DeleteRecipe extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_recipe);

        EditText etRecipieId = findViewById(R.id.etDeleteRecipeId);
        Button btnDeleteRecipe = findViewById(R.id.btnDeleteRecipe);

        btnDeleteRecipe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int id = Integer.parseInt(etRecipieId.getText().toString());

                APIService service = RetrofitClient.getRetrofitInstance().create(APIService.class);
                Call<ResponseBody> call = service.deleteRecipe(id);

                call.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        boolean status = response.isSuccessful();
                        System.out.println(status);
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent);
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        System.out.println("Fail to delete");
                    }
                });

            }
        });
    }
}