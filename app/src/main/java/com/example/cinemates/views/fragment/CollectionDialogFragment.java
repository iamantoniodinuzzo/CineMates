package com.example.cinemates.views.fragment;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.cinemates.R;
import com.example.cinemates.adapter.MovieRecyclerViewAdapter;
import com.example.cinemates.databinding.LayoutCollectionDialogBinding;
import com.example.cinemates.model.Collection;
import com.example.cinemates.util.Constants;
import com.example.cinemates.viewmodel.MovieViewModel;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import java.util.HashMap;

import dagger.hilt.android.AndroidEntryPoint;

/**
 * @author Antonio Di Nuzzo
 * Created 27/05/2022 at 17:56
 */
@AndroidEntryPoint
public class CollectionDialogFragment extends DialogFragment {
    //    private static Collection mCollection;
    private LayoutCollectionDialogBinding mBinding;
    private MovieRecyclerViewAdapter mAdapter;
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
        mAdapter = new MovieRecyclerViewAdapter();

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
        HashMap<String, String> map = new HashMap<>();
        map.put("api_key", Constants.API_KEY);
        map.put("page", "1");
        mViewModel.getCollection().observe(getViewLifecycleOwner(), new Observer<Collection>() {
            @Override
            public void onChanged(Collection collection) {
                mAdapter.addItems(collection.getParts());
            }
        });
        mViewModel.getCollection(collection.getId(), map);
    }
}
