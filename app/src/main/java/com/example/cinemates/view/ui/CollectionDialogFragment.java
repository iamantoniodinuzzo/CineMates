package com.example.cinemates.view.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.cinemates.adapter.ItemsRecyclerViewAdapter;
import com.example.cinemates.databinding.LayoutCollectionDialogBinding;
import com.example.cinemates.model.data.Collection;
import com.example.cinemates.model.data.Movie;
import com.example.cinemates.util.ViewSize;
import com.example.cinemates.view.viewmodel.MovieViewModel;

import dagger.hilt.android.AndroidEntryPoint;

/**
 * @author Antonio Di Nuzzo
 * Created 27/05/2022 at 17:56
 */

public class CollectionDialogFragment extends DialogFragment {
    //    private static Collection mCollection;
    private LayoutCollectionDialogBinding mBinding;
//    private MovieRecyclerViewAdapter mAdapter;
    private ItemsRecyclerViewAdapter<Movie> mAdapter;
    private MovieViewModel mViewModel;

    public CollectionDialogFragment() {
    }

    public static CollectionDialogFragment newInstance(Collection collection) {
        Bundle args = new Bundle();
        args.putSerializable("collection", collection);
        CollectionDialogFragment fragment = new CollectionDialogFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewModel = new ViewModelProvider(getActivity()).get(MovieViewModel.class);
        mAdapter = new ItemsRecyclerViewAdapter<>(ViewSize.SMALL);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mBinding = LayoutCollectionDialogBinding.inflate(inflater, container, false);

        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Collection collection = (Collection) getArguments().getSerializable("collection");
        mBinding.moviesIntoCollection.setAdapter(mAdapter);
        mBinding.setCollection(collection);
        mViewModel.getCollection().observe(getViewLifecycleOwner(), new Observer<Collection>() {
            @Override
            public void onChanged(Collection collection) {
                mAdapter.addItems(collection.getParts());
            }
        });
        mViewModel.getCollection(collection.getId());
    }
}
