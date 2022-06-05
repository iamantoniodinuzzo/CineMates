package com.example.cinemates.adapter;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.cinemates.model.Movie;
import com.example.cinemates.views.fragment.MediaCastFragment;
import com.example.cinemates.views.fragment.MediaInfoFragment;
import com.example.cinemates.views.fragment.MediaReviewsFragment;

import java.util.ArrayList;
import java.util.List;

public class MovieDetailsViewPagerAdapter extends FragmentStatePagerAdapter {
    private static final int FIRST_TAB = 0;
    private static final int SECOND_TAB = 1;
    private static final int THIRD_TAB = 3;
    private int[] TABS;
    private Context mContext;
    private Movie mMovie;

    private final List<Fragment> fragments = new ArrayList<>();
    private final List<String> fragmentTitles = new ArrayList<>();

    public MovieDetailsViewPagerAdapter(final Context context, final FragmentManager fm, Movie movie) {
        super(fm);
        mContext = context.getApplicationContext();
        TABS = new int[]{FIRST_TAB, SECOND_TAB, THIRD_TAB};
        mMovie = movie;
    }


    public void addFragment(Fragment fragment, String title) {
        fragments.add(fragment);
        fragmentTitles.add(title);
    }


    @NonNull
    @Override
    public Fragment getItem(int position) {
        Bundle bundle = new Bundle();
        switch (TABS[position]) {
            case FIRST_TAB:
                bundle.putString("param1", "FIRST TAB");
                bundle.putSerializable("movie", mMovie);
                MediaInfoFragment mediaInfoFragment = new MediaInfoFragment();
                mediaInfoFragment.setArguments(bundle);
                return mediaInfoFragment;

            case SECOND_TAB:
                bundle.putString("param1", "SECOND TAB");
                bundle.putSerializable("movie", mMovie);
                MediaCastFragment mediaCastFragment = new MediaCastFragment();
                mediaCastFragment.setArguments(bundle);
                return mediaCastFragment;
            case THIRD_TAB:
                bundle.putString("param1", "THIRD TAB");
                bundle.putSerializable("movie", mMovie);
                MediaReviewsFragment mediaReviewsFragment = new MediaReviewsFragment();
                mediaReviewsFragment.setArguments(bundle);
                return mediaReviewsFragment;

        }
        return null;
    }

    @Override
    public int getCount() {
        return TABS.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (TABS[position]) {
            case FIRST_TAB:
                return "Info";
            case SECOND_TAB:
                return "Cast";
            case THIRD_TAB:
                return "Reviews";
        }
        return null;
    }
}
