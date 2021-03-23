//package com.example.android.vegetablemandi;
//
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.recyclerview.widget.GridLayoutManager;
//import androidx.recyclerview.widget.LinearLayoutManager;
//import androidx.recyclerview.widget.RecyclerView;
//
//import android.os.Bundle;
//import android.widget.ProgressBar;
//
//import com.example.android.vegetablemandi.Adapter.CategoryAdapter;
//import com.example.android.vegetablemandi.Adapter.GroceryAdapter;
//import com.example.android.vegetablemandi.ApiInterface.RetrofitApi;
//import com.example.android.vegetablemandi.client.RetrofitClientInstance;
//import com.example.android.vegetablemandi.model.Category;
//import com.example.android.vegetablemandi.model.Data;
//import com.example.android.vegetablemandi.model.Grocery;
//
//import java.util.ArrayList;
//import java.util.Arrays;
//
//import retrofit2.Call;
//import retrofit2.Callback;
//import retrofit2.Response;
//
//public class HomePageActivity extends AppCompatActivity {
//
//    LinearLayoutManager layoutManager;
//    RecyclerView recyclerView;
//    CategoryAdapter adapter;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_home_page);
//
////        recyclerView = findViewById(R.id.category_RecycleView);
////        adapter = new CategoryAdapter( this,new ArrayList<Category>(Arrays.asList( new Category("Leafy Vegetables", 1) )));
//////        layoutManager = new LinearLayoutManager(this,RecyclerView.HORIZONTAL,false);
//////        recyclerView.setLayoutManager(layoutManager);
////        layoutManager = new GridLayoutManager(this, 2,
////                GridLayoutManager.VERTICAL, false);
//////        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
////        recyclerView.setLayoutManager(layoutManager);
////        recyclerView.setAdapter(adapter);
//
////        RetrofitApi retrofitApi = RetrofitClientInstance.getRetrofitInstance()
////                .create(RetrofitApi.class);
////        Call<Data> call= retrofitApi.getAllVegetables();
////        call.enqueue(new Callback<Data>() {
////            @Override
////            public void onResponse(Call<Data> call, Response<Data> response) {
////                if (response.isSuccessful() && response.body() != null) {
////                    adapter.setGroceryCategoryList(response.body().getData());
////                }
////            }
////
////            @Override
////            public void onFailure(Call<Data> call, Throwable t) {
////
////            }
////        });
//    }
//}