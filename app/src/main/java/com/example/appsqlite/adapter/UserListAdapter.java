package com.example.appsqlite.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appsqlite.VolleyActivity;
import com.example.appsqlite.beans.User;
import com.example.appsqlite.databinding.UserlistBinding;

import java.util.ArrayList;

public class UserListAdapter extends  RecyclerView.Adapter<UserListAdapter.UserViewHolder> {

    Context context;
    ArrayList<User> userList;


    public UserListAdapter(Context context,ArrayList<User> userList)
    {
        this.context=context;
        this.userList=userList;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        UserlistBinding userlistBinding =UserlistBinding.inflate(LayoutInflater.from(context),parent,false);
        return  new UserViewHolder(userlistBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {

        User u=userList.get(position);
//        Toast.makeText(context, "MAK"+u, Toast.LENGTH_SHORT).show();
        holder.userlistBinding.tvinputuid.setText(""+u.getUserId());
        holder.userlistBinding.tvinputId.setText(""+u.getId());
        holder.userlistBinding.tvinputtitle.setText(u.getTitle());
        holder.userlistBinding.tvinputbody.setText(u.getBody());
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    public class UserViewHolder extends RecyclerView.ViewHolder
    {
        UserlistBinding userlistBinding;

        public UserViewHolder(UserlistBinding userlistBinding) {

            super(userlistBinding.getRoot());
            this.userlistBinding=userlistBinding;


        }
    }


}
