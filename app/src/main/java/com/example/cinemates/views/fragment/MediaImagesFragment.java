package com.example.cinemates.views.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.cinemates.adapter.ReviewRecyclerViewAdapter;
import com.example.cinemates.databinding.FargmentMediaImagesBinding;
import com.example.cinemates.model.Images;
import com.example.cinemates.model.Movie;
import com.example.cinemates.model.Review;
import com.example.cinemates.viewmodel.MovieViewModel;

import java.util.ArrayList;

/**
 * @author Antonio Di Nuzzo
 * Created 19/06/2022 at 09:54
 */
public class MediaImagesFragment extends Fragment {
    private FargmentMediaImagesBinding mBinding;
    private Movie mMovie;
    private MovieViewModel mViewModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewModel = new ViewModelProvider(getActivity()).get(MovieViewModel.class);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = FargmentMediaImagesBinding.inflate(inflater, container, false);
        mMovie = (Movie) getArguments().getSerializable("movie");

        mViewModel.getImages().observe(getViewLifecycleOwner(), new Observer<Images>() {
            @Override
            public void onChanged(Images images) {
                System.out.println(images);
            }
        });
        mViewModel.getImages(mMovie.getId());
        return mBinding.getRoot();
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mBinding = null;
    }
}
