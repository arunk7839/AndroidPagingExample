package com.c1ctech.androidpagingexample.rest;

import com.c1ctech.androidpagingexample.model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;


public interface ApiInterface {


    @GET("/users")
    Call<List<User>> getUsers(@Query("since") long since, @Query("per_page") int per_page);


}
