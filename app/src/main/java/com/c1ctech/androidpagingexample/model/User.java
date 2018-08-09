package com.c1ctech.androidpagingexample.model;

import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;
import android.support.v7.recyclerview.extensions.DiffCallback;

import com.google.gson.annotations.SerializedName;


public class User {


    public static DiffCallback<User> DIFF_CALLBACK = new DiffCallback<User>() {
        @Override
        public boolean areItemsTheSame(@NonNull User oldItem, @NonNull User newItem) {
            return oldItem.uniqueId == newItem.uniqueId;
        }

        @Override
        public boolean areContentsTheSame(@NonNull User oldItem, @NonNull User newItem) {
            return oldItem.equals(newItem);
        }
    };

    @SerializedName("id")
    @PrimaryKey
    public long uniqueId;

    @SerializedName("login")
    public String login;


    @SerializedName("url")
    public String url;


    @Override
    public boolean equals(Object obj) {
        if (obj == this)
            return true;

        User user = (User) obj;

        return user.uniqueId == this.uniqueId && user.login == this.login  ;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + uniqueId +
                ", login='" + login + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
