package com.example.cinemates.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

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
import androidx.recyclerview.widget.RecyclerView;

import com.example.cinemates.R;
import com.example.cinemates.adapter.SectionRecyclerViewAdapter;
import com.example.cinemates.databinding.FragmentHomeBinding;
import com.example.cinemates.model.Cast;
import com.example.cinemates.model.Movie;
import com.example.cinemates.model.Section;
import com.example.cinemates.util.ItemMoveCallback;
import com.example.cinemates.util.MediaType;
import com.example.cinemates.util.TimeWindow;
import com.example.cinemates.viewmodel.MovieViewModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {


    private FragmentHomeBinding mBinding;
    private NavController mNavController;
    private Section<Movie> upcomingSection, topRatedSection, trendingSection;
    private Section<Cast> trendingPerson;
    private Toolbar mToolbar;
    private MovieViewModel mViewModel;
    private SectionRecyclerViewAdapter mAdapter;
    private List<Section<?>> mSectionList;
    private BottomNavigationView mBottomNavigationView;
    private Animation slideIn, slideOut;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAdapter = new SectionRecyclerViewAdapter(this, getContext());
        upcomingSection = new Section<>("Upcoming", Movie.class, null);
        topRatedSection = new Section<>("Top Rated", Movie.class, null);
        trendingSection = new Section<>("Movies Trending this week", Movie.class, null);
        trendingPerson = new Section<>("Person Trending this week", Cast.class, null);
        mSectionList = new ArrayList<>();
        mSectionList.add(upcomingSection);
        mSectionList.add(topRatedSection);
        mSectionList.add(trendingSection);
        mSectionList.add(trendingPerson);
        slideIn = AnimationUtils.loadAnimation(getContext(), R.anim.slide_in);
        slideOut = AnimationUtils.loadAnimation(getContext(), R.anim.slide_out);
        mViewModel = new ViewModelProvider(getActivity()).get(MovieViewModel.class);

    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = FragmentHomeBinding.inflate(inflater, container, false);

        mNavController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment);
        AppBarConfiguration appBarConfiguration =
                new AppBarConfiguration.Builder(mNavController.getGraph()).build();
        mToolbar = mBinding.toolbar;
        mBottomNavigationView = getActivity().findViewById(R.id.nav_view);

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
        mViewModel.getTrending(MediaType.MOVIE, TimeWindow.WEEK);
        mViewModel.getTrending(MediaType.PERSON, TimeWindow.WEEK);


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

        mBinding.sectionRv.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (dy > 0 && mBottomNavigationView.isShown()) {
                    mBottomNavigationView.startAnimation(slideOut);
                    mBottomNavigationView.setVisibility(View.INVISIBLE);
                } else if (dy < 0) {
                    mBottomNavigationView.startAnimation(slideIn);
                    mBottomNavigationView.setVisibility(View.VISIBLE);

                }
            }
        });
    }


    private void observeData() {
        upcomingSection.setMutableLiveData(mViewModel.getUpcomingMoviesList());
        topRatedSection.setMutableLiveData(mViewModel.getTopRatedMoviesList());
        trendingSection.setMutableLiveData(mViewModel.getTrendingMovieList());
        trendingPerson.setMutableLiveData(mViewModel.getTrendingPerson());

    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mBinding = null;
    }
}