package com.example.android.vegetablemandi.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android.vegetablemandi.Adapter.CartAdapter;
import com.example.android.vegetablemandi.R;
import com.example.android.vegetablemandi.model.RoomDatabase.CartEntity;
import com.example.android.vegetablemandi.model.RoomDatabase.CartViewModel;

import java.util.ArrayList;
import java.util.List;

public class CartActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    CartAdapter cartAdapter;
    List<CartEntity> cartEntities;
    CartViewModel cartViewModel;
    ImageButton deleteAll,increment,decrement;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        recyclerView = findViewById(R.id.recyclerview_cart);
        deleteAll = findViewById(R.id.imageView2);
        increment = findViewById(R.id.increment_cart);
        decrement = findViewById(R.id.decrement_cart);
        cartAdapter = new CartAdapter(this,new ArrayList<CartEntity>());
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(cartAdapter);
       // cartAdapter.setCartList(cartEntities);


        cartViewModel = new ViewModelProvider.AndroidViewModelFactory(getApplication())
                .create(CartViewModel.class);

        cartViewModel.getAllItems().observe(this, new Observer<List<CartEntity>>() {
            @Override
            public void onChanged(List<CartEntity> cartEntities) {
                cartAdapter.setCartList(cartEntities);
                Toast.makeText(CartActivity.this,"Working fine",Toast.LENGTH_SHORT).show();
            }
        });

        deleteAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cartViewModel.deleteAll();
            }
        });

        increment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                
            }
        });

    }
}