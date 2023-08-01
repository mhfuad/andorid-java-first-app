package com.fuad.firstapp;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface MockApi {
    @GET("/post")
    Call<List<Post>> getPosts();

    @POST("/post")
    Call<Post> savePost(@Body PostRequest postRequest);
}
