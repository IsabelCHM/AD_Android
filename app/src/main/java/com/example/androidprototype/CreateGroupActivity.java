package com.example.androidprototype;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.androidprototype.model.Group;
import com.example.androidprototype.model.ImgBB;
import com.example.androidprototype.model.Tag;
import com.example.androidprototype.model.User;
import com.example.androidprototype.model.groupUserJson;
import com.example.androidprototype.service.APIService;
import com.example.androidprototype.service.ImgService;

import java.io.File;
import java.util.ArrayList;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.androidprototype.CreateRecipe.verifyStoragePermissions;

public class CreateGroupActivity extends AppCompatActivity
        implements View.OnClickListener{

    private EditText nameET;
    private EditText descET;
    private ImageView photoIV;
    private EditText tagsET;

    private ArrayList<Tag> tags;
    private String img_url;

    private APIService service;
    private ImgService imgService;
    private final int SELECT_PHOTO = 1;
    private String Document_img1="";
    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSIONS_STORAGE = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_group);

        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.action_bar);

        //titleET = (EditText) findViewById(R.id.recipeTitle);

        // Retrofit Service
        service = RetrofitClient.getRetrofitInstance().create(APIService.class);
        imgService = ImgClient.getRetrofitInstance().create(ImgService.class);

        nameET = (EditText) findViewById(R.id.cgrpName);
        descET = (EditText) findViewById(R.id.cgrpDesc);
        photoIV = (ImageView) findViewById(R.id.cgrpPhoto);
        tagsET = (EditText) findViewById(R.id.cgrpTags);

        Button submit = findViewById(R.id.cGrpSubmit);
        submit.setOnClickListener(this);

        /*Button test = findViewById(R.id.test);
        test.setOnClickListener(this);*/

        ImageButton home = findViewById(R.id.refreshHome);
        home.setOnClickListener(this);

        ImageButton groups = findViewById(R.id.groups);
        groups.setOnClickListener(this);

        ImageButton myProfile = findViewById(R.id.myProfile);
        myProfile.setOnClickListener(this);

        photoIV.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {

        int id = view.getId();

        if(id == R.id.cgrpPhoto){
            selectImg();
        }
        /*if (id == R.id.test) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }*/

        if (id == R.id.refreshHome) {
            Intent intent = new Intent(this, HomeActivity.class);
            intent.setAction("REFRESH");
            startActivity(intent);
        }

        if (id == R.id.groups) {
            Intent intent = new Intent(this, ListGroupActivity.class);
            intent.setAction("view");
            startActivity(intent);
        }

        if (id == R.id.cGrpSubmit) {
            CreateGroup();
        }

    }

    public void CreateGroup() {
        APIService service = RetrofitClient.getRetrofitInstance().create(APIService.class);

        Group newGroup = new Group();
        String grpName = nameET.getText().toString();
        if (grpName.trim().isEmpty())
        {
            Toast.makeText(CreateGroupActivity.this, "Group name cannot be blank", Toast.LENGTH_SHORT).show();
            return;
        }
        newGroup.setGroupName(grpName);
        newGroup.setDescription(descET.getText().toString());
        newGroup.setGroupPhoto(img_url);

        String tags = tagsET.getText().toString();

        User u = new User();
        u.setId(1);

        Call<Group> call = service.saveGroup(new groupUserJson(u, newGroup, tags));
        call.enqueue(new Callback<Group>() {
            @Override
            public void onResponse(Call<Group> call, Response<Group> response) {
                if(response.isSuccessful()) {
                    Group rg = response.body();
                    Toast.makeText(CreateGroupActivity.this, "Group created successfully", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(CreateGroupActivity.this, ViewGroupActivity.class);
                    intent.putExtra("GroupId",rg.getGroupId());
                    startActivity(intent);
                }
            }

            @Override
            public void onFailure(Call<Group> call, Throwable t) {
                Toast.makeText(CreateGroupActivity.this, "Unable to create group", Toast.LENGTH_SHORT).show();
            }
        });


    }

    private void selectImg() {
        Intent intent = new   Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, SELECT_PHOTO);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == SELECT_PHOTO) {
                verifyStoragePermissions(CreateGroupActivity.this);
                Uri selectedImage = data.getData();
                String[] filePath = { MediaStore.Images.Media.DATA };
                Cursor c = getContentResolver().query(selectedImage,filePath, null, null, null);
                c.moveToFirst();
                int columnIndex = c.getColumnIndex(filePath[0]);
                String picturePath = c.getString(columnIndex);
                c.close();
                Bitmap thumbnail = (BitmapFactory.decodeFile(picturePath));
                //thumbnail = getResizedBitmap(thumbnail, 400);
                //Log.w("path of image from gallery......******************.........", picturePath+"");
                photoIV.setImageBitmap(thumbnail);
                //String encodedImg = BitMapToString(thumbnail);
                File imgToUpload = new File(picturePath);
                Thread bkgdThread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        UploadToImgBB(imgToUpload);
                    }
                });
                bkgdThread.start();

            }
        }
    }

    private void UploadToImgBB(File img) {
        RequestBody requestBody = RequestBody.create(MediaType.parse("image/*"), img);
        MultipartBody.Part part = MultipartBody.Part.createFormData("image", img.getName(), requestBody);

        /*Call<ImgBBJson> call = imgService.uploadImg(ImgClient.myKey, encodedImg);
        call.enqueue(new Callback<ImgBBJson>() {
            @Override
            public void onResponse(Call<ImgBBJson> call, Response<ImgBBJson> response) {
                String img_url = response.body().getUrl();
                Toast.makeText(CreateRecipe.this, "Photo uploaded successfully", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<ImgBBJson> call, Throwable t) {
                Toast.makeText(CreateRecipe.this, "Unable to upload photo", Toast.LENGTH_SHORT).show();
            }
        });*/

        Call<ImgBB> call = imgService.uploadImg(ImgClient.myKey, part);
        call.enqueue(new Callback<ImgBB>() {
            @Override
            public void onResponse(Call<ImgBB> call, Response<ImgBB> response) {
                img_url = response.body().getImgBBData().getUrl();
                Toast.makeText(CreateGroupActivity.this, "Photo uploaded successfully", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<ImgBB> call, Throwable t) {
                Toast.makeText(CreateGroupActivity.this, "Photo uploaded successfully", Toast.LENGTH_SHORT).show();
            }
        });

    }



    public static void verifyStoragePermissions(Activity activity) {
        // Check if we have write permission
        int permission = ActivityCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE);

        if (permission != PackageManager.PERMISSION_GRANTED) {
            // We don't have permission so prompt the user
            ActivityCompat.requestPermissions(
                    activity,
                    PERMISSIONS_STORAGE,
                    REQUEST_EXTERNAL_STORAGE
            );
        }
    }

}