package com.c1ctech.androidpagingexample.adapder;

import android.arch.paging.PagedListAdapter;
import android.content.Context;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.c1ctech.androidpagingexample.R;
import com.c1ctech.androidpagingexample.model.User;


public class UserAdapter extends PagedListAdapter<User, UserAdapter.UserViewHolder>{

    Context context;


    public UserAdapter(Context context) {

        super(User.DIFF_CALLBACK);

        this.context = context;

    }

    @Override
    public UserAdapter.UserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_list_item, parent, false);
        return new UserViewHolder(view);

    }

    public static class UserViewHolder extends RecyclerView.ViewHolder {


        TextView userName;
        TextView userId;


        public UserViewHolder(View v) {
            super(v);
            userId = (TextView) v.findViewById(R.id.tv_id);
            userName = (TextView) v.findViewById(R.id.tv_name);

        }

        public void bindTo(User user) {
            userId.setText(String.valueOf(user.uniqueId));
            userName.setText(user.login);

        }
    }


    @Override
    public void onBindViewHolder(UserViewHolder holder, int position) {

        holder.bindTo(getItem(position));

    }



}

