package com.fuad.firstapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import com.google.gson.Gson;

public class MainActivity extends AppCompatActivity {
    private static final String API_URL = "https://64a40253c3b509573b56ea44.mockapi.io/users";
    private RecyclerView recyclerView;
    private TableAdapter adapter;
    private List<TableRowModel> tableData;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(API_URL)
                .build();
            client.newCall(request).enqueue(new Callback() {
                public void onFailure(Call call, IOException e) {
                    e.printStackTrace();
                    // Handle failure
                }

                public void onResponse(Call call, Response response) throws IOException {
                    if (response.isSuccessful()) {
                        String responseData = response.body().string();
                        List<TableRowModel> data = parseResponseData(responseData);
                        System.out.println(data);
                        // Process and display the data
                        runOnUiThread(() -> showDataInTable(data));
                    } else {
                        // Handle error
                    }
                }
            });
        }
        //tableData = new ArrayList<>();
        //adapter = new TableAdapter(tableData);
        //recyclerView.setAdapter(adapter);

        //new ApiCallTask().execute(API_URL);
        private List<TableRowModel> parseResponseData(String responseData) {
            Gson gson = new Gson();
            TableRowModel[] dataModels = gson.fromJson(responseData, TableRowModel[].class);
            return Arrays.asList(dataModels);
        }

        private void showDataInTable(List<TableRowModel> data) {
            adapter = new TableAdapter(data);
            recyclerView.setAdapter(adapter);
        }
}