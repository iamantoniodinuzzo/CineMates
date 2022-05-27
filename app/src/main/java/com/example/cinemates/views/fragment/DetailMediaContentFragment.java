package com.example.cinemates.views.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;

import com.example.cinemates.adapter.ViewPagerAdapter;
import com.example.cinemates.databinding.FragmentDetailMediaContentBinding;
import com.example.cinemates.model.Movie;
import com.google.android.material.tabs.TabLayout;

import dagger.hilt.android.AndroidEntryPoint;

/**
 * @author Antonio Di Nuzzo
 * Created 26/05/2022 at 15:44
 */
@AndroidEntryPoint
public class DetailMediaContentFragment extends Fragment {
    public static FragmentManager fragmentManager;
    private FragmentDetailMediaContentBinding mBinding;
    private Movie mMovie;
    private int indicatorWidth;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fragmentManager = getActivity().getSupportFragmentManager();

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
        mMovie = DetailMediaContentFragmentArgs.fromBundle(getArguments()).getMovie();
        mBinding.setMovie(mMovie);


        bindViewPagerAdapter(mBinding.viewPager);
        bindViewPagerTabs(mBinding.tabLayout, mBinding.viewPager);
        tabSettings();
    }


    public void bindViewPagerAdapter(final ViewPager view) {
        final ViewPagerAdapter adapter = new ViewPagerAdapter(view.getContext(), fragmentManager, mMovie);
        view.setAdapter(adapter);
    }

    public void bindViewPagerTabs(final TabLayout view, final ViewPager pagerView) {
        view.setupWithViewPager(pagerView, true);

    }

    private void tabSettings() {
        mBinding.tabLayout.post(() -> {
            indicatorWidth = mBinding.tabLayout.getWidth() / mBinding.tabLayout.getTabCount();/*
            FrameLayout.LayoutParams indicatorParams = (FrameLayout.LayoutParams) mBinding.indicator.getLayoutParams();
            indicatorParams.width = indicatorWidth;
            binding.indicator.setLayoutParams(indicatorParams);*/
        });

        mBinding.viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetP) {
               /* FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) binding.indicator.getLayoutParams();
                float translationOffset = (positionOffset + position) * indicatorWidth;
                params.leftMargin = (int) translationOffset;
                binding.indicator.setLayoutParams(params);*/
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mBinding = null;
    }
}
