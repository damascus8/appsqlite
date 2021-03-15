package com.example.appsqlite;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.appsqlite.adapter.ContactListAdapter;
import com.example.appsqlite.beans.Contact;
import com.example.appsqlite.databinding.AddcontactBinding;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.io.ByteArrayOutputStream;


public class ContactActivity extends AppCompatActivity {


    AddcontactBinding databinding;
    DbManager db = new DbManager(this);
    /*Uri imageUri;
    byte [] img;*/
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        databinding = AddcontactBinding.inflate(LayoutInflater.from(this));
        setContentView(databinding.getRoot());
            initToolbar();
        databinding.btnadd.setVisibility(View.VISIBLE);
//        Toast.makeText(this, "sss", Toast.LENGTH_SHORT).show();
        addContacts();
        viewContact();

        deleteContacts();

        updateContacts();

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    private void updateContacts() {
        databinding.btnupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             String name=databinding.etname.getText().toString();
             String contact=databinding.etcontact.getText().toString();

        /*     Contact c = new Contact(name,contact);
               String result= db.updateContact(c);
                Toast.makeText(ContactActivity.this, result, Toast.LENGTH_SHORT).show();
*/
            }
        });
    }

    private void deleteContacts() {
        databinding.btndelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name=databinding.etname.getText().toString();
//                String result = db.deleteContact(name);
//                Toast.makeText(ContactActivity.this, result, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void viewContact() {
        databinding.btnshow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String contact=databinding.etcontact.getText().toString();
                Cursor result=db.getContact(contact);
                if(result.getCount()>0) {
                    while (result.moveToNext()) {
                        Toast.makeText(ContactActivity.this, "OUTPUT\nname = " + result.getString(1) + "\n contact = " +
                                result.getString(2), Toast.LENGTH_SHORT).show();
                    }
                }
                else
                {
                    Toast.makeText(ContactActivity.this, "Contact not available..", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void initToolbar() {

        setSupportActionBar(databinding.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Add Contacts");
//        addImage();
    }
/*
    private void addImage() {

        databinding.civdp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadImage();
            }
        });

    }*/
/*

    private void uploadImage() {
        CropImage.activity()
                .setGuidelines(CropImageView.Guidelines.ON)
                .setAspectRatio(1, 1)
                .start(ContactActivity.this);

    }



    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK) {
                imageUri = result.getUri();
                databinding.civdp.setImageURI(imageUri);
                Uri datapath=data.getData();
                try {
                    Bitmap bitmap= MediaStore.Images.Media.getBitmap(getApplicationContext().getContentResolver(),datapath);
                    ByteArrayOutputStream byteArray=new ByteArrayOutputStream();
                    bitmap.compress(Bitmap.CompressFormat.PNG,100,byteArray);
                     img=byteArray.toByteArray();
                }
                catch(Exception e)
                {
                    e.printStackTrace();
                }


            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                Exception error = result.getError();
                Log.e("ERRORIMAGE", "" + error);

            }
        }
    }
*/


    private void addContacts() {

        databinding.btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name=databinding.etname.getText().toString();
                String contact=databinding.etcontact.getText().toString();
                Contact c =new Contact(name,contact);

                 db.addContacts(c);
                databinding.etname.setText("");
                databinding.etcontact.setText("");


            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();

    }
}