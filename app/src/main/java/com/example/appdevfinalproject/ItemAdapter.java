package com.example.appdevfinalproject;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ViewHolder> {

    private List<Item> itemList;
    private Context context;
    private String username;
// constructor
    public ItemAdapter(Context context, List<Item> itemList, String username) {
        this.context = context;
        this.itemList = itemList;
        this.username = username;
    }

    @NonNull
    @Override
    // inflates the layout
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_layout, parent, false);
        return new ViewHolder(itemView);
    }
// retrieve item object
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Item item = itemList.get(position);
        holder.nameTextView.setText(item.getName());
        holder.priceTextView.setText("₱"+item.getPrice());
        holder.itemImageView.setImageResource(item.getImageResId());

// click
        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, ProductInfo.class);
            intent.putExtra("username", username);
            intent.putExtra("productName", item.getName());
            intent.putExtra("productPrice", item.getPrice());
            intent.putExtra("productImageResId", item.getImageResId());
            intent.putExtra("productDescription", item.getDescription());
            intent.putExtra("productSpecifications", item.getSpecifications());
            intent.putExtra("productLocation", item.getLocation());
            context.startActivity(intent);
        });
    }
// number of items in the list
    @Override
    public int getItemCount() {
        return itemList.size();
    }
// hold references to the views
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView itemImageView;
        public TextView nameTextView, priceTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemImageView = itemView.findViewById(R.id.itemImageView);
            nameTextView = itemView.findViewById(R.id.nameTextView);
            priceTextView = itemView.findViewById(R.id.priceTextView);
        }
    }
}