package com.ivy.bakingapp.data.remote;

/**
 * Created by Ivoline-Clarisse on 10/28/2017.
 */

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RecipeClient {

    private static Retrofit retrofit = null;


    public static Retrofit getClient(String baseUrl) {

//        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
//        // set your desired log level
//        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
//
//        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
//        // add your other interceptors …
//
//        // add logging as last interceptor
//        httpClient.addInterceptor(logging);  // <-- this is the important line!

        if (retrofit==null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    //.client(httpClient.build())
                    .build();
        }
        return retrofit;
    }
}

