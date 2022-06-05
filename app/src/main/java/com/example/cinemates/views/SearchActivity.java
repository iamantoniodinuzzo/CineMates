package com.example.cinemates.views;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cinemates.R;
import com.example.cinemates.adapter.FragmentSearchAdapter;
import com.example.cinemates.adapter.MovieDetailsViewPagerAdapter;
import com.example.cinemates.adapter.ViewPagerAdapter;
import com.example.cinemates.databinding.ActivitySearchBinding;
import com.example.cinemates.views.fragment.SearchActorFragment;
import com.example.cinemates.views.fragment.SearchMovieFragment;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class SearchActivity extends AppCompatActivity {

    private ActivitySearchBinding mBinding;
    //    private FragmentSearchAdapter mAdapter;
    private LinearLayoutManager mLinearLayoutManager;
    private GridLayoutManager mGridLayoutManager;
    private SearchMovieFragment searchMovieFragment;
    private SearchActorFragment searchActorsFragment;
    private boolean layoutGrid = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivitySearchBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());
        mLinearLayoutManager = new LinearLayoutManager(this);
        mGridLayoutManager = new GridLayoutManager(this, 3);
        searchMovieFragment = new SearchMovieFragment();
        searchActorsFragment = new SearchActorFragment();

        setupAppBar();
        setupTabLayout();

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
                Navigation.findNavController(view).popBackStack();
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
//                searchActorsFragment.bindData(newText);
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
    }


    private void setupTabLayout() {
      /*  TabLayout tabLayout = mBinding.tabLayout;
        new TabLayoutMediator(tabLayout, mBinding.viewPager, new TabLayoutMediator.TabConfigurationStrategy() {
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
        }).attach();*/

        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), 0);

        viewPagerAdapter.addFragment(searchMovieFragment, "Movies");
        viewPagerAdapter.addFragment(searchActorsFragment, "Actors");
        mBinding.viewPager.setAdapter(viewPagerAdapter);

        mBinding.tabLayout.setupWithViewPager(mBinding.viewPager);
    }

    private void setupAppBar() {
      /*  setSupportActionBar(mBinding.toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }*/
       /* NavController navController = Navigation.findNavController(view);
        AppBarConfiguration appBarConfiguration =
                new AppBarConfiguration.Builder(R.id.searchFragment).build();

        NavigationUI.setupWithNavController(mBinding.toolbar, navController, appBarConfiguration);*/
    }

}