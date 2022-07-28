package com.example.cinemates.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.cinemates.adapter.ItemsRecyclerViewAdapter;
import com.example.cinemates.databinding.FragmentMediaCastBinding;
import com.example.cinemates.model.Cast;
import com.example.cinemates.model.Movie;
import com.example.cinemates.util.ViewSize;
import com.example.cinemates.viewmodel.MovieViewModel;

import java.util.List;


public class MediaCastFragment extends Fragment {
    private FragmentMediaCastBinding mBinding;
    private ItemsRecyclerViewAdapter<Cast> mAdapter;
    private MovieViewModel mViewModel;
    private Movie mMovie;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAdapter = new ItemsRecyclerViewAdapter<>(ViewSize.LONG);
        mViewModel = new ViewModelProvider(getActivity()).get(MovieViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mBinding = FragmentMediaCastBinding.inflate(inflater, container, false);

        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mMovie = (Movie) getArguments().getSerializable("movie");

        mBinding.castRecyclerView.setAdapter(mAdapter);

        mViewModel.getMovieCastList().observe(getViewLifecycleOwner(), new Observer<List<Cast>>() {
            @Override
            public void onChanged(List<Cast> casts) {
                mAdapter.addItems(casts);
            }
        });
        mViewModel.getCast(mMovie.getId());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mBinding = null;
    }
}