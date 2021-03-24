package com.example.android.vegetablemandi.ui;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android.vegetablemandi.Adapter.CartAdapter;
import com.example.android.vegetablemandi.R;
import com.example.android.vegetablemandi.model.Grocery;
import com.example.android.vegetablemandi.model.RoomDatabase.CartEntity;
import com.example.android.vegetablemandi.model.RoomDatabase.CartViewModel;

import java.util.ArrayList;
import java.util.List;

public class CartActivity extends AppCompatActivity implements CartAdapter.OnCartClickListener {

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
        cartAdapter = new CartAdapter(this,new ArrayList<CartEntity>(),this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(cartAdapter);
       // cartAdapter.setCartList(cartEntities);
        cartEntities = new ArrayList<>();


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

//        increment.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });
//
//        decrement.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });

    }

    @Override
    public void onAddItemClick(final int position) {

        CartEntity ce = cartAdapter.getNoteAt(position);
        cartViewModel.updateQuantity(ce.name);
      //  ce.setActual_quantity();
    }
//
    @Override
    public void onDeleteItemClick(final int position) {
//        Toast.makeText(getApplicationContext(),"onclick happening",Toast.LENGTH_SHORT).show();
//        LiveData<List<CartEntity>> cartEntityList = cartViewModel.getAllItems();
//        cartEntityList.observe(this, new Observer<List<CartEntity>>() {
//            @Override
//            public void onChanged(List<CartEntity> cartEntityList) {
//                try {
//                    if (cartEntityList.get(position).getActual_quantity() == 1 && (cartEntityList.indexOf(cartEntityList.get(position)) < cartEntityList.size())) {
//                        cartViewModel.delete(cartEntityList.get(position));
//                    }
//                }catch (Exception e) {
//                    Log.d("TAG", "onChanged: ");
//                    e.printStackTrace();
//                }
//            }
//        });

        CartEntity ce =cartAdapter.getNoteAt(position);
        if(cartAdapter.getNoteAt(position).getActual_quantity() == 1) {
            cartViewModel.delete(ce);
        }
        else {
            cartViewModel.decrementQuantity(ce.name);
        }
    }
}