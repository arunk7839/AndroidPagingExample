package com.c1ctech.androidpagingexample.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.arch.paging.LivePagedListBuilder;
import android.arch.paging.PagedList;

import com.c1ctech.androidpagingexample.datasource.UserDataSource;
import com.c1ctech.androidpagingexample.datasource.UserDataSourceFactory;
import com.c1ctech.androidpagingexample.model.User;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class UserViewModel extends ViewModel {

    public LiveData<PagedList<User>> matchList;

    Executor executor;
    LiveData<UserDataSource> tDataSource;

    public UserViewModel() {
        executor = Executors.newFixedThreadPool(5);
        UserDataSourceFactory userDataSourceFactory = new UserDataSourceFactory(executor);

        tDataSource = userDataSourceFactory.getMutableLiveData();


        PagedList.Config pagedListConfig =
                (new PagedList.Config.Builder()).setEnablePlaceholders(false)
                        .setInitialLoadSizeHint(10)
                        .setPageSize(20).build();

        matchList = (new LivePagedListBuilder(userDataSourceFactory, pagedListConfig))
                .setBackgroundThreadExecutor(executor)
                .build();
    }
}
