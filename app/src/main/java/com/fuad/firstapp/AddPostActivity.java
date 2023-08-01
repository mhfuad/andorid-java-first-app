package com.fuad.firstapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddPostActivity extends AppCompatActivity {

    EditText title, description;
    Button postSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_post);

        title = findViewById(R.id.title);
        description = findViewById(R.id.description);
        postSubmit = findViewById(R.id.postSubmit);

        postSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PostRequest postRequest = new PostRequest();


                if(title.getText().length() != 0 && description.getText().length() != 0) {
                    postRequest.setTitle(title.getText().toString());
                    postRequest.setBody(description.getText().toString());

                    RetrofitInstance.getInstance().mockApi.savePost(postRequest).enqueue(new Callback<Post>() {
                        @Override
                        public void onResponse(Call<Post> call, Response<Post> response) {
                            if (response.isSuccessful()) {
                                Toast.makeText(AddPostActivity.this, "Save Post Successful", Toast.LENGTH_LONG).show();
                                gotoMain();
                            } else {
                                Toast.makeText(AddPostActivity.this, "Save Post Fail", Toast.LENGTH_LONG).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<Post> call, Throwable t) {
                            Toast.makeText(AddPostActivity.this, "Save Post fail" + t.getLocalizedMessage(), Toast.LENGTH_LONG).show();
                        }
                    });
                }else if(title.getText().length() == 0){
                    Toast.makeText(AddPostActivity.this, "Title should not empty", Toast.LENGTH_LONG).show();
                }else if (description.getText().length() == 0){
                    Toast.makeText(AddPostActivity.this, "Description should not empty", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public void gotoMain(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}