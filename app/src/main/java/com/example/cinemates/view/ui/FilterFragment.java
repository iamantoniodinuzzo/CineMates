package com.example.cinemates.view.ui;

import static com.example.cinemates.util.Constants.getRandomColor;

import android.app.Dialog;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.CompoundButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.cinemates.R;
import com.example.cinemates.adapter.ItemsRecyclerViewAdapter;
import com.example.cinemates.model.data.Genre;
import com.example.cinemates.model.data.Movie;
import com.example.cinemates.util.Sort;
import com.example.cinemates.util.ViewSize;
import com.example.cinemates.view.viewmodel.MovieViewModel;
import com.google.android.material.chip.Chip;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class FilterFragment extends Fragment {

    private FragmentFilterBinding mBinding;
    private Genre mGenre;
    private ArrayList<Movie> filtered_list;
    private MovieViewModel mViewModel;
    private CompoundButton.OnCheckedChangeListener mCheckedChangeListener;
//    private MovieRecyclerViewAdapter mAdapter;
    private ItemsRecyclerViewAdapter<Movie> mAdapter;
    private MutableLiveData<String> mSortObservable;
    private final Random rand = new Random();


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mGenre = FilterFragmentArgs.fromBundle(getArguments()).getGenre();
        mViewModel = new ViewModelProvider(getActivity()).get(MovieViewModel.class);
        mAdapter = new ItemsRecyclerViewAdapter<>(ViewSize.SMALL);
        mSortObservable = new MutableLiveData<>();
        mSortObservable.setValue(Sort.POPULARITY.getAttribute());//Default value of sorting chips
        filtered_list = new ArrayList<>();
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
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mBinding = FragmentFilterBinding.inflate(inflater, container, false);
        mBinding.setTitle(mGenre.getName());


        populateSortingChips();

        return mBinding.getRoot();
    }

    private void populateSortingChips() {
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
                filtered_list.addAll(movies);
            }
        });
        mSortObservable.observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String sort) {
                mViewModel.getDiscoverMovies(sort, String.valueOf(mGenre.getId()));
            }
        });
        mBinding.shuffle.getRoot().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showCustomDialog();
            }
        });

    }

    private void showCustomDialog() {
        Movie movie = filtered_list.get(rand.nextInt(filtered_list.size()));
        final Dialog dialog = new Dialog(getContext(), R.style.AppDialogTheme);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(true);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        LayoutCustomDialogRandomBinding poster = LayoutCustomDialogRandomBinding.inflate(getLayoutInflater());
        poster.setMovie(movie);
        dialog.setContentView(poster.getRoot());
        poster.getRoot().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), MovieDetailsActivity.class);
                intent.putExtra("movie", movie);
                dialog.dismiss();
                view.getContext().startActivity(intent);

            }
        });
        dialog.show();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mBinding = null;
    }
}