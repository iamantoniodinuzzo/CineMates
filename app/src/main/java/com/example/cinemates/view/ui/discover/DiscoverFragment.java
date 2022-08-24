package com.example.cinemates.view.ui.discover;

import static com.example.cinemates.util.Constants.getRandomColor;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.example.cinemates.R;
import com.example.cinemates.databinding.FragmentDiscoverBinding;
import com.example.cinemates.model.data.Genre;
import com.example.cinemates.util.Constants;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import com.google.android.material.transition.MaterialFadeThrough;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;


public class DiscoverFragment extends Fragment {

    private FragmentDiscoverBinding mBinding;
    private final HashMap<Integer, String> mGenres = Constants.getGenreMap();
    private Random mRnd;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mRnd = new Random();
        setupMotionAnimations();
    }

    private void setupMotionAnimations() {
        setEnterTransition(new MaterialFadeThrough());
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mBinding = FragmentDiscoverBinding.inflate(inflater, container, false);
        populateChipGroup();
        return mBinding.getRoot();
    }

    private void populateChipGroup() {
        ChipGroup chipGroup = mBinding.chipGroup;
        chipGroup.removeAllViews();
        for (Map.Entry<Integer, String> genre : mGenres.entrySet()) {
            Chip genre_chip = new Chip(getContext());
            genre_chip.setId(genre.getKey());
            genre_chip.setText(genre.getValue());
            genre_chip.setTextColor(Color.WHITE);
            genre_chip.setChipBackgroundColor(ColorStateList.valueOf(getRandomColor()));
            genre_chip.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Genre tmp_genre = new Genre(genre.getKey(), genre.getValue());
                    DiscoverFragmentDirections.ActionDiscoverFragmentToFilterFragment action = DiscoverFragmentDirections.actionDiscoverFragmentToFilterFragment(tmp_genre);
                    Navigation.findNavController(view).navigate(action);
                }
            });
            chipGroup.addView(genre_chip);
        }
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mBinding.searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_discoverFragment_to_searchFragment);
            }
        });

    }




    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mBinding = null;
    }
}