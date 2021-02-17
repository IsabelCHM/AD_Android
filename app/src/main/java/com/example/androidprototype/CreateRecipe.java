package com.example.androidprototype;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.androidprototype.adpater.RecipeIngredientAdapter;
import com.example.androidprototype.adpater.RecipeStepAdapter;
import com.example.androidprototype.model.ImgBB;
import com.example.androidprototype.model.RecipeIngredientsJson;
import com.example.androidprototype.model.RecipeJson;

import com.example.androidprototype.model.RecipeStepsJson;
import com.example.androidprototype.model.RecipeTag;
import com.example.androidprototype.model.RecipeTagList;
import com.example.androidprototype.model.Tag;
import com.example.androidprototype.model.TagList;
import com.example.androidprototype.service.APIService;
import com.example.androidprototype.service.ImgService;
import com.example.androidprototype.service.ListItemClickListener;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreateRecipe extends AppCompatActivity
        implements View.OnClickListener, ListItemClickListener {
    private Button addStepBtn;
    private Button addIngredientBtn;
    private Button createRecipeBtn;
    private Button mins15;
    private Button mins15_30;
    private Button mins30_60;
    private Button mins60Plus;
    private Button generateATag;
    //private Button deleteIngredientBtn;
    private int durationFlag;
    private boolean isClicked;
    private String coverImgUrl;
    private String stepImgUrl;
    private ArrayList<RecipeStepsJson> recipeStepsList;
    private ArrayList<RecipeIngredientsJson> recipeIngredientsList;
    private List<RecipeTag> tags;
    private RecyclerView rvRecipeStep;
    private RecyclerView rvRecipeIngredient;
    private RecipeStepAdapter rsAdapter;
    private RecipeIngredientAdapter riAdapter;

    private ImageView recipeCover;
    private EditText titleET;
    private EditText desET;
    private EditText servingSizeET;
    private EditText durationET;
<<<<<<< HEAD
    private EditText caloriesET;
=======
    private TextView allergenWarnings;
>>>>>>> 452973e2d10d03f6bacb35a14bb675075756e802

    private APIService service;
    private ImgService imgService;
    private final int SELECT_COVER_PHOTO = 1;
    private final int SELECT_STEP_PHOTO = 2;
    private int stepImgPos = -1;
    private String Document_img1="";
    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSIONS_STORAGE = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_recipe);

        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.action_bar);


        // Retrofit Service
        service = RetrofitClient.getRetrofitInstance().create(APIService.class);
        imgService = ImgClient.getRetrofitInstance().create(ImgService.class);

        recipeCover = (ImageView) findViewById(R.id.recipeCover);
        titleET = (EditText) findViewById(R.id.recipeTitle);
        desET = (EditText) findViewById(R.id.description);
        servingSizeET = (EditText) findViewById(R.id.servingSize);
        caloriesET = (EditText) findViewById(R.id.recipeCalories);
        durationET = (EditText) findViewById(R.id.duration);
        allergenWarnings = (TextView) findViewById(R.id.allergens);

        mins15 = (Button) findViewById(R.id.mins15);
        mins15_30 = (Button) findViewById(R.id.mins15_30);
        mins30_60 = (Button) findViewById(R.id.mins30_60);
        mins60Plus = (Button) findViewById(R.id.mins60plus);
        generateATag = (Button) findViewById(R.id.generateATags);

        mins15.setOnClickListener(this);
        mins15_30.setOnClickListener(this);
        mins30_60.setOnClickListener(this);
        mins60Plus.setOnClickListener(this);
        recipeCover.setOnClickListener(this);
        generateATag.setOnClickListener(this);

        // Initiate a new list for recipe steps
        RecipeStepsJson recipeSteps = new RecipeStepsJson();
        recipeSteps.setStepNumber(1);
        recipeStepsList = new ArrayList<>();

        recipeStepsList.add(recipeSteps);

        // Initiate a new list for recipe ingredients
        RecipeIngredientsJson recipeIngredientsJson = new RecipeIngredientsJson();
        //recipeIngredients.setRecipeIngredientsId(1);
        recipeIngredientsList = new ArrayList<>();

        recipeIngredientsList.add(recipeIngredientsJson);

        // binding adpater and layout manager with steps recyclerview
        rvRecipeStep = (RecyclerView) findViewById(R.id.rvRecipeStep);
        rsAdapter = new RecipeStepAdapter(this, recipeStepsList);

        rvRecipeStep.setAdapter(rsAdapter);
        LinearLayoutManager lym_rs = new LinearLayoutManager(this);
        lym_rs.setStackFromEnd(true);
        rvRecipeStep.setLayoutManager(lym_rs);

        // binding adapter and layout manager with ingredients recyclerview
        rvRecipeIngredient = (RecyclerView) findViewById(R.id.rvIngredient);
        riAdapter = new RecipeIngredientAdapter(recipeIngredientsList);

        rvRecipeIngredient.setAdapter(riAdapter);
        LinearLayoutManager lym_ri = new LinearLayoutManager(this);
        lym_ri.setStackFromEnd(true);
        rvRecipeIngredient.setLayoutManager(lym_ri);

        addStepBtn = findViewById(R.id.addStep);
        addIngredientBtn = findViewById(R.id.addIngredient);
        createRecipeBtn = findViewById(R.id.createRecipe);
        //deleteIngredientBtn = findViewById(R.id.deleteIngredient);

        addStepBtn.setOnClickListener(this);
        addIngredientBtn.setOnClickListener(this);
        createRecipeBtn.setOnClickListener(this);
        //deleteIngredientBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();

        switch (id) {
            case R.id.recipeCover:
                selectCoverImg();
                break;
            case R.id.addStep:
                RecipeStepsJson newRecipeStep = new RecipeStepsJson();
                newRecipeStep.setStepNumber(recipeStepsList.size() + 1);
                rsAdapter.addStep(newRecipeStep);
                rvRecipeStep.scrollToPosition(recipeStepsList.size() - 1);
                break;
            case R.id.addIngredient:
                RecipeIngredientsJson newRecipeIngredient = new RecipeIngredientsJson();
                //newRecipeIngredient.setRecipeIngredientsId(recipeIngredientsList.size()+1);
                riAdapter.addStep(newRecipeIngredient);
                rvRecipeIngredient.scrollToPosition(recipeStepsList.size() - 1);
                break;
            case R.id.mins15:
                selectDuration(mins15, isClicked, durationFlag);
                break;
            case R.id.mins15_30:
                selectDuration(mins15_30, isClicked, durationFlag);
                break;
            case R.id.mins30_60:
                selectDuration(mins30_60, isClicked, durationFlag);
                break;
            case R.id.mins60plus:
                selectDuration(mins60Plus, isClicked, durationFlag);
                break;
            case R.id.createRecipe:
                RecipeJson newRecipe = new RecipeJson();
                newRecipe.setMainMediaUrl(coverImgUrl);
                newRecipe.setTitle(titleET.getText().toString());
                newRecipe.setDescription(desET.getText().toString());
                newRecipe.setCalories(Integer.parseInt(caloriesET.getText().toString()));
                newRecipe.setRecipeIngredientsList(riAdapter.getRecipeIngredientList());
                newRecipe.setRecipeStepsList(rsAdapter.getRecipeStepsList());
                newRecipe.setServingSize(Integer.parseInt(servingSizeET.getText().toString()));
                newRecipe.setDurationInMins(getDuration(durationFlag));

                Call<ResponseBody> call = service.saveRecipe(newRecipe);
                call.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if (response.isSuccessful()) {
                            Intent intent = new Intent(getApplicationContext(), SuccessPage.class);
                            startActivity(intent);
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Toast.makeText(CreateRecipe.this, "Unable to save recipe", Toast.LENGTH_SHORT).show();
                    }
                });
                break;
            case R.id.generateATags:
                Call<RecipeTagList> call1 = service.generateATags(riAdapter.getRecipeIngredientList());
                call1.enqueue(new Callback<RecipeTagList>() {
                    @Override
                    public void onResponse(Call<RecipeTagList> call, Response<RecipeTagList> response) {
                        if (response.isSuccessful()) {
                            tags = response.body().getRecipeTags();
                            String warnings = "";
                            for (RecipeTag r : tags)
                            {
                                if (r.getAllergenTag())
                                {
                                    warnings += r.getTagXXId().getWarning();
                                }
                            }
                            if (!warnings.isEmpty())
                            {
                                allergenWarnings.setText("May cause " + warnings);
                            }
                            else {
                                allergenWarnings.setText("No allergy warnings");
                            }

                            System.out.println("Reached back");
                        }
                    }

                    @Override
                    public void onFailure(Call<RecipeTagList> call, Throwable t) {
                        Toast.makeText(CreateRecipe.this, "Unable to generate tags", Toast.LENGTH_SHORT).show();
                    }
                });
                break;

        }

    }

    private void selectCoverImg() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, SELECT_COVER_PHOTO);
    }

    private void selectStepImg(int position) {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        stepImgPos = position;
        startActivityForResult(intent, SELECT_STEP_PHOTO);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            verifyStoragePermissions(CreateRecipe.this);
            Uri selectedImage = data.getData();
            String[] filePath = { MediaStore.Images.Media.DATA };
            Cursor c = getContentResolver().query(selectedImage,filePath, null, null, null);
            c.moveToFirst();
            int columnIndex = c.getColumnIndex(filePath[0]);
            String picturePath = c.getString(columnIndex);
            c.close();
            Bitmap thumbnail = (BitmapFactory.decodeFile(picturePath));
            File imgToUpload = new File(picturePath);
            if (requestCode == SELECT_COVER_PHOTO) {
                //thumbnail = getResizedBitmap(thumbnail, 400);
                recipeCover.setImageBitmap(thumbnail);
                //String encodedImg = BitMapToString(thumbnail);
                if (imgToUpload != null) {
                    Thread bkgdThread = new Thread(new Runnable() {
                        @Override
                        public void run() {
                            uploadCoverImgToImgBB(imgToUpload);
                        }
                    });
                    bkgdThread.start();
                }
            }
            if (requestCode == SELECT_STEP_PHOTO) {
                rsAdapter.setStepImg(thumbnail, stepImgPos);
                rsAdapter.notifyDataSetChanged();
                if (imgToUpload != null) {
                    Thread bkgdThread = new Thread(new Runnable() {
                        @Override
                        public void run() {
                            uploadStepImgToImgBB(imgToUpload);
                        }
                    });
                    bkgdThread.start();
                }
            }
        }
    }

    private Bitmap getResizedBitmap(Bitmap image, int maxSize) {
        int width = image.getWidth();
        int height = image.getHeight();

        float bitmapRatio = (float)width / (float) height;
        if (bitmapRatio > 1) {
            width = maxSize;
            height = (int) (width / bitmapRatio);
        } else {
            height = maxSize;
            width = (int) (height * bitmapRatio);
        }
        return Bitmap.createScaledBitmap(image, width, height, true);
    }

    private String BitMapToString(Bitmap img) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        img.compress(Bitmap.CompressFormat.JPEG, 60, baos);
        byte[] b = baos.toByteArray();
        Document_img1 = Base64.encodeToString(b, Base64.DEFAULT);
        return Document_img1;
    }

    private void uploadCoverImgToImgBB(File img) {
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
                if (response.isSuccessful()){
                    coverImgUrl = response.body().getImgBBData().getUrl();
                    Toast.makeText(CreateRecipe.this, "Photo uploaded successfully", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ImgBB> call, Throwable t) {
                Toast.makeText(CreateRecipe.this, "Unable to upload photo", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void uploadStepImgToImgBB(File img) {
        RequestBody requestBody = RequestBody.create(MediaType.parse("image/*"), img);
        MultipartBody.Part part = MultipartBody.Part.createFormData("image", img.getName(), requestBody);

        Call<ImgBB> call = imgService.uploadImg(ImgClient.myKey, part);
        call.enqueue(new Callback<ImgBB>() {
            @Override
            public void onResponse(Call<ImgBB> call, Response<ImgBB> response) {
                if (response.isSuccessful()){
                    stepImgUrl = response.body().getImgBBData().getUrl();
                    rsAdapter.setStepImgUrl(stepImgUrl, stepImgPos);
                    Toast.makeText(CreateRecipe.this, "Photo uploaded successfully", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ImgBB> call, Throwable t) {
                Toast.makeText(CreateRecipe.this, "Unable to upload photo", Toast.LENGTH_SHORT).show();
            }
        });
    }


    public void resetDuration(int durationFlag) {
        switch (durationFlag) {
            case R.id.mins15:
                //mins15.setBackgroundColor(R.color.purple_test);
                mins15.setBackgroundColor(getResources().getColor(R.color.purple_test));
                break;
            case R.id.mins15_30:
                //mins15_30.setBackgroundColor(R.color.purple_test);
                mins15_30.setBackgroundColor(getResources().getColor(R.color.purple_test));
                break;
            case R.id.mins30_60:
                //mins30_60.setBackgroundColor(R.color.purple_test);
                mins30_60.setBackgroundColor(getResources().getColor(R.color.purple_test));
                break;
            case R.id.mins60plus:
                //mins60Plus.setBackgroundColor(R.color.purple_test);
                mins60Plus.setBackgroundColor(getResources().getColor(R.color.purple_test));
                break;
        }
    }

    public void selectDuration(Button btn, Boolean isClicked, int durationFlag) {
        if (isClicked) {
            resetDuration(durationFlag);
        }
        btn.setBackgroundColor(getResources().getColor(R.color.checked));
        this.durationFlag = btn.getId();
        this.isClicked = true;
    }

    public int getDuration(int durationFlag) {
        int duration = 0;

        if (durationFlag == R.id.mins15)
            duration = 1;
        else if (durationFlag == R.id.mins15_30)
            duration = 2;
        else if (durationFlag == R.id.mins30_60)
            duration = 3;
        else if (durationFlag == R.id.mins60plus)
            duration = 4;

        return duration;
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

    @Override
    public void onListItemClick(int position) {
        selectStepImg(position);
    }
}