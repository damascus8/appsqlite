package com.example.appsqlite;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.widget.BaseAdapter;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.appsqlite.adapter.PhoneLVAdapter;
import com.example.appsqlite.beans.Contact;
import com.example.appsqlite.databinding.ActivityphonecontactBinding;

import java.util.ArrayList;

public class PhoneContactActivity extends AppCompatActivity {

    ArrayList<Contact> contactList = new ArrayList<>();
    PhoneLVAdapter adapter;
    ActivityphonecontactBinding activityphonecontactBinding;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        activityphonecontactBinding = ActivityphonecontactBinding.inflate(LayoutInflater.from(this));
        setContentView(activityphonecontactBinding.getRoot());

        //
        ContentResolver contentResolver = getContentResolver();

        Uri uri= ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
        String [] projection=null;
        String selection=null;
        String [] selectionArgs=null;
        String sortOrder=null;

        Cursor cursor = contentResolver.query(uri,projection,selection,selectionArgs,sortOrder);

        while(cursor.moveToNext())
        {

            String name=cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
            String number=cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));

            Contact c=new Contact(name,number);
            contactList.add(c);

        }



        //
        adapter = new PhoneLVAdapter(PhoneContactActivity.this,contactList);
        activityphonecontactBinding.lvphonecontacts.setAdapter(adapter);
        adapter.notifyDataSetChanged();


    }
}
