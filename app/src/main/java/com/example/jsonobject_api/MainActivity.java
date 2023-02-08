package com.example.jsonobject_api;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;

    ProgressDialog dialog;

    ArrayList idList = new ArrayList();
    ArrayList titleList = new ArrayList();
    ArrayList descList = new ArrayList();
    ArrayList priceList = new ArrayList();

    ArrayList disperList = new ArrayList();

    ArrayList ratingList = new ArrayList();

    ArrayList stockList = new ArrayList();

    ArrayList brandList = new ArrayList();

    ArrayList categoryList = new ArrayList();

    ArrayList thumbList = new ArrayList();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView=findViewById(R.id.recycle);

        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "https://dummyjson.com/products";
        dialog=ProgressDialog.show(this,"Please Wait","Data Loading");

// Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        dialog.dismiss();
                        // Display the first 500 characters of the response string.
                        try {
                            JSONObject object = new JSONObject(response);
                            JSONArray jsonArray=object.getJSONArray("products");
                            int total=object.getInt("total");
                            int limit = object.getInt("limit");

                            for(int i=0;i<jsonArray.length();i++)
                            {
//                                System.out.println(idList);
                                JSONObject object1=jsonArray.getJSONObject(i);
                                idList.add(object1.getInt("id"));
                                titleList.add(object1.getString("title"));
                                descList.add(object1.getString("description"));
                                priceList.add(object1.getInt("price"));
                                disperList.add(object1.getInt("discountPercentage"));
                                ratingList.add(object1.getInt("rating"));
                                stockList.add(object1.getInt("stock"));
                                brandList.add(object1.getString("brand"));
                                categoryList.add(object1.getString("category"));
                                thumbList.add(object1.getString("thumbnail"));
                            }
                            myadapter my = new myadapter(MainActivity.this,idList,titleList,descList,priceList,disperList,ratingList,stockList,brandList,categoryList,thumbList);
                            recyclerView.setAdapter(my);

                            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(null,RecyclerView.VERTICAL,false);
                            recyclerView.setLayoutManager(linearLayoutManager);

                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        });

// Add the request to the RequestQueue.
        queue.add(stringRequest);

    }
}