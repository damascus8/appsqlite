package com.example.appsqlite.adapter;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appsqlite.DbManager;
import com.example.appsqlite.R;
import com.example.appsqlite.beans.Contact;
import com.example.appsqlite.databinding.AddcontactBinding;
import com.example.appsqlite.databinding.ContactlistBinding;

import java.util.ArrayList;

public class ContactListAdapter extends RecyclerView.Adapter<ContactListAdapter.ContactViewHolder> {
    Context context;
    ArrayList<Contact> contactList;
//    onRecyclerViewClick listener;
DbManager db = new DbManager(context);

    public ContactListAdapter(Context context, ArrayList<Contact> contactList)
    {
        this.context=context;
        this.contactList=contactList;
    }

    @NonNull
    @Override
    public ContactViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        ContactlistBinding contactlistBinding= ContactlistBinding.inflate(LayoutInflater.from(context),parent,false);
        return new ContactViewHolder(contactlistBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactViewHolder holder, int position) {

        Contact c  =contactList.get(position);
        holder.contactlistBinding.name.setText(c.getName());
        holder.contactlistBinding.number.setText(c.getContact());
        /*contactList.remove(position);
        notifyDataSetChanged();
*/

    }

    @Override
    public int getItemCount() {
        return contactList.size();
    }

    public class ContactViewHolder extends RecyclerView.ViewHolder
    {
        ContactlistBinding contactlistBinding;
        public ContactViewHolder(ContactlistBinding contactlistBinding) {
            super(contactlistBinding.getRoot());
            this.contactlistBinding=contactlistBinding;

            contactlistBinding.ivclick.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position=getAdapterPosition();

                    Contact c= contactList.get(position);
//                    Toast.makeText(context, "ooo=>"+c.getId()+c.getName(), Toast.LENGTH_SHORT).show();
//                    Toast.makeText(context, "ooo=>", Toast.LENGTH_SHORT).show();
                    PopupMenu popupMenu =new PopupMenu(context,contactlistBinding.getRoot());
                    popupMenu.getMenuInflater().inflate(R.menu.menu_list,popupMenu.getMenu());
                    popupMenu.setGravity(Gravity.RIGHT);
                    popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                        @Override
                        public boolean onMenuItemClick(MenuItem item) {
                            String title=item.getTitle().toString();
                            if(title.equals("Update"))
                            {
                                AddcontactBinding addcontactBinding ;
                                addcontactBinding=AddcontactBinding.inflate(LayoutInflater.from(context));
                                    updateContact(addcontactBinding,c);

                            }
                            if(title.equals("Delete")) {
                                    deleteContacts(c);
                                    Log.e("000//>",""+c.getId());
                            }

                            return false;
                        }
                    });
            popupMenu.show();
                }
            });


        }

        private void deleteContacts(Contact c) {

            AlertDialog.Builder ab = new AlertDialog.Builder(context);
            ab.setMessage("Do you want to delete  this contact ?");
            ab.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
//                    Toast.makeText(context, "SID"+c.getId(), Toast.LENGTH_SHORT).show();
                    Log.e("SID",""+c.getId());
                    DbManager db = new DbManager(context);
                    contactList.remove(c);
                    notifyDataSetChanged();
                    db.deleteContact(c.getId());

                Toast.makeText(context,"deleted Successfully !!", Toast.LENGTH_SHORT).show();
                }
            });
            ab.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });
            ab.show();
        }

        }

        private void updateContact(AddcontactBinding addcontactBinding,Contact c) {

        View v=addcontactBinding.getRoot();
        AlertDialog.Builder ab=new AlertDialog.Builder(context);
        LayoutInflater inflater=LayoutInflater.from(context);

        addcontactBinding.etcontact.setText(c.getContact());
        addcontactBinding.etname.setText(c.getName());

        AlertDialog.Builder builder=ab.setView(v).setPositiveButton("UPdate", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {


                c.setName(addcontactBinding.etname.getText().toString());
                c.setContact(addcontactBinding.etcontact.getText().toString());

                DbManager db = new DbManager(context);
                db.updateContact(c);
                notifyDataSetChanged();
                Toast.makeText(context,"Updated Successfully !!", Toast.LENGTH_SHORT).show();

            }
        }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
            ab.show();
        }
    }


   /* public interface onRecyclerViewClick
    {
        public void onItemClick(Contact c,int position);
    }

public void setOnItemClickListener(ContactListAdapter.onRecyclerViewClick listener)
{
    this.listener=listener;
}*/

/*
}*/
