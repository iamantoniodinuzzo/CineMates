package com.example.cinemates.view.ui.details.movie;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.cinemates.adapter.ViewPagerAdapter;
import com.example.cinemates.databinding.FragmentMovieDetailsBinding;
import com.example.cinemates.model.data.Movie;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;


/**
 * @author Antonio Di Nuzzo
 * Created 26/05/2022 at 15:44
 */
public class MovieDetailsFragment extends Fragment {
    public static FragmentManager fragmentManager;
    private FragmentMovieDetailsBinding mBinding;
    private MovieDetailsFragmentArgs args;
    private Movie mMovie;
    private MovieInfoFragment mMovieInfoFragment;
    private MovieCastFragment mMovieCastFragment;
    private MovieImagesFragment mMovieImagesFragment;
    private ViewPagerAdapter mViewPagerAdapter;
    private Bundle mBundle;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fragmentManager = getActivity().getSupportFragmentManager();

        args = MovieDetailsFragmentArgs.fromBundle(requireArguments());
        mViewPagerAdapter = new ViewPagerAdapter(getActivity().getSupportFragmentManager(), getLifecycle());
        mMovieInfoFragment = new MovieInfoFragment();
        mMovieCastFragment = new MovieCastFragment();
        mMovieImagesFragment = new MovieImagesFragment();
        mBundle = new Bundle();

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = FragmentMovieDetailsBinding.inflate(inflater, container, false);

        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mMovie = args.getMovie();
        mBinding.setMovie(mMovie);
        mBundle.putSerializable("movie", mMovie);
        mBinding.toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().onBackPressed();
            }
        });

        mMovieCastFragment.setArguments(mBundle);
        mMovieInfoFragment.setArguments(mBundle);
        mMovieImagesFragment.setArguments(mBundle);


        initializeViewPager();

        /*mBinding.toolbar.setNavigationOnClickListener((view1) -> {
            Navigation.findNavController(view).navigateUp();
        });*/


    }

    private void initializeViewPager() {
        mViewPagerAdapter.addFragment(mMovieInfoFragment);
        mViewPagerAdapter.addFragment(mMovieCastFragment);
        mViewPagerAdapter.addFragment(mMovieImagesFragment);

        mBinding.viewPager.setAdapter(mViewPagerAdapter);
        mBinding.viewPager.setUserInputEnabled(false);

        new TabLayoutMediator(mBinding.tabLayout, mBinding.viewPager, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                switch (position) {
                    case 0:
                        tab.setText("Info");
                        break;
                    case 1:
                        tab.setText("Cast");
                        break;
                    case 2:
                        tab.setText("Images");
                        break;
                }
            }
        }).attach();

    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mBinding = null;
    }
}
