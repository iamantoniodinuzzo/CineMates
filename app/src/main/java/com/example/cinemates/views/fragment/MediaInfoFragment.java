package com.example.cinemates.views.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.cinemates.R;
import com.example.cinemates.databinding.FragmentMediaInfoBinding;
import com.example.cinemates.model.Genre;
import com.example.cinemates.model.Movie;
import com.example.cinemates.util.Constants;
import com.example.cinemates.viewmodel.MovieViewModel;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipDrawable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class MediaInfoFragment extends Fragment {

    private FragmentMediaInfoBinding mBinding;
    private MovieViewModel mViewModel;
    private Movie mMovie;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewModel = new ViewModelProvider(getActivity()).get(MovieViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mBinding = FragmentMediaInfoBinding.inflate(inflater, container, false);
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mMovie = (Movie) getArguments().getSerializable("movie");

        HashMap<String, String> map = new HashMap<>();
        map.put("api_key", Constants.API_KEY);
        map.put("page", "1");

        mViewModel.getMovie().observe(getViewLifecycleOwner(), new Observer<Movie>() {
            @Override
            public void onChanged(Movie movie) {
                mBinding.setMovie(movie);
                mMovie = movie;
                System.out.println(movie);
            }
        });
        mViewModel.getMovieDetails(mMovie.getId(), map);
    }

    private void setGenresChip() {
        /*for (Genre genre : mGenres) {
            Chip chip = new Chip(getContext());
            ChipDrawable drawable = ChipDrawable.createFromAttributes(getContext(), null,
                    0, R.style.Widget_MaterialComponents_Chip_Action);
            chip.setChipDrawable(drawable);
            chip.setText(genre.getName());
            chip.setId(genre.getId());
            chip.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    Toast.makeText(getContext(), compoundButton.getText(), Toast.LENGTH_SHORT).show();
                }
            });
            mBinding.chipGroupGenres.addView(chip);
        }*/
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mBinding = null;

    }
}