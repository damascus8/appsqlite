package com.example.appsqlite.service;

import com.example.appsqlite.beans.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

public class DataService {
             public static String url = "https://jsonplaceholder.typicode.com/";

    public static  DataApi dataApi;

    public  static DataApi getDataApiInstace()
    {
        Retrofit retrofit =new Retrofit.Builder().baseUrl(url)
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();

        if(dataApi==null)
                dataApi=retrofit.create(DataApi.class);

        return  dataApi;
    }



    public interface DataApi
    {
        @GET("posts")
        Call<List<User>> getUsers();
    }

}
