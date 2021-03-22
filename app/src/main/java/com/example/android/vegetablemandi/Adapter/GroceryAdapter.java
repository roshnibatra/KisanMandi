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
import com.example.android.vegetablemandi.model.Data;
import com.example.android.vegetablemandi.model.Grocery;

import java.util.List;

public class GroceryAdapter extends RecyclerView.Adapter<GroceryAdapter.GroceryViewHolder> {

    List<Grocery> groceryList;
    Context context;
    private static final String BASE_URL = "https://staging.madrasmandi.zethic.com";

    public GroceryAdapter(List<Grocery> groceryList, Context context) {
        this.groceryList = groceryList;
        this.context = context;
    }

    @NonNull
    @Override
    public GroceryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.grocery_items,parent,false);
        return new GroceryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GroceryViewHolder holder, int position) {
        Grocery grocery = groceryList.get(position);
        Log.i("TAG", "onBindViewHolder: " + grocery.getLogo());
        holder.min_weight.setText("Min "+String.valueOf(grocery.getMinimum_quantity())+" "+grocery.getUnit());
        holder.vegetable_name.setText(grocery.getName());
        holder.price.setText(grocery.getFormatted_price()+"/"+grocery.getUnit());
        holder.actual_quantity.setText(String.valueOf(grocery.getCart_quantity()));
        Glide.with(context)
                .load(BASE_URL + grocery.getLogo())
                .centerCrop()
                .into(holder.image);

    }

    @Override
    public int getItemCount() {
        return groceryList!= null ? groceryList.size() : 0;
    }

    public void setGroceryList(List<Grocery> g) {
        groceryList = g;
        notifyDataSetChanged();
    }

    static class GroceryViewHolder extends RecyclerView.ViewHolder {

        TextView min_weight,vegetable_name, price,actual_quantity,description;
        ImageView image;
        ImageButton increment , decrement;
        int value = 1;
        public GroceryViewHolder(@NonNull View itemView) {
            super(itemView);

            min_weight = itemView.findViewById(R.id.min_quantity);
            vegetable_name = itemView.findViewById(R.id.grocery_name);
            price =  itemView.findViewById(R.id.price);
            actual_quantity = itemView.findViewById(R.id.actual_quantity);
            increment= itemView.findViewById(R.id.increment);
            decrement= itemView.findViewById(R.id.decrement);
            image =  itemView.findViewById(R.id.image);
            description= itemView.findViewById(R.id.description);

            increment.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                   if(Integer.parseInt(actual_quantity.toString()) >= 0) {
                       actual_quantity.setText(Integer.parseInt(actual_quantity.getText().toString())+value);
                   }
                }
            });

            decrement.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(Integer.parseInt(actual_quantity.toString()) >= 0) {
                        actual_quantity.setText(Integer.parseInt(actual_quantity.getText().toString())-value);
                    }
                }
            });
        }
    }
}
