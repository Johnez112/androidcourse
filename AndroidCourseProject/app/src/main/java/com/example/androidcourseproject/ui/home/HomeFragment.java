package com.example.androidcourseproject.ui.home;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidcourseproject.R;
import com.example.androidcourseproject.SharedViewModel;


import com.example.androidcourseproject.databinding.FragmentHomeBinding;

import java.util.Arrays;
import java.util.List;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;

    private SharedViewModel sharedViewModel;
    private FavoritesAdapter adapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        sharedViewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);
        EditText nameInput = root.findViewById(R.id.editTextName);
        EditText priceInput = root.findViewById(R.id.editTextPrice);
        Button addButton = root.findViewById(R.id.buttonAdd);

        addButton.setOnClickListener(v -> {
            String name = nameInput.getText().toString();
            String priceStr = priceInput.getText().toString();
            if (!name.isEmpty() && !priceStr.isEmpty()) {
                int quantity = Integer.parseInt(priceStr);
                sharedViewModel.addItem(name, quantity);
                nameInput.setText("");
                priceInput.setText("");
            }
        });

        RecyclerView recyclerView = binding.recyclerFavorites;
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new FavoritesAdapter(sharedViewModel, this::showQuantityDialog);
        recyclerView.setAdapter(adapter);

        sharedViewModel.getFavorites().observe(getViewLifecycleOwner(), items -> {
            adapter.setItems(items);
        });

        return root;
    }

    private void showQuantityDialog(String productName) {
        final List<String> quantities = Arrays.asList("1","2","3","4","5","6","7","8","9","10");

        new AlertDialog.Builder(getContext())
                .setTitle("Valitse kappalemäärä")
                .setItems(quantities.toArray(new String[0]), (dialog, which) -> {
                    int qty = Integer.parseInt(quantities.get(which));
                    sharedViewModel.addItem(productName, qty);
                })
                .setNegativeButton("Peruuta", null)
                .show();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}