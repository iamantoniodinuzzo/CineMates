package com.example.cinemates.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.cinemates.adapter.SectionRecyclerViewAdapter;
import com.example.cinemates.databinding.FragmentProfileBinding;
import com.example.cinemates.model.Movie;
import com.example.cinemates.model.Section;
import com.example.cinemates.util.ViewSize;
import com.example.cinemates.viewmodel.DbViewModel;

import java.util.ArrayList;
import java.util.List;

public class ProfileFragment extends Fragment {

    private FragmentProfileBinding mBinding;
    private Section<Movie> favorite_section;
    private DbViewModel mDbViewModel;
    private SectionRecyclerViewAdapter mAdapter;
    private List<Section<?>> mSectionList;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAdapter = new SectionRecyclerViewAdapter(this, getContext());
        favorite_section = new Section<>("Favorites","Movies",Movie.class, null, ViewSize.SMALL);
        mSectionList = new ArrayList<>();
        mSectionList.add(favorite_section);
        mDbViewModel = new ViewModelProvider(getActivity()).get(DbViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mBinding = FragmentProfileBinding.inflate(inflater, container, false);
        mBinding.recyclerView.setAdapter(mAdapter);
        mAdapter.addItems(mSectionList);
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        favorite_section.setMutableLiveData(mDbViewModel.getFavorite_movies());
        mDbViewModel.getAllFavorites();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mBinding = null;
    }
}