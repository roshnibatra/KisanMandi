package com.example.android.vegetablemandi.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.android.vegetablemandi.R;
import com.example.android.vegetablemandi.model.Category;
import com.example.android.vegetablemandi.model.Grocery;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder> {

    List<Grocery> groceryList;
    Context context;
    private static final String BASE_URL = "https://staging.madrasmandi.zethic.com";

    public CategoryAdapter(Context context, List<Grocery> groceryList) {
        this.groceryList = groceryList;
        this.context = context;
    }

    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.category_item,parent,false);

        return new CategoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, int position) {
        Grocery grocery = groceryList.get(position);

        holder.category_name.setText(grocery.getCategory().getName());
        Glide.with(context)
                .load(BASE_URL + grocery.getIcon())
                .centerCrop()
                .into(holder.category_image);
    }

    public void setGroceryCategoryList(List<Grocery> groceryList) {
        this.groceryList = groceryList;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return groceryList!=null ? groceryList.size() : 0;
    }

    static class CategoryViewHolder extends RecyclerView.ViewHolder{

        TextView category_name;
        ImageView category_image;

        public CategoryViewHolder(@NonNull View itemView) {
            super(itemView);
            category_image = itemView.findViewById(R.id.category_image);
            category_name = itemView.findViewById(R.id.category_name);
        }
    }
}
