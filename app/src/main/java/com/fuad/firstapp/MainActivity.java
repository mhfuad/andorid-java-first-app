package com.fuad.firstapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {
    public static final String API_URL = "https://64a40253c3b509573b56ea44.mockapi.io";
    RecyclerView rcvMain;
    FloatingActionButton fab;
    List<Post> posts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rcvMain = findViewById(R.id.rcvMain);
        fab = findViewById(R.id.fab);
        rcvMain.setLayoutManager(new LinearLayoutManager(this));


        fab.setContentDescription("Add new post");
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "button click",Toast.LENGTH_LONG).show();
                openPostForm();
            }
        });

        RetrofitInstance.getInstance().mockApi.getPosts().enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                if(!response.isSuccessful()){
                    return;
                }

                posts = response.body();
                rcvMain.setAdapter(new PostAdapter(MainActivity.this,posts));
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
            }
        });
    }
    public void openPostForm(){
        Intent intent = new Intent(this, AddPostActivity.class);
        startActivity(intent);
    }
}