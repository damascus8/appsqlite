package com.example.appsqlite.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.appsqlite.R;
import com.example.appsqlite.beans.Contact;
import com.example.appsqlite.databinding.ContactlistBinding;

import java.util.ArrayList;

public class PhoneLVAdapter extends ArrayAdapter {

    Context context;
    ArrayList<Contact> al;

public PhoneLVAdapter (Context context, ArrayList<Contact> al)
{
    super(context, R.layout.contactlist,al);
    this.context = context;
    this.al=al;
}


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        Contact c =al.get(position);
        ContactlistBinding contactlistBinding;
        contactlistBinding = ContactlistBinding.inflate(LayoutInflater.from(context));
        View v = contactlistBinding.getRoot();
        contactlistBinding.name.setText(c.getName());
        contactlistBinding.number.setText(c.getContact());
        contactlistBinding.ivclick.setVisibility(View.GONE);

        return v;



    }
}
