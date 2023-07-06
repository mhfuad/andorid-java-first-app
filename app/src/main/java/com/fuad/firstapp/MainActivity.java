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
import java.util.Iterator;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    private EditText name;

    private TextView responseTextView;

    private RecyclerView recyclerView;
    private TableAdapter adapter;
    private List<TableRowModel> tableData;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name = findViewById(R.id.editText);
        responseTextView = findViewById(R.id.textView);



        recyclerView = findViewById(R.id.recyclerView);
        tableData = new ArrayList<>();
        adapter = new TableAdapter(tableData);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        String apiUrl = "https://64a40253c3b509573b56ea44.mockapi.io/users";
        new ApiCallTask().execute(apiUrl);
    }

    private class ApiCallTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {
            String apiUrl = params[0];
            return fetchData(apiUrl);
        }

        protected void onPostExecute(String response){
            if (response != null){
                responseTextView.setText(response);
                try {
                    JSONArray jsonArray = new JSONArray(response);
                    //System.out.println(json.toString());

                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject json = jsonArray.getJSONObject(i);

                        // Loop through each key in the JSON object
                        Iterator<String> keys = json.keys();
                        while (keys.hasNext()) {
                            String key = keys.next();
                            Object value = json.get(key);
                            System.out.println("Key: " + key + ", Value: " + value);
                            TableRowModel row = new TableRowModel(value.toString());
                            tableData.add(row);
                        }
                    }
                } catch (JSONException e) {
                    System.out.println("exception bal");
                    e.printStackTrace();
                }
                //tableData.clear();
//                for(TableRowModel data : json){
//                    TableRowModel row = new TableRowModel(data.getColumn1());
//                    tableData.add(row);
//                }
            }else{
                responseTextView.setText("Error occurred");
            }
        }

        private String fetchData(String apiUrl) {
            OkHttpClient client = new OkHttpClient();

            Request request = new Request.Builder()
                    .url(apiUrl)
                    .build();

            try (Response response = client.newCall(request).execute()) {
                if (response.isSuccessful()) {
                    return response.body().string();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }
    }
}