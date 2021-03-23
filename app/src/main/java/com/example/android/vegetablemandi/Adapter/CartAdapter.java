package com.example.android.vegetablemandi.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.android.vegetablemandi.R;
import com.example.android.vegetablemandi.model.Grocery;
import com.example.android.vegetablemandi.model.RoomDatabase.CartEntity;

import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {

    Context context;
    List<CartEntity> cartList;

    private static final String BASE_URL = "https://staging.madrasmandi.zethic.com";

    public CartAdapter(Context context, List<CartEntity> cartList) {
        this.context = context;
        this.cartList = cartList;
    }


    public void setCartList(List<CartEntity> g) {
        cartList = g;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.cart_item,parent,false);
        return new CartViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {
        CartEntity groceryData = cartList.get(position);
        holder.name.setText(groceryData.getName());
       // Log.d("Main", "onBindViewHolder: "+groceryData.getLogo());
//        Log.d("Main", "onBindViewHolder: "+groceryData.getIcon());
       holder.totalPrice.setText("Total Rs"+(int) (groceryData.getPrice() * groceryData.getCart_quantity()));
        Log.d("TAG", "onBindViewHolder: "+(groceryData.getPrice()));
        Log.d("TAG", "onBindViewHolder: "+groceryData.getCart_quantity());
        holder.price.setText("Rs "+groceryData.getPrice());
        holder.minQuantity.setText("Min "+groceryData.getMinimum_quantity());
        Glide.with(context)
                .load(groceryData.getIcon())
                .centerCrop()
                .into(holder.icon);
    }

    @Override
    public int getItemCount() {
        return cartList != null ? cartList.size() : 0;
    }

    class CartViewHolder extends RecyclerView.ViewHolder {
        TextView name, price,totalPrice,minQuantity,actual_quantity;
        ImageView icon;

        public CartViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.cart_name);
            price = itemView.findViewById(R.id.cart_price_per_kg);
            totalPrice = itemView.findViewById(R.id.cart_price_total);
            minQuantity = itemView.findViewById(R.id.min_quant_cart);
            actual_quantity = itemView.findViewById(R.id.actual_quantity_cart);
            icon = itemView.findViewById(R.id.image_icon);
        }
    }
}