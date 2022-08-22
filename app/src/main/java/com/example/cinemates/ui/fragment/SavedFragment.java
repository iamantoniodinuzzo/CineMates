package com.example.cinemates.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cinemates.R;
import com.example.cinemates.adapter.ViewPagerAdapter;
import com.example.cinemates.databinding.FragmentSavedBinding;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class SavedFragment extends Fragment {

    private FragmentSavedBinding mBinding;
    private ToSeeFragment toSeeFragment;
    private SeenFragment seenFragment;
    private LinearLayoutManager mLinearLayoutManager;
    private GridLayoutManager mGridLayoutManager;
    private boolean layoutGrid = false;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        toSeeFragment = new ToSeeFragment();
        seenFragment = new SeenFragment();
        mLinearLayoutManager = new LinearLayoutManager(getContext());
        mGridLayoutManager = new GridLayoutManager(getContext(), 3);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mBinding = FragmentSavedBinding.inflate(inflater, container, false);
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        updateToolbar();

        // Listen menu item click and change layout into recyclerview
        // owned by SearchActor & SearchMovie fragment
        mBinding.toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.menu_switch_grid:
                        switchLayout(mGridLayoutManager);
                        updateToolbar();

                        break;
                    case R.id.menu_switch_list:
                        switchLayout(mLinearLayoutManager);
                        updateToolbar();

                        break;
                    case R.id.menu_filter:
                        Toast.makeText(getContext(), "Soon!", Toast.LENGTH_SHORT).show();
                        break;
                }
                return false;
            }
        });

        setupTabLayout();
    }

    private void setupTabLayout() {
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getChildFragmentManager(), getLifecycle());

        viewPagerAdapter.addFragment(toSeeFragment);
        viewPagerAdapter.addFragment(seenFragment);
        mBinding.viewPager.setAdapter(viewPagerAdapter);
        new TabLayoutMediator(mBinding.tabLayout, mBinding.viewPager, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                switch (position) {
                    case 0:
                        tab.setText("To See");
                        break;
                    case 1:
                        tab.setText("Seen");
                        break;
                }
            }
        }).attach();
    }


    //Change menu icon in toolbar showing list or grid view

    private void updateToolbar() {
        layoutGrid = !layoutGrid;
        MenuItem gridView = mBinding.toolbar.getMenu().findItem(R.id.menu_switch_grid);
        gridView.setVisible(layoutGrid);
        MenuItem listView = mBinding.toolbar.getMenu().findItem(R.id.menu_switch_list);
        listView.setVisible(!layoutGrid);
    }


    private void switchLayout(RecyclerView.LayoutManager layoutManager) {
        Toast.makeText(getContext(), "Soon!", Toast.LENGTH_SHORT).show();
        toSeeFragment.changeLayout(layoutManager);
        seenFragment.changeLayout(layoutManager);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mBinding = null;
    }
}