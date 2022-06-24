package com.example.cinemates.views.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cinemates.R;
import com.example.cinemates.adapter.ViewPagerAdapter;
import com.example.cinemates.databinding.FragmentSearchBinding;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class SearchFragment extends Fragment {

    private FragmentSearchBinding mBinding;
    private LinearLayoutManager mLinearLayoutManager;
    private GridLayoutManager mGridLayoutManager;
    private SearchMovieFragment searchMovieFragment;
    private SearchActorFragment searchActorsFragment;
    private boolean layoutGrid = false;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mLinearLayoutManager = new LinearLayoutManager(getContext());
        mGridLayoutManager = new GridLayoutManager(getContext(), 3);
        searchMovieFragment = new SearchMovieFragment();
        searchActorsFragment = new SearchActorFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = FragmentSearchBinding.inflate(getLayoutInflater());

        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setupTabLayout();
        updateToolbar();

        // Listen menu item click and change layout into recyclerview
        // owned by SearchActor & SearchMovie fragment
        mBinding.toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.menu_switch_grid:
                        switchLayout(mGridLayoutManager);
                        break;
                    case R.id.menu_switch_list:
                        switchLayout(mLinearLayoutManager);
                        break;
                }
                updateToolbar();
                return false;
            }
        });

        mBinding.toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().onBackPressed();
            }
        });

        //Send query to SearchActor & SearchMovie
        mBinding.searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                //NOTE send this query to Search actor & Search movie fragment
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                searchMovieFragment.bindData(newText);
                searchActorsFragment.bindData(newText);
                return false;
            }
        });
    }


    /**
     * Change menu icon in toolbar showing list or grid view
     */
    private void updateToolbar() {
        layoutGrid = !layoutGrid;

        MenuItem gridView = mBinding.toolbar.getMenu().findItem(R.id.menu_switch_grid);
        gridView.setVisible(layoutGrid);
        MenuItem listView = mBinding.toolbar.getMenu().findItem(R.id.menu_switch_list);
        listView.setVisible(!layoutGrid);
    }


    private void switchLayout(RecyclerView.LayoutManager layoutManager) {
        searchMovieFragment.changeLayout(layoutManager);
        searchActorsFragment.changeLayout(layoutManager);
    }


    private void setupTabLayout() {
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getActivity().getSupportFragmentManager(), getLifecycle());

        viewPagerAdapter.addFragment(searchMovieFragment);
        viewPagerAdapter.addFragment(searchActorsFragment);
        mBinding.viewPager.setAdapter(viewPagerAdapter);
        new TabLayoutMediator(mBinding.tabLayout, mBinding.viewPager, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                switch (position) {
                    case 0:
                        tab.setText("Movies");
                        break;
                    case 1:
                        tab.setText("Actors");
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