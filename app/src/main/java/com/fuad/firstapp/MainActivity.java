package com.fuad.firstapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class MainActivity extends AppCompatActivity {
    public static final String API_URL = "https://64a40253c3b509573b56ea44.mockapi.io";
    //TextView textViewResult;
    RecyclerView rcvMain;
    List<Post> posts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //textViewResult = findViewById(R.id.text_view_result);
        rcvMain = findViewById(R.id.rcvMain);
        rcvMain.setLayoutManager(new LinearLayoutManager(this));

        RetrofitInstance.getInstance().mockApi.getPosts().enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                if(!response.isSuccessful()){
                    //textViewResult.setText("code: "+ response.code());
                    return;
                }

                posts = response.body();
                rcvMain.setAdapter(new PostAdapter(MainActivity.this,posts));
                for(Post post: posts){
                    String content = "";
                    content += "Id: "+ post.getId() + "\n";
                    content += "User ID: "+ post.getUserId() + "\n";
                    content += "Title: "+ post.getTitle() + "\n";
                    content += "Text: "+ post.getText() + "\n";
                    //textViewResult.append(content);
                }

            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                //textViewResult.setText("prob "+t.getMessage());
            }
        });
    }
}