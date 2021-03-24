package com.example.android.vegetablemandi.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.android.vegetablemandi.R;
import com.example.android.vegetablemandi.model.Data;
import com.example.android.vegetablemandi.model.Grocery;
import com.example.android.vegetablemandi.model.RoomDatabase.CartEntity;
import com.example.android.vegetablemandi.model.RoomDatabase.CartRepository;
import com.example.android.vegetablemandi.model.RoomDatabase.CartViewModel;
import com.example.android.vegetablemandi.ui.MainActivity;

import java.util.List;

public class GroceryAdapter extends RecyclerView.Adapter<GroceryAdapter.GroceryViewHolder> {

    List<Grocery> groceryList;
    Context context;
    List<CartEntity> cartEntities;
    OnCartClickListener onCartClickListener;
    CartViewModel cartViewModel;
    private static final String BASE_URL = "https://staging.madrasmandi.zethic.com";

    public GroceryAdapter(List<Grocery> groceryList, Context context, OnCartClickListener onCartClickListener) {
        this.groceryList = groceryList;
        this.context = context;
        this.onCartClickListener = onCartClickListener;
    }

    @NonNull
    @Override
    public GroceryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.grocery_items, parent, false);
        return new GroceryViewHolder(view, onCartClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull GroceryViewHolder holder, int position) {
        Grocery grocery = groceryList.get(position);
        // Log.i("TAG", "onBindViewHolder: " + grocery.getLogo());
        holder.min_weight.setText("Min " + String.valueOf(grocery.getMinimum_quantity()) + " " + grocery.getUnit());
        holder.vegetable_name.setText(grocery.getName());
        holder.price.setText(grocery.getFormatted_price() + "/" + grocery.getUnit());
        //  holder.actual_quantity.setText(String.valueOf(grocery.getCart_quantity()));
        Glide.with(context)
                .load(BASE_URL + grocery.getLogo())
                .centerCrop()
                .into(holder.image);

    }

    @Override
    public int getItemCount() {
        return groceryList != null ? groceryList.size() : 0;
    }

    public void setGroceryList(List<Grocery> g) {
        groceryList = g;
        notifyDataSetChanged();
    }

    public void getAllItemInCart(List<CartEntity> cartEntityList) {
        this.cartEntities = cartEntityList;
    }

    class GroceryViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView min_weight, vegetable_name, price, actual_quantity, description;
        ImageView image;
        ImageButton increment, decrement;
        OnCartClickListener onCartClickListener;
        int value = 1;

        public GroceryViewHolder(@NonNull View itemView, OnCartClickListener onCartClickListener) {
            super(itemView);

            min_weight = itemView.findViewById(R.id.min_quantity);
            vegetable_name = itemView.findViewById(R.id.grocery_name);
            price = itemView.findViewById(R.id.price);
            actual_quantity = itemView.findViewById(R.id.actual_quantity);
            increment = itemView.findViewById(R.id.increment);
            decrement = itemView.findViewById(R.id.decrement);
            image = itemView.findViewById(R.id.image);
            description = itemView.findViewById(R.id.description);
            this.onCartClickListener = onCartClickListener;
            increment.setOnClickListener(this);
            decrement.setOnClickListener(this);

//            increment.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    //   Log.i("Main", "Clciked on increment" + getAdapterPosition());
//                    if (actual_quantity.getText() != null && Integer.parseInt(actual_quantity.getText().toString()) >= 0) {
//                        actual_quantity.setText(String.valueOf(Integer.parseInt(actual_quantity.getText().toString()) + value));
//
//                    }
//
//                }
//            });


//            decrement.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    if (actual_quantity.getText() != null && Integer.parseInt(actual_quantity.getText().toString()) >= 0) {
//                        actual_quantity.setText(String.valueOf(Integer.parseInt(actual_quantity.getText().toString()) - value));
//                    }
//                }
//            });
        }

        @Override
        public void onClick(View v) {
            if(v == decrement) {
                onCartClickListener.onDeleteItemClick(getAdapterPosition());
            }
            else if (v == increment) {
                onCartClickListener.onAddItemClick(getAdapterPosition());
            }
        }
    }

    public interface OnCartClickListener {
        void onAddItemClick(int position);
        void onDeleteItemClick(int position);
    }
}
