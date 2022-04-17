package com.example.cinemates.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.cinemates.views.fragment.SearchActorFragment;
import com.example.cinemates.views.fragment.SearchMovieFragment;

/**
 * @author Antonio Di Nuzzo
 * Created 05/12/2021 at 09:56
 */
public class FragmentSearchAdapter extends FragmentStateAdapter {
    public FragmentSearchAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 1:
                return new SearchMovieFragment();
            case 2:
                return new SearchActorFragment();
        }
        return new SearchMovieFragment();
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
