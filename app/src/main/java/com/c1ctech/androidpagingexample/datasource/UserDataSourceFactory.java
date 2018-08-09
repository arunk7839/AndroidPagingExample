package com.c1ctech.androidpagingexample.datasource;

import android.arch.lifecycle.MutableLiveData;
import android.arch.paging.DataSource;

import com.c1ctech.androidpagingexample.model.User;

import java.util.concurrent.Executor;

public class UserDataSourceFactory implements DataSource.Factory<String,User>{

    MutableLiveData<UserDataSource> mutableLiveData;
    UserDataSource userDataSource;
    Executor executor;

    public UserDataSourceFactory(Executor executor) {
        this.mutableLiveData = new MutableLiveData<UserDataSource>();
        this.executor = executor;
    }


    @Override
    public DataSource create() {
        userDataSource = new UserDataSource(executor);
        mutableLiveData.postValue(userDataSource);
        return userDataSource;
    }

    public MutableLiveData<UserDataSource> getMutableLiveData() {
        return mutableLiveData;
    }


}
