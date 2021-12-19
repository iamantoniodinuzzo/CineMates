package com.example.cinemates.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
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
import java.util.Arrays;
import java.util.List;


public class HomeFragment extends Fragment {


    private FragmentHomeBinding mBinding;
    private NavController mNavController;
    private List<Section> mSectionList = new ArrayList<>();
    private SectionRecyclerViewAdapter mSectionRecyclerViewAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();

        mSectionRecyclerViewAdapter = new SectionRecyclerViewAdapter();


    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = FragmentHomeBinding.inflate(inflater, container, false);
        mSectionRecyclerViewAdapter.addItems(mSectionList);
        mBinding.recyclerView.setAdapter(mSectionRecyclerViewAdapter);


        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view,
                              @Nullable Bundle savedInstanceState) {
        setupAppBar(view);

        mBinding.imageProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_homeFragment_to_accountFragment);
            }
        });
    }


    private void setupAppBar(@NonNull View view) {
        mNavController = Navigation.findNavController(view);
        AppBarConfiguration appBarConfiguration =
                new AppBarConfiguration.Builder(R.id.homeFragment).build();
        Toolbar toolbar = mBinding.toolbar;

        NavigationUI.setupWithNavController(toolbar, mNavController, appBarConfiguration);

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