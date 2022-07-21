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
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.ItemTouchHelper;

import com.example.cinemates.R;
import com.example.cinemates.adapter.SectionRecyclerViewAdapter;
import com.example.cinemates.databinding.FragmentHomeBinding;
import com.example.cinemates.model.Movie;
import com.example.cinemates.model.Section;
import com.example.cinemates.util.ItemMoveCallback;
import com.example.cinemates.util.MediaType;
import com.example.cinemates.util.TimeWindow;
import com.example.cinemates.viewmodel.MovieViewModel;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {


    private FragmentHomeBinding mBinding;
    private NavController mNavController;
    private Section<Movie> upcomingSection, topRatedSection, trendingSection;
    private Toolbar mToolbar;
    private MovieViewModel mViewModel;
    private SectionRecyclerViewAdapter<Movie> mAdapter;
    private List<Section<Movie>> mSectionList;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
            mAdapter = new SectionRecyclerViewAdapter<>(this, getContext());
            upcomingSection = new Section<>("Upcoming", null);
            topRatedSection = new Section<>("Top Rated", null);
            trendingSection = new Section<>("Trending this week", null);
            mSectionList = new ArrayList<>();
            mSectionList.add(upcomingSection);
            mSectionList.add(topRatedSection);
            mSectionList.add(trendingSection);

            mViewModel = new ViewModelProvider(getActivity()).get(MovieViewModel.class);

    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = FragmentHomeBinding.inflate(inflater, container, false);


        mNavController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment);
        AppBarConfiguration appBarConfiguration =
                new AppBarConfiguration.Builder(R.id.homeFragment, R.id.searchFragment).build();
        mToolbar = mBinding.toolbar;
        NavigationUI.setupWithNavController(mToolbar, mNavController, appBarConfiguration);

        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view,
                              @Nullable Bundle savedInstanceState) {
        ItemTouchHelper.Callback callback =
                new ItemMoveCallback(mAdapter);
        ItemTouchHelper touchHelper = new ItemTouchHelper(callback);
        touchHelper.attachToRecyclerView(mBinding.sectionRv);
        mBinding.sectionRv.setAdapter(mAdapter);
        mAdapter.addItems(mSectionList);

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
            }
        });

        mToolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if (item.getItemId() == R.id.searchFragment) {
                    mNavController.navigate(R.id.action_homeFragment_to_searchFragment);
                }
                return false;
            }
        });
    }

    private void observeData() {
        upcomingSection.setMutableLiveData(mViewModel.getUpcomingMoviesList());
        topRatedSection.setMutableLiveData(mViewModel.getTopRatedMoviesList());
        trendingSection.setMutableLiveData(mViewModel.getTrendingMovieList());

    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mBinding = null;
    }
}