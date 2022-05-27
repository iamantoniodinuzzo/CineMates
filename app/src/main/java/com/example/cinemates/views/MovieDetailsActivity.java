package com.example.cinemates.views;

import android.app.Activity;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.Navigation;

import com.example.cinemates.R;
import com.example.cinemates.databinding.ActivityMovieDetailsBinding;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class MovieDetailsActivity extends AppCompatActivity {
    private ActivityMovieDetailsBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivityMovieDetailsBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());

        Navigation.findNavController(this,  R.id.nav_host_fragment_container).setGraph(R.navigation.movie_details_nav_graph, getIntent().getExtras());
    }
}