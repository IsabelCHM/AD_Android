package com.example.androidprototype;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ImgClient {
    private static Retrofit retrofit;
    private static OkHttpClient okHttpClient = new OkHttpClient.Builder().build();
    //Define the base URL//

    //private static final String BASE_URL = "https://jsonplaceholder.typicode.com";
    private static final String BASE_URL = "https://api.imgbb.com";
    public static final String myKey = "3a9c449c9047b1b972d5299cf6c77e29";


//Create the Retrofit instance//

    public static Retrofit getRetrofitInstance() {
        //okHttpClient.interceptors().add(new RedirectInterceptor());
        if (retrofit == null) {
            Gson gson = new GsonBuilder()
                    .registerTypeAdapterFactory(new TypeAdapterFactory())
                    .create();

            retrofit = new retrofit2.Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .client(okHttpClient)
                    .build();
        }
        return retrofit;
    }
}
