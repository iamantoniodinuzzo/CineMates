package com.example.cinemates.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.cinemates.views.fragment.SeenTabFragment;
import com.example.cinemates.views.fragment.ToSeeTabFragment;

/**
 * @author Antonio Di Nuzzo
 * Created 05/12/2021 at 09:56
 */
public class FragmentStandardListsAdapter extends FragmentStateAdapter {
    public FragmentStandardListsAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 1:
                return new ToSeeTabFragment();
            case 2:
                return new SeenTabFragment();
        }
        return new ToSeeTabFragment();
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
