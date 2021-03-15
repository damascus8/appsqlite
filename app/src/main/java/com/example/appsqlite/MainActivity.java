package com.example.appsqlite;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import com.example.appsqlite.adapter.ContactListAdapter;
import com.example.appsqlite.beans.Contact;
import com.example.appsqlite.databinding.ActivityMainBinding;
import com.example.appsqlite.databinding.ContactlistviewBinding;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

        ArrayList<Contact> contactList =new ArrayList<>();
        ActivityMainBinding databinding;
    ContactListAdapter adapter = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

            databinding = ActivityMainBinding.inflate(LayoutInflater.from(this));
            setContentView(databinding.getRoot());

            initToolbar();


            //code to R/W contacts from phone
            int resultValue=1;
        if( getApplicationContext().checkSelfPermission( Manifest.permission.READ_CONTACTS ) != PackageManager.PERMISSION_GRANTED )
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_CONTACTS}, resultValue);


                  databinding.btnphonecontact.setOnClickListener(new View.OnClickListener() {
                      @Override
                      public void onClick(View v) {
                          Intent in  = new Intent(MainActivity.this,PhoneContactActivity.class);
                          startActivity(in);
                      }
                  });


                databinding.btnlistview.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent in=new Intent(MainActivity.this,LVActivity.class);
                        startActivity(in);
                    }
                });


                   databinding.btnretrofit.setOnClickListener(new View.OnClickListener() {
                       @Override
                       public void onClick(View v) {
                           Intent in =new Intent(MainActivity.this,RetrofitActivity.class);
                           startActivity(in);
                       }
                   });

            databinding.btnvolley.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent in=new Intent(MainActivity.this,VolleyActivity.class);
                    startActivity(in);
                }
            });

                DbManager db=new DbManager(this);
                databinding.btnaddcontact.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent in =new Intent(MainActivity.this, ContactActivity.class);
                    Toast.makeText(MainActivity.this, "intent gone", Toast.LENGTH_SHORT).show();
                    startActivity(in);
                }
            });
             Cursor cursor= db.getAllContacts();
        while (cursor.moveToNext())
        {
            Contact contact=new Contact(cursor.getInt(0),cursor.getString(1),cursor.getString(2));
            contactList.add(contact);
        }
            databinding.rvcontacts.setLayoutManager(new LinearLayoutManager(this));
            adapter = new ContactListAdapter(MainActivity.this,contactList);
            databinding.rvcontacts.setAdapter(adapter);
            adapter.notifyDataSetChanged();


    }

    private void initToolbar() {
        setSupportActionBar(databinding.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Home");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Toast.makeText(this, "sdc", Toast.LENGTH_SHORT).show();
//        MainActivity.rv.getAdapter().notifyDataSetChanged();
         adapter.notifyDataSetChanged();
    }
}