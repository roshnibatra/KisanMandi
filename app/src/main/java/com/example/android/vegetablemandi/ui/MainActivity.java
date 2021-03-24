package com.example.android.vegetablemandi.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android.vegetablemandi.Adapter.GroceryAdapter;
import com.example.android.vegetablemandi.ApiInterface.RetrofitApi;
import com.example.android.vegetablemandi.R;
import com.example.android.vegetablemandi.client.RetrofitClientInstance;
import com.example.android.vegetablemandi.model.Data;
import com.example.android.vegetablemandi.model.Grocery;
import com.example.android.vegetablemandi.model.RoomDatabase.CartEntity;
import com.example.android.vegetablemandi.model.RoomDatabase.CartRepository;
import com.example.android.vegetablemandi.model.RoomDatabase.CartViewModel;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements GroceryAdapter.OnCartClickListener {

    private static final String TAG = "MainActivity";
    private CartViewModel cartViewModel;
    ProgressBar progressBar;
    LinearLayoutManager layoutManager;
    Boolean isScrolling = true;
    int currentItem, totalItem, scrolledOutItem, previousScrolledOut;
    int view_threshold = 6;
    RecyclerView recyclerView;
    GroceryAdapter adapter;
    List<Grocery> groceryList;
    List<Grocery> filterList;
    ImageView cart;
    private static final String BASE_URL = "https://staging.madrasmandi.zethic.com";
    CartRepository cartRepository;
    Button cat1, cat2, cat3, cat4, cat5, cat6;
    Button selectedNow;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        groceryList = new ArrayList<>();
        filterList = new ArrayList<>();
        cartViewModel = new ViewModelProvider.AndroidViewModelFactory(getApplication())
                .create(CartViewModel.class);
        recyclerView = findViewById(R.id.recycler_view);
        adapter = new GroceryAdapter(new ArrayList<Grocery>(), this, this);
        // RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
        layoutManager = new GridLayoutManager(this, 2,
                GridLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setAdapter(adapter);

        cart = findViewById(R.id.cart);
        cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CartActivity.class);
                startActivity(intent);
            }
        });



        cat1 = findViewById(R.id.category1);
        cat1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                filterCategory(1);
                adapter.setGroceryList(filterList);

                Log.d(TAG, "onClick: "+ filterList);
                Log.d(TAG, "size: "+ filterList.size());
            }
        });

        cat2 = findViewById(R.id.category2);
        cat2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                filterCategory(2);
                adapter.setGroceryList(filterList);
                Log.d(TAG, "onClick: "+ filterList);
                Log.d(TAG, "size: "+ filterList.size());
            }
        });

        cat3 = findViewById(R.id.category3);
        cat3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                filterCategory(3);
                adapter.setGroceryList(filterList);
                Log.d(TAG, "onClick: "+ filterList);
                Log.d(TAG, "size: "+ filterList.size());
            }
        });

        cat4 = findViewById(R.id.category4);
        cat4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                filterCategory(4);
                adapter.setGroceryList(filterList);
                Log.d(TAG, "onClick: "+ filterList);
                Log.d(TAG, "size: "+ filterList.size());
            }
        });

        cat5 = findViewById(R.id.category5);
        cat5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                filterCategory(5);
                adapter.setGroceryList(filterList);
                Log.d(TAG, "onClick: "+ filterList);
                Log.d(TAG, "size: "+ filterList.size());
            }
        });

        cat6 = findViewById(R.id.category6);
        cat6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                filterCategory(6);
                adapter.setGroceryList(filterList);
                Log.d(TAG, "onClick: "+ filterList);
                Log.d(TAG, "size: "+ filterList.size());
            }
        });


        progressBar = findViewById(R.id.progress_bar);
        progressBar.setVisibility(View.VISIBLE);

        RetrofitApi retrofitApi = RetrofitClientInstance.getRetrofitInstance()
                .create(RetrofitApi.class);
        Call<Data> call = retrofitApi.getAllVegetables();
        call.enqueue(new Callback<Data>() {
            @Override
            public void onResponse(Call<Data> call, final Response<Data> response) {
                progressBar.setVisibility(View.GONE);
                if (response.isSuccessful() && response.body() != null) {

                    groceryList = response.body().getData();
                    adapter.setGroceryList(groceryList);

                    Log.i("MainActivity", "response successfully received");
                    Log.i("TAG", "onResponse: " + response.body().getData());
                    recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
     //                   @Override
//                        public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
//                            super.onScrollStateChanged(recyclerView, newState);
//                            if (currentItem < 0) {
//                                fetchData(response);
//                            }
//                            if (newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL) {
//
//                                isScrolling = true;
//                            }
//                        }



//                        @Override
//                        public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
//                            super.onScrolled(recyclerView, dx, dy);
//                            currentItem = layoutManager.getChildCount();
//                            totalItem = layoutManager.getItemCount();
//                            scrolledOutItem = layoutManager.findFirstVisibleItemPosition();
//
//                            if (dy > 0) {
//                                if (isScrolling) {
//                                    if (totalItem > scrolledOutItem) {
//                                        isScrolling = false;
//                                        previousScrolledOut = totalItem;
//                                    }
//                                }
//
//                                if(!isScrolling && (totalItem -currentItem) <= (scrolledOutItem+view_threshold))
//                                {
//                                    fetchData(response);
//                                    isScrolling = true;
//                                }
//                                //   fetchData(response);
////                                if (currentItem == 0) {
////                                    fetchData(response);
////                                } else if ((currentItem + scrolledOutItem > totalItem)) {
////                                    //  fetch new data
////                                    isScrolling = false;
////                                    progressBar.setVisibility(View.VISIBLE);
////                                    fetchData(response);
////                                }
//
//                            }
//                        }
                    });

                }
            }

            @Override
            public void onFailure(Call<Data> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
                t.printStackTrace();
                Log.i("MainActivity", "error in receiving data");

            }
        });
    }

    private void fetchData(final Response<Data> response) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                groceryList = response.body().getData();
                adapter.setGroceryList(groceryList);
                adapter.notifyDataSetChanged();
                progressBar.setVisibility(View.GONE);

            }
        }, 5000);
    }

    public void filterCategory(int cat) {
        filterList.clear();
        for (int i = 0; i < groceryList.size(); i++) {
           int id = groceryList.get(i).getCategory_id();
           if(id == cat) {
               filterList.add(groceryList.get(i));
           }
        }
    }
