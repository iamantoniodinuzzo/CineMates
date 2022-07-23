package com.example.cinemates.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.cinemates.adapter.MovieRecyclerViewAdapter;
import com.example.cinemates.databinding.FragmentFilterBinding;
import com.example.cinemates.model.Genre;
import com.example.cinemates.model.Movie;
import com.example.cinemates.viewmodel.MovieViewModel;

import java.util.ArrayList;

public class FilterFragment extends Fragment {

    private FragmentFilterBinding mBinding;
    private Genre mGenre;
    private ArrayList<Movie> filtered_list ;
    private MovieViewModel mViewModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mGenre = FilterFragmentArgs.fromBundle(getArguments()).getGenre();
        mViewModel = new ViewModelProvider(getActivity()).get(MovieViewModel.class);
        filtered_list = new ArrayList<>();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mBinding = FragmentFilterBinding.inflate(inflater, container, false);
        mBinding.setTitle(mGenre.getName());
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

        mBinding.recyclerView.setAdapter(new MovieRecyclerViewAdapter());

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mBinding = null;
    }
}