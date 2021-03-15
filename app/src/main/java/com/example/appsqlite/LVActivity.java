package com.example.appsqlite;

import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.appsqlite.adapter.ContactListAdapter;
import com.example.appsqlite.adapter.ContactListViewAdapter;
import com.example.appsqlite.beans.Contact;
import com.example.appsqlite.databinding.ContactlistviewBinding;

import java.util.ArrayList;

public class LVActivity extends AppCompatActivity {
    ArrayAdapter<Contact> contactArrayAdapter;
    ArrayList<Contact> contactArrayList=new ArrayList<>();
    ContactlistviewBinding contactlistviewBinding;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        contactlistviewBinding= ContactlistviewBinding.inflate(LayoutInflater.from(this));
        setContentView(contactlistviewBinding.getRoot());

        DbManager db=new DbManager(this);
        Cursor csr=db.getAllContacts();

        while (csr.moveToNext())
        {
            Contact c=new Contact(csr.getInt(0),csr.getString(1),csr.getString(2));
            contactArrayList.add(c);
        }


        contactArrayAdapter = new ContactListViewAdapter(this,contactArrayList);
        contactlistviewBinding.lv.setAdapter(contactArrayAdapter);

    }
}
