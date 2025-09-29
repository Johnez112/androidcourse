package com.example.androidcourseproject.ui.dashboard;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidcourseproject.R;
import com.example.androidcourseproject.SharedViewModel;

import java.util.ArrayList;
import java.util.List;


public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemViewHolder> {

    private List<SharedViewModel.Item> items = new ArrayList<>();
    private SharedViewModel sharedViewModel;

    public ItemAdapter(SharedViewModel sharedViewModel) {
        this.sharedViewModel = sharedViewModel;
    }

    public void setItems(List<SharedViewModel.Item> newItems) {
        this.items = newItems;
        notifyDataSetChanged();
    }

    static class ItemViewHolder extends RecyclerView.ViewHolder {
        TextView name, quantity;
        Button deleteButton, favButton;

        ItemViewHolder(View view) {
            super(view);
            name = view.findViewById(R.id.textName);
            quantity = view.findViewById(R.id.textQuantity);
            deleteButton = view.findViewById(R.id.buttonDelete);
            favButton = view.findViewById(R.id.buttonFavorite);
        }
    }

    @NonNull
    @Override
    public ItemAdapter.ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row, parent, false);
        return new ItemViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemAdapter.ItemViewHolder holder, int position) {
        SharedViewModel.Item item = items.get(position);
        holder.name.setText(item.name);
        holder.quantity.setText("x" + item.quantity);

        holder.deleteButton.setOnClickListener(v -> sharedViewModel.removeItem(item));

        holder.favButton.setText(item.favorite ? "⭐" : "☆");
        holder.favButton.setOnClickListener(v -> sharedViewModel.toggleFavorite(item));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}