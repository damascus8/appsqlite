package com.example.appsqlite;

import android.os.Bundle;
import android.view.LayoutInflater;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.appsqlite.adapter.UserListAdapter;
import com.example.appsqlite.beans.User;
import com.example.appsqlite.databinding.ActivityretrofitBinding;
import com.example.appsqlite.service.DataService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class RetrofitActivity extends AppCompatActivity {

    ActivityretrofitBinding activityretrofitBinding;
    List<User> userList = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        activityretrofitBinding=ActivityretrofitBinding.inflate(LayoutInflater.from(this));
        setContentView(activityretrofitBinding.getRoot());


        DataService.DataApi dataApi =DataService.getDataApiInstace();

        Call<List<User>> call=dataApi.getUsers();
        call.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {

                if(response.code()==200)
                {
                     userList=response.body();

                    UserListAdapter adapter = new UserListAdapter(RetrofitActivity.this, (ArrayList<User>) userList);
                    activityretrofitBinding.rvuser.setLayoutManager(new LinearLayoutManager(RetrofitActivity.this));
                    activityretrofitBinding.rvuser.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {

            }
        });




    }
}
