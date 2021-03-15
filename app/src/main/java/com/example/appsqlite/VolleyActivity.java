package com.example.appsqlite;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.appsqlite.adapter.UserListAdapter;
import com.example.appsqlite.beans.User;
import com.example.appsqlite.databinding.ActivityvolleyBinding;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class VolleyActivity extends AppCompatActivity {


    ActivityvolleyBinding activityvolleyBinding;
    ArrayList<User> userList = new ArrayList<>();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        activityvolleyBinding = ActivityvolleyBinding.inflate(LayoutInflater.from(this));
        setContentView(activityvolleyBinding.getRoot());
        Toast.makeText(this, "In caller", Toast.LENGTH_SHORT).show();

                RequestQueue requestQueue;

                requestQueue = Volley.newRequestQueue(VolleyActivity.this);

                String url = "https://jsonplaceholder.typicode.com/posts";


                JsonArrayRequest request=new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            for(int i=0;i<response.length();i++)
                            {
                                JSONObject jsonObject = response.getJSONObject(i);
                                String body=jsonObject.getString("body");
                                int userId=jsonObject.getInt("userId");
                                String title=jsonObject.getString("title");
                                int id=jsonObject.getInt("id");
                                User u =new User(userId,id,title,body);
                                userList.add(u);
//                                Toast.makeText(VolleyActivity.this, ""+u, Toast.LENGTH_SHORT).show();

//                                Toast.makeText(VolleyActivity.this, ""+userId+body, Toast.LENGTH_SHORT).show();

                                UserListAdapter adapter = new UserListAdapter(VolleyActivity.this,userList);
                                activityvolleyBinding.rvuser.setLayoutManager(new LinearLayoutManager(VolleyActivity.this));
                                activityvolleyBinding.rvuser.setAdapter(adapter);

                                adapter.notifyDataSetChanged();
                            }
                        }
                        catch (JSONException e) {
                            Log.e("TAG",""+e);
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(VolleyActivity.this, ""+error, Toast.LENGTH_SHORT).show();
                        Log.e("TAG","\n"+error);
                    }
                });
                requestQueue.add(request);





    }
}