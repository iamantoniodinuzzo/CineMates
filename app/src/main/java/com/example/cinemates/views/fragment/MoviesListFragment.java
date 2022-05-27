package com.example.cinemates.views.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.cinemates.R;
import com.example.cinemates.adapter.MovieRecyclerViewAdapter;
import com.example.cinemates.databinding.FragmentMoviesListBinding;
import com.example.cinemates.model.Collection;
import com.example.cinemates.util.Constants;
import com.example.cinemates.viewmodel.MovieViewModel;

import java.util.HashMap;

public class MoviesListFragment extends Fragment {

    private FragmentMoviesListBinding mBinding;
    private MovieRecyclerViewAdapter mAdapter;
    private MovieViewModel mViewModel;
    private Collection mCollection;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAdapter = new MovieRecyclerViewAdapter();
        mViewModel = new ViewModelProvider(getActivity()).get(MovieViewModel.class);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mBinding = FragmentMoviesListBinding.inflate(inflater, container, false);
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mBinding.recyclerviewMovies.setAdapter(mAdapter);
//        mCollection = MoviesListFragmentArgs.fromBundle(getArguments()).getCollection();
        mBinding.setCollection(mCollection);

        HashMap<String, String> map = new HashMap<>();
        map.put("api_key", Constants.API_KEY);
        map.put("page", "1");

        mViewModel.getCollection().observe(getViewLifecycleOwner(), new Observer<Collection>() {
            @Override
            public void onChanged(Collection collection) {
                mAdapter.addItems(collection.getParts());

            }
        });
        mViewModel.getCollection(mCollection.getId(), map);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mBinding = null;
    }
}