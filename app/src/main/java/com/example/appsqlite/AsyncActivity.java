package com.example.appsqlite;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.appsqlite.databinding.ActivityasyncBinding;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class AsyncActivity extends AppCompatActivity {


    ActivityasyncBinding activityasyncBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityasyncBinding = ActivityasyncBinding.inflate(LayoutInflater.from(this));
        setContentView(activityasyncBinding.getRoot());
        String api = "https://jsonplaceholder.typicode.com/posts";
        new PostTask().execute(api);
    }


    class PostTask extends AsyncTask<String,Void,String> {
        ProgressDialog pd = new ProgressDialog(AsyncActivity.this);
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pd.setMessage("please wait for response..");
            pd.show();
        }

        @Override
        protected String doInBackground(String... strings) {
            String data="";
            try{
                String apiUrl = strings[0];
                URL url = new URL(apiUrl);
                HttpURLConnection connection = (HttpURLConnection)url.openConnection();
                connection.setRequestMethod("GET");
                connection.connect();
                InputStream in = connection.getInputStream();
                while(true){
                    int x = in.read();
                    if(x==-1)
                        break;
                    data = data +(char)x;
                }
            }
            catch (Exception e){

            }
            return data;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            pd.dismiss();
            Toast.makeText(AsyncActivity.this, s, Toast.LENGTH_SHORT).show();
        }
    }











}
