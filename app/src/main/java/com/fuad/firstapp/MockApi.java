package com.fuad.firstapp;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface MockApi {
    @GET("/post")
    Call<List<Post>> getPosts();
}
