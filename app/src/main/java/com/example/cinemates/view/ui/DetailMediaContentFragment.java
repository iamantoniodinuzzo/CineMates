package com.example.cinemates.view.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.cinemates.adapter.ViewPagerAdapter;
import com.example.cinemates.databinding.FragmentDetailMediaContentBinding;
import com.example.cinemates.model.data.Movie;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import dagger.hilt.android.AndroidEntryPoint;

/**
 * @author Antonio Di Nuzzo
 * Created 26/05/2022 at 15:44
 */
@AndroidEntryPoint
public class DetailMediaContentFragment extends Fragment {
    public static FragmentManager fragmentManager;
    private FragmentDetailMediaContentBinding mBinding;
    private DetailMediaContentFragmentArgs args;
    private Movie mMovie;
    private MediaInfoFragment mMediaInfoFragment;
    private MediaCastFragment mMediaCastFragment;
    private MediaImagesFragment mMediaImagesFragment;
    private ViewPagerAdapter mViewPagerAdapter;
    private Bundle mBundle;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fragmentManager = getActivity().getSupportFragmentManager();
        args = DetailMediaContentFragmentArgs.fromBundle(requireArguments());
        mViewPagerAdapter = new ViewPagerAdapter(getActivity().getSupportFragmentManager(), getLifecycle());
        mMediaInfoFragment = new MediaInfoFragment();
        mMediaCastFragment = new MediaCastFragment();
        mMediaImagesFragment = new MediaImagesFragment();
        mBundle = new Bundle();

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = FragmentDetailMediaContentBinding.inflate(inflater, container, false);

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

        mMediaCastFragment.setArguments(mBundle);
        mMediaInfoFragment.setArguments(mBundle);
        mMediaImagesFragment.setArguments(mBundle);



        initializeViewPager();


    }

    private void initializeViewPager() {
        mViewPagerAdapter.addFragment(mMediaInfoFragment);
        mViewPagerAdapter.addFragment(mMediaCastFragment);
        mViewPagerAdapter.addFragment(mMediaImagesFragment);

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
