package com.example.cinemates.views.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.cinemates.R;
import com.example.cinemates.adapter.MovieRecyclerViewAdapter;
import com.example.cinemates.databinding.FragmentDetailedViewBinding;


public class DetailedViewFragment extends Fragment {

    private FragmentDetailedViewBinding mBinding;
    private NavController mNavController;
    private LinearLayoutManager mLinearLayoutManager;
    private GridLayoutManager mGridLayoutManager;
    private MovieRecyclerViewAdapter mRecyclerViewAdapter;
    private boolean layoutGrid = false;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mRecyclerViewAdapter = new MovieRecyclerViewAdapter();
        mLinearLayoutManager = new LinearLayoutManager(getContext());
        mGridLayoutManager = new GridLayoutManager(getContext(), 3);
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mBinding = FragmentDetailedViewBinding.inflate(inflater, container, false);

        mBinding.recyclerView.setAdapter(mRecyclerViewAdapter);

        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        setupAppBar(view);

        mBinding.toolbar.setNavigationIcon(R.drawable.ic_outline_arrow_back_24);
        mBinding.toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_detailedViewFragment_to_homeFragment);
            }
        });

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
    }

    private void updateToolbar() {
        layoutGrid = !layoutGrid;

        MenuItem gridView = mBinding.toolbar.getMenu().findItem(R.id.menu_switch_grid);
        gridView.setVisible(layoutGrid);
        MenuItem listView = mBinding.toolbar.getMenu().findItem(R.id.menu_switch_list);
        listView.setVisible(!layoutGrid);
    }


    private void switchLayout(RecyclerView.LayoutManager layoutManager) {
        //TODO switch layout into recycler view
        mBinding.recyclerView.setLayoutManager(layoutManager);
        Toast.makeText(getContext(), "Layout cambiato", Toast.LENGTH_SHORT).show();

    }

    private void setupAppBar(@NonNull View view) {
        mNavController = Navigation.findNavController(view);
        AppBarConfiguration appBarConfiguration =
                new AppBarConfiguration.Builder(R.id.detailedViewFragment).build();
        Toolbar toolbar = mBinding.toolbar;

        NavigationUI.setupWithNavController(toolbar, mNavController, appBarConfiguration);

    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mBinding = null;
    }
}