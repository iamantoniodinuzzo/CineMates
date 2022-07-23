package com.example.cinemates.ui.fragment;

import static com.example.cinemates.util.Constants.getRandomColor;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.cinemates.adapter.MovieRecyclerViewAdapter;
import com.example.cinemates.databinding.FragmentFilterBinding;
import com.example.cinemates.model.Genre;
import com.example.cinemates.model.Movie;
import com.example.cinemates.util.Sort;
import com.example.cinemates.viewmodel.MovieViewModel;
import com.google.android.material.chip.Chip;

import java.util.ArrayList;
import java.util.List;

public class FilterFragment extends Fragment {

    private FragmentFilterBinding mBinding;
    private Genre mGenre;
    private ArrayList<Movie> filtered_list;
    private MovieViewModel mViewModel;
    private CompoundButton.OnCheckedChangeListener mCheckedChangeListener;
    private MovieRecyclerViewAdapter mAdapter;
    private MutableLiveData<String> mSortObservable;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mGenre = FilterFragmentArgs.fromBundle(getArguments()).getGenre();
        mViewModel = new ViewModelProvider(getActivity()).get(MovieViewModel.class);
        mAdapter = new MovieRecyclerViewAdapter();
        mSortObservable = new MutableLiveData<>();
        mSortObservable.setValue(Sort.POPULARITY.getAttribute());
        filtered_list = new ArrayList<>();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mBinding = FragmentFilterBinding.inflate(inflater, container, false);
        mBinding.setTitle(mGenre.getName());
        mCheckedChangeListener = new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked) {
                    int index = compoundButton.getId();
                    Sort[] sorts = Sort.values();
                    mSortObservable.setValue(sorts[index].getAttribute());
                }
            }
        };

        for (Sort sort : Sort.values()) {
            Chip chip = new Chip(getContext());
            chip.setText(sort.toString());
            chip.setCheckable(true);
            chip.setId(sort.ordinal());
            chip.setTextColor(Color.WHITE);
            chip.setChipBackgroundColor(ColorStateList.valueOf(getRandomColor()));
            chip.setOnCheckedChangeListener(mCheckedChangeListener);
            mBinding.chipGroup.addView(chip);
            if (sort.ordinal() == Sort.POPULARITY.ordinal()) {
                chip.setChecked(true);
            }
        }

        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mBinding.toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().onBackPressed();
            }
        });
        mBinding.recyclerView.setAdapter(mAdapter);
        mViewModel.getFilteredMovies().observe(getViewLifecycleOwner(), new Observer<List<Movie>>() {
            @Override
            public void onChanged(List<Movie> movies) {
                mAdapter.addItems(movies);
            }
        });
        mSortObservable.observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String sort) {
                    mViewModel.getDiscoverMovies(sort, String.valueOf(mGenre.getId()));
            }
        });

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mBinding = null;
    }
}