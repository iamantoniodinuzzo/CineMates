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
import com.example.cinemates.adapter.SectionRecyclerViewAdapter;
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
    private List<Section> mSectionList;
    private SectionRecyclerViewAdapter mSectionRecyclerViewAdapter;
    private Toolbar mToolbar;
    private MovieViewModel mViewModel;
    private ArrayList<Movie> mCurrentMovies, mPopularMovies, mTopRatedMovies, mUpcomingMovies;
    private Section mSectionCurrent, mSectionTopRated, mSectionPopular, mSectionUpcoming;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mSectionRecyclerViewAdapter = new SectionRecyclerViewAdapter();
        mCurrentMovies = new ArrayList<>();
        mPopularMovies = new ArrayList<>();
        mTopRatedMovies = new ArrayList<>();
        mUpcomingMovies = new ArrayList<>();
        mSectionList = new ArrayList<>();
        mViewModel = new ViewModelProvider(this).get(MovieViewModel.class);
        mSectionCurrent = new Section("Al Cinema", mCurrentMovies);
        mSectionTopRated = new Section("Top Rated", mTopRatedMovies);
        mSectionPopular = new Section("Popolari", mPopularMovies);
        mSectionUpcoming = new Section("Prossimamente", mUpcomingMovies);
        mSectionList.add(mSectionCurrent);
        mSectionList.add(mSectionPopular);
        mSectionList.add(mSectionTopRated);
        mSectionList.add(mSectionUpcoming);
        mSectionRecyclerViewAdapter.addItems(mSectionList);
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


        mBinding.recyclerView.setAdapter(mSectionRecyclerViewAdapter);

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
                mSectionRecyclerViewAdapter.addItems(new Section("Al cinema", movies));


            }
        });
        mViewModel.getUpcomingMoviesList().observe(getViewLifecycleOwner(), new Observer<ArrayList<Movie>>() {
            @Override
            public void onChanged(ArrayList<Movie> movies) {
                mSectionRecyclerViewAdapter.addItems(new Section("Prossimamente", movies));


            }
        });
        mViewModel.getTopRatedMoviesList().observe(getViewLifecycleOwner(), new Observer<ArrayList<Movie>>() {
            @Override
            public void onChanged(ArrayList<Movie> movies) {
                mSectionRecyclerViewAdapter.addItems(new Section("Top Rated", movies));


            }
        });
        mViewModel.getPopularMoviesList().observe(getViewLifecycleOwner(), new Observer<ArrayList<Movie>>() {
            @Override
            public void onChanged(ArrayList<Movie> movies) {
                mSectionRecyclerViewAdapter.addItems(new Section("Popolari", movies));


            }
        });

    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mBinding = null;
    }
}