package com.example.cinemates.views.fragment;

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
import com.example.cinemates.viewmodel.MovieViewModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class HomeFragment extends Fragment {


    private FragmentHomeBinding mBinding;
    private NavController mNavController;
    private MovieRecyclerViewAdapter mPopularRecyclerViewAdapter, mCurrentRecyclerViewAdapter, mUpcomingRecyclerViewAdapter, mTopRatedRecyclerViewAdapter;
    private Toolbar mToolbar;
    private MovieViewModel mViewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPopularRecyclerViewAdapter = new MovieRecyclerViewAdapter();
        mCurrentRecyclerViewAdapter = new MovieRecyclerViewAdapter();
        mUpcomingRecyclerViewAdapter = new MovieRecyclerViewAdapter();
        mTopRatedRecyclerViewAdapter = new MovieRecyclerViewAdapter();
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


        mBinding.popularRecyclerView.setAdapter(mPopularRecyclerViewAdapter);
        mBinding.currentRecyclerView.setAdapter(mCurrentRecyclerViewAdapter);
        mBinding.topratedRecyclerView.setAdapter(mTopRatedRecyclerViewAdapter);
        mBinding.upcomingRecyclerView.setAdapter(mUpcomingRecyclerViewAdapter);

        HashMap<String, String> map = new HashMap<>();
        map.put("api_key", Constants.API_KEY);
        map.put("page", "1");

        observeData();
        mViewModel.getCurrentlyShowingMovies(map);
        mViewModel.getUpcomingMovies(map);
        mViewModel.getTopRatedMovies(map);
        mViewModel.getPopularMovies(map);


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
                if (item.getItemId() == R.id.discoverFragment)
                    Navigation.findNavController(view).navigate(R.id.action_homeFragment_to_discoverFragment);
                return false;
            }
        });
    }

    private void observeData() {
        mViewModel.getCurrentlyShowingList().observe(getViewLifecycleOwner(), new Observer<ArrayList<Movie>>() {
            @Override
            public void onChanged(ArrayList<Movie> movies) {
                mCurrentRecyclerViewAdapter.addItems(movies);
            }
        });
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
        mViewModel.getPopularMoviesList().observe(getViewLifecycleOwner(), new Observer<ArrayList<Movie>>() {
            @Override
            public void onChanged(ArrayList<Movie> movies) {
                mPopularRecyclerViewAdapter.addItems(movies);
            }
        });

    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mBinding = null;
    }
}