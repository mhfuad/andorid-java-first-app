package com.fuad.firstapp;

import static com.fuad.firstapp.MainActivity.API_URL;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitInstance {
    public static RetrofitInstance instance;
    MockApi mockApi;

    RetrofitInstance(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        mockApi = retrofit.create(MockApi.class);
    }

    public static RetrofitInstance getInstance(){
        if (instance == null){
            instance = new RetrofitInstance();
        }
        return instance;
    }
}