//
//    void insertOrUpdate(CartEntity item) {
//        List<CartEntity> itemsFromDB = cartViewModel.getItemByName(c))
//        if (itemsFromDB.isEmpty())
//            cartViewModel.insert(item);
//        else
//            cartViewModel.updateQuantity(item.getName());
//    }

    @Override
    public void onAddItemClick(int position) {
        Log.d(TAG, "onAddItemClick: "+filterList.size());
        if(filterList.size() > 0) {
            Grocery groceryData = filterList.get(position);

            CartEntity ce = new CartEntity(groceryData.getName(), groceryData.getPrice(), groceryData.getLogo(),
                    1, groceryData.getMinimum_quantity(), groceryData.getUnit(), BASE_URL + groceryData.getIcon());
           // groceryData.setCart_quantity(groceryData.getCart_quantity()+1);
            cartViewModel.insert(ce);
            Log.d(TAG, "onAddItemClick: " + BASE_URL + groceryData.getIcon());
           // Toast.makeText(this, groceryData.getCart_quantity()+1, Toast.LENGTH_SHORT).show();
        }
//        if(filterList.size() > 0) {
//            final Grocery groceryData = filterList.get(position);
//            List<CartEntity> cartEntityList = (List<CartEntity>) cartViewModel.getAllItems();
//
//                    if(cartEntityList.contains(groceryData.getName())) {
//                        cartViewModel.updateQuantity(groceryData.getName());
//                    }
//                    else {
//
//                        CartEntity ce = new CartEntity(groceryData.getName(), groceryData.getPrice(), groceryData.getLogo(),
//                                1, groceryData.getMinimum_quantity(), groceryData.getUnit(), BASE_URL + groceryData.getIcon());
//                        // groceryData.setCart_quantity(groceryData.getCart_quantity() + 1);
//                        cartViewModel.insert(ce);
//                    }
//                }



       else {
            final Grocery groceryData = groceryList.get(position);
//        CartEntity cartEntity = new CartEntity(groceryData.getName(), (int) groceryData.getPrice(),
//                BASE_URL+groceryData.getLogo(),groceryData.getCart_quantity()
//                ,groceryData.getMinimum_quantity());
            Grocery ge = adapter.getNoteAt(position);
            String name = ge.getName();

            Log.i(TAG, "onAddItemClick: item clicked: " + position);
            CartEntity ce = new CartEntity(groceryData.getName(), groceryData.getPrice(), groceryData.getLogo(),
                     1, groceryData.getMinimum_quantity(), groceryData.getUnit(), BASE_URL + groceryData.getIcon());
           // groceryData.setCart_quantity(groceryData.getCart_quantity() + 1);
            cartViewModel.insert(ce);
            Log.d(TAG, "onAddItemClick: " + BASE_URL + groceryData.getIcon());
            Toast.makeText(this, groceryData.getName(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onDeleteItemClick(int position) {

//        LiveData<List<CartEntity>> cartEntityList = cartViewModel.getAllItems();
//        Grocery ge = adapter.getNoteAt(position);
//        if(cartEntityList.equals(ge))
//        cartViewModel.delete();
    }
}