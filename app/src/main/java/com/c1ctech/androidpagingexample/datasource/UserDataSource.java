package com.c1ctech.androidpagingexample.datasource;

import android.arch.paging.ItemKeyedDataSource;
import android.support.annotation.NonNull;
import android.util.Log;

import com.c1ctech.androidpagingexample.model.User;
import com.c1ctech.androidpagingexample.rest.ApiClient;
import com.c1ctech.androidpagingexample.rest.ApiInterface;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserDataSource extends ItemKeyedDataSource<Long,User>{

    public static final String TAG = "UserDataSource";

    LoadInitialParams<Long> initialParams;
    LoadParams<Long> afterParams;

    private Executor retryExecutor;

    public UserDataSource(Executor retryExecutor) {

        this.retryExecutor = retryExecutor;
    }


    @Override
    public void loadInitial(@NonNull LoadInitialParams<Long> params, @NonNull LoadInitialCallback<User> callback) {

        List<User> gitHubUser = new ArrayList();
        initialParams = params;

        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);

        Call<List<User>> call = apiService.getUsers(1,params.requestedLoadSize);
        call.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {

                if(response.body()!=null) {
                    gitHubUser.addAll(response.body());
                }
                callback.onResult(gitHubUser);

                initialParams = null;
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                Log.e(TAG, t.toString());
            }
        });


    }

    @Override
    public void loadAfter(@NonNull LoadParams<Long> params, @NonNull LoadCallback<User> callback) {
        Log.i(TAG, "Loading Rang " + params.key + " Count " + params.requestedLoadSize);
        List<User> gitHubUser = new ArrayList();
        afterParams = params;


        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);

        Call<List<User>> call = apiService.getUsers(params.key,params.requestedLoadSize);
        call.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                if (response.isSuccessful() && response.code() == 200) {
                    gitHubUser.addAll(response.body());
                    callback.onResult(gitHubUser);

                    afterParams = null;
                }
                else {
                    Log.e("API CALL", response.message());
                }
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                Log.e(TAG, t.toString());
            }

        });


    }

    @Override
    public void loadBefore(@NonNull LoadParams<Long> params, @NonNull LoadCallback<User> callback) {

    }

    @NonNull
    @Override
    public Long getKey(@NonNull User item) {
        return item.uniqueId;
    }

}
