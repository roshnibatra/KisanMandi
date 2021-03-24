package com.example.android.vegetablemandi.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
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
    OnCartClickListener onCartClickListener;

    private static final String BASE_URL = "https://staging.madrasmandi.zethic.com";


    public CartAdapter(Context context, List<CartEntity> cartList, OnCartClickListener onCartClickListener) {
        this.context = context;
        this.cartList = cartList;
        this.onCartClickListener = onCartClickListener;
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
        return new CartViewHolder(view,onCartClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {
        CartEntity groceryData = cartList.get(position);
        holder.name.setText(groceryData.getName());
       // Log.d("Main", "onBindViewHolder: "+groceryData.getLogo());
//        Log.d("Main", "onBindViewHolder: "+groceryData.getIcon());
       holder.totalPrice.setText("Total Rs"+(double) (groceryData.getPrice() * groceryData.getActual_quantity()));
        Log.d("TAG", "onBindViewHolder: "+(groceryData.getPrice()));
        Log.d("TAG", "onBindViewHolder: "+groceryData.getActual_quantity());
        holder.price.setText("Rs "+groceryData.getPrice());
        holder.minQuantity.setText("Min "+groceryData.getMinimum_quantity()+groceryData.getUnit());
        Glide.with(context)
                .load(groceryData.getIcon())
                .centerCrop()
                .into(holder.icon);
    }

    @Override
    public int getItemCount() {
        return cartList != null ? cartList.size() : 0;
    }

    class CartViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView name, price,totalPrice,minQuantity,actual_quantity;
        ImageView icon;
        ImageButton increment,decrement;
        OnCartClickListener onCartClickListener;

        public CartViewHolder(@NonNull View itemView,OnCartClickListener onCartClickListener) {
            super(itemView);
            name = itemView.findViewById(R.id.cart_name);
            price = itemView.findViewById(R.id.cart_price_per_kg);
            totalPrice = itemView.findViewById(R.id.cart_price_total);
            minQuantity = itemView.findViewById(R.id.min_quant_cart);
            actual_quantity = itemView.findViewById(R.id.actual_quantity_cart);
            icon = itemView.findViewById(R.id.image_icon);
            increment = itemView.findViewById(R.id.increment_cart);
            decrement= itemView.findViewById(R.id.decrement_cart);
            this.onCartClickListener = onCartClickListener;
            increment.setOnClickListener(this);
//
//            increment.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    if (actual_quantity.getText() != null && Integer.parseInt(actual_quantity.getText().toString()) > 0) {
//                        actual_quantity.setText(String.valueOf(Integer.parseInt(actual_quantity.getText().toString()) + 1));
//                        Log.d("TAG", "onClick: "+actual_quantity.getText());
//                    }
//                }
//            });
//
//            decrement.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    if (actual_quantity.getText() != null && Integer.parseInt(actual_quantity.getText().toString()) > 0) {
//                        actual_quantity.setText(String.valueOf(Integer.parseInt(actual_quantity.getText().toString()) - 1));
//                    }
//
//                }
//            });
        }

        @Override
        public void onClick(View v) {
           // onCartClickListener.onDeleteItemClick(getAdapterPosition());
            Log.d("TAG", "onClick: "+getAdapterPosition());
            onCartClickListener.onAddItemClick(getAdapterPosition());

        }

    }
    public interface OnCartClickListener {
        void onAddItemClick(int position);
      //  void onDeleteItemClick(int position);
    }
}
