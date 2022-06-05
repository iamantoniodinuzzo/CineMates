package com.example.cinemates.views.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.cinemates.R;
import com.example.cinemates.adapter.MovieRecyclerViewAdapter;
import com.example.cinemates.databinding.FragmentHomeBinding;
import com.example.cinemates.model.Movie;
import com.example.cinemates.model.Section;
import com.example.cinemates.util.Constants;
import com.example.cinemates.util.MediaType;
import com.example.cinemates.util.TimeWindow;
import com.example.cinemates.viewmodel.MovieViewModel;
import com.example.cinemates.views.MovieDetailsActivity;
import com.example.cinemates.views.SearchActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class HomeFragment extends Fragment {


    private FragmentHomeBinding mBinding;
    private NavController mNavController;
    private MovieRecyclerViewAdapter mUpcomingRecyclerViewAdapter, mTopRatedRecyclerViewAdapter, mTrendingRecyclerViewAdapter;
    private Toolbar mToolbar;
    private MovieViewModel mViewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mUpcomingRecyclerViewAdapter = new MovieRecyclerViewAdapter();
        mTopRatedRecyclerViewAdapter = new MovieRecyclerViewAdapter();
        mTrendingRecyclerViewAdapter = new MovieRecyclerViewAdapter();
        mViewModel = new ViewModelProvider(this).get(MovieViewModel.class);

    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = FragmentHomeBinding.inflate(inflater, container, false);


        mNavController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment);
        AppBarConfiguration appBarConfiguration =
                new AppBarConfiguration.Builder(mNavController.getGraph()).build();
        mToolbar = mBinding.toolbar;
        NavigationUI.setupWithNavController(mToolbar, mNavController, appBarConfiguration);

        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view,
                              @Nullable Bundle savedInstanceState) {

        mBinding.topratedRecyclerView.setAdapter(mTopRatedRecyclerViewAdapter);
        mBinding.upcomingRecyclerView.setAdapter(mUpcomingRecyclerViewAdapter);
        mBinding.trendingRecyclerView.setAdapter(mTrendingRecyclerViewAdapter);

        observeData();
        mViewModel.getCurrentlyShowingMovies();
        mViewModel.getUpcomingMovies();
        mViewModel.getTopRatedMovies();
        mViewModel.getPopularMovies();
        mViewModel.getTrendingMovies(MediaType.MOVIE, TimeWindow.WEEK);


        mBinding.imageProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO open drawer
                Navigation.findNavController(view).navigate(R.id.action_homeFragment_to_accountFragment);
            }
        });

        mToolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if (item.getItemId() == R.id.discoverFragment) {
//                    Navigation.findNavController(view).navigate(R.id.action_homeFragment_to_searchActivity);
                    Intent intent = new Intent(view.getContext(), SearchActivity.class);
                    view.getContext().startActivity(intent);
                }
                return false;
            }
        });
    }

    private void observeData() {
        mViewModel.getUpcomingMoviesList().observe(getViewLifecycleOwner(), new Observer<ArrayList<Movie>>() {
            @Override
            public void onChanged(ArrayList<Movie> movies) {
                mUpcomingRecyclerViewAdapter.addItems(movies);
            }
        });
        mViewModel.getTopRatedMoviesList().observe(getViewLifecycleOwner(), new Observer<ArrayList<Movie>>() {
            @Override
            public void onChanged(ArrayList<Movie> movies) {
                mTopRatedRecyclerViewAdapter.addItems(movies);
            }
        });
        mViewModel.getTrendingMovieList().observe(getViewLifecycleOwner(), new Observer<ArrayList<Movie>>() {
            @Override
            public void onChanged(ArrayList<Movie> movies) {
                System.out.println(movies);
                mTrendingRecyclerViewAdapter.addItems(movies);
            }
        });

    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mBinding = null;
    }
}