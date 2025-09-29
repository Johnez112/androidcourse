package com.example.androidcourseproject.ui.notifications;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.androidcourseproject.SharedViewModel;
import com.example.androidcourseproject.databinding.FragmentNotificationsBinding;

public class NotificationsFragment extends Fragment {

    private FragmentNotificationsBinding binding;
    private SharedViewModel sharedViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        sharedViewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);

        binding = FragmentNotificationsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        TextView totalCount = binding.textTotalCount;
        TextView favoriteCount = binding.textFavoriteCount;


        sharedViewModel.getItems().observe(getViewLifecycleOwner(), items -> {
            totalCount.setText("Tuotteita yhteensä: " + sharedViewModel.getTotalCount());
            favoriteCount.setText("Suosikkituotteita yhteensä: " + sharedViewModel.getFavoriteCount());
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
