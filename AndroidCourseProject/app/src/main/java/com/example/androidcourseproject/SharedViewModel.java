package com.example.androidcourseproject;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

public class SharedViewModel extends ViewModel {


    public static class Item {
        public String name;
        public int quantity;
        public boolean bought;
        public boolean favorite;

        public Item(String name, int quantity) {
            this.name = name;
            this.quantity = quantity;
            this.bought = false;
            this.favorite = false;
        }
    }

    private final MutableLiveData<List<Item>> items = new MutableLiveData<>(new ArrayList<>());
    private final MutableLiveData<List<Item>> favorites = new MutableLiveData<>(new ArrayList<>());

    public LiveData<List<Item>> getItems() {
        return items;
    }
    public LiveData<List<Item>> getFavorites() {
        return favorites;
    }
    public void addItem(String name, int quantity) {
        List<Item> current = items.getValue();
        current.add(new Item(name, quantity));
        items.setValue(current);
        Log.d("SharedViewModel", "Adding item: " + name + " " + quantity);


    }

    public void removeItem(Item item) {
        List<Item> current = items.getValue();
        current.remove(item);
        items.setValue(current);
    }

    public void toggleFavorite(Item item) {
        List<Item> favs = favorites.getValue();
        if (item.favorite) {
            item.favorite = false;
            favs.remove(item);
        } else {
            item.favorite = true;
            favs.add(item);
        }
        favorites.setValue(favs);
        items.setValue(items.getValue()); // force update
    }

    public int getTotalCount() {
        return items.getValue().size();
    }

    public int getFavoriteCount() {
        int count = 0;
        for (Item i : items.getValue()) {
            if (i.favorite) count++;
        }
        return count;
    }




    public int getRemainingCount() {
        int count = 0;
        for (Item i : items.getValue()) {
            if (!i.bought) count++;
        }
        return count;
    }
}
