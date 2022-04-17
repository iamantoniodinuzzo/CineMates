package com.example.cinemates.views.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.cinemates.R;
import com.example.cinemates.adapter.SectionRecyclerViewAdapter;
import com.example.cinemates.databinding.FragmentHomeBinding;
import com.example.cinemates.model.Movie;
import com.example.cinemates.model.Section;

import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends Fragment {


    private FragmentHomeBinding mBinding;
    private NavController mNavController;
    private List<Section> mSectionList = new ArrayList<>();
    private SectionRecyclerViewAdapter mSectionRecyclerViewAdapter;
    private Toolbar mToolbar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();

        mSectionRecyclerViewAdapter = new SectionRecyclerViewAdapter();
        mSectionRecyclerViewAdapter.addItems(mSectionList);

    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = FragmentHomeBinding.inflate(inflater, container, false);
        mBinding.recyclerView.setAdapter(mSectionRecyclerViewAdapter);

        mNavController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment);
        AppBarConfiguration appBarConfiguration =
                new AppBarConfiguration.Builder(mNavController.getGraph()).build();
        mToolbar = mBinding.toolbar;
        NavigationUI.setupWithNavController(mToolbar, mNavController, appBarConfiguration);

        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view,
                              @Nullable Bundle savedInstanceState) {

        mBinding.imageProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO open drawer
                Navigation.findNavController(view).navigate(R.id.action_homeFragment_to_accountFragment);
            }
        });

        mToolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if(item.getItemId() == R.id.discoverFragment)
                    Navigation.findNavController(view).navigate(R.id.action_homeFragment_to_discoverFragment);
                return false;
            }
        });
    }


    private void initData() {
        String sectionOneName = "Tendenze";
        List<Movie> sectionOneItems = new ArrayList<>();
        //addItems
        String sectionTwoName = "Al Cinema";
        List<Movie> sectionTwoItems = new ArrayList<>();
        //addItems
        String sectionThreeName = "In Arrivo";
        List<Movie> sectionThreeItems = new ArrayList<>();
        //addItems

        mSectionList.add(new Section(sectionOneName, sectionOneItems));
        mSectionList.add(new Section(sectionTwoName, sectionTwoItems));
        mSectionList.add(new Section(sectionThreeName, sectionThreeItems));
    }



    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mBinding = null;
    }
}