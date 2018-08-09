package com.c1ctech.androidpagingexample.activity;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.c1ctech.androidpagingexample.R;
import com.c1ctech.androidpagingexample.adapder.UserAdapter;
import com.c1ctech.androidpagingexample.viewmodel.UserViewModel;

public class MainActivity extends AppCompatActivity {



    private final static String API_KEY = "kAv1Vdcj8teYg5QnVmAACIofzdg2";
    private static final String TAG = MainActivity.class.getSimpleName();
    private UserViewModel viewModel;
    RecyclerView recyclerView;
     UserAdapter userAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        viewModel = ViewModelProviders.of(this).get(UserViewModel.class);

         UserAdapter userAdapter = new UserAdapter(getApplicationContext());

        viewModel.matchList.observe(this,  pagedList -> {
            userAdapter.setList(pagedList);
        });
               
        recyclerView.setAdapter(userAdapter);


    }
}
