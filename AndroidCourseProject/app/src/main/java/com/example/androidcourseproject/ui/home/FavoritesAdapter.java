package com.example.androidcourseproject.ui.home;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidcourseproject.R;
import com.example.androidcourseproject.SharedViewModel;

import java.util.ArrayList;
import java.util.List;

public class FavoritesAdapter extends RecyclerView.Adapter<FavoritesAdapter.FavViewHolder> {

    private List<SharedViewModel.Item> items = new ArrayList<>();
    private final OnItemClickListener listener;
    private final SharedViewModel sharedViewModel;

    public interface OnItemClickListener {
        void onItemClick(String productName);
    }

    public FavoritesAdapter(SharedViewModel sharedViewModel, OnItemClickListener listener) {
        this.sharedViewModel = sharedViewModel;
        this.listener = listener;
    }

    public void setItems(List<SharedViewModel.Item> newItems) {
        this.items = newItems;
        notifyDataSetChanged();
    }

    static class FavViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        FavViewHolder(View view) {
            super(view);
            name = view.findViewById(R.id.textFavName);
        }
    }

    @NonNull
    @Override
    public FavViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_favorite, parent, false);
        return new FavViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull FavViewHolder holder, int position) {
        SharedViewModel.Item item = items.get(position);
        holder.name.setText(item.name);
        holder.itemView.setOnClickListener(v -> listener.onItemClick(item.name));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
