package com.example.androidprototype.service;

import com.example.androidprototype.model.ImgBB;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

public interface ImgService {
    /*@FormUrlEncoded
    @POST("/upload")
    Call<ImgBBJson> uploadImg(@Query("key") String myKey, @Field("image") String necodedImage);*/

    @Multipart
    @POST("/1/upload")
    Call<ImgBB> uploadImg(@Query("key") String myKey, @Part MultipartBody.Part img);

}
