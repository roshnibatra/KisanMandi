package com.example.android.vegetablemandi;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android.vegetablemandi.Adapter.GroceryAdapter;
import com.example.android.vegetablemandi.ApiInterface.RetrofitApi;
import com.example.android.vegetablemandi.client.RetrofitClientInstance;
import com.example.android.vegetablemandi.model.Data;
import com.example.android.vegetablemandi.model.Grocery;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    ProgressBar progressBar;
    LinearLayoutManager layoutManager;
    Boolean isScrolling = true;
    int currentItem,totalItem ,scrolledOutItem;
    RecyclerView recyclerView;
     GroceryAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

         recyclerView= findViewById(R.id.recycler_view);
         adapter = new GroceryAdapter(new ArrayList<Grocery>(), this);
        // RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
        layoutManager = new GridLayoutManager(this, 2,
                GridLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);


        progressBar = findViewById(R.id.progress_bar);
        progressBar.setVisibility(View.VISIBLE);

        RetrofitApi retrofitApi = RetrofitClientInstance.getRetrofitInstance()
                .create(RetrofitApi.class);
        Call<Data> call= retrofitApi.getAllVegetables();
        call.enqueue(new Callback<Data>() {
            @Override
            public void onResponse(Call<Data> call, final Response<Data> response) {
                progressBar.setVisibility(View.GONE);
                if (response.isSuccessful() && response.body() != null) {
                    Log.i("MainActivity","response successfully received");
                    Log.i("TAG", "onResponse: " + response.body().getData());
                    recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
                        @Override
                        public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                            super.onScrollStateChanged(recyclerView, newState);
                            if(currentItem == 0) {
                                fetchData(response);
                            }
                            if(newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL)
                            {

                                isScrolling = true;
                            }
                        }

                        @Override
                        public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                            super.onScrolled(recyclerView, dx, dy);
                            currentItem = layoutManager.getChildCount();
                            totalItem= layoutManager.getItemCount();
                            scrolledOutItem = layoutManager.findFirstVisibleItemPosition();
                             //   fetchData(response);
                            if(currentItem ==0) {
                                fetchData(response);
                            }
                           else if((currentItem + scrolledOutItem > totalItem)) {
                              //  fetch new data
                                isScrolling = false;
                                progressBar.setVisibility(View.VISIBLE);
                                fetchData(response);
                            }

                        }
                    });

                }
            }

            @Override
            public void onFailure(Call<Data> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
                t.printStackTrace();
                Log.i("MainActivity","error in receiving data");

            }
        });
    }

    private void fetchData(final Response<Data> response) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                adapter.setGroceryList(response.body().getData());
                    adapter.notifyDataSetChanged();
                    progressBar.setVisibility(View.GONE);

            }
        }, 8000);
    }

}