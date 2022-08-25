package com.example.cinemates.view.ui.saved;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cinemates.adapter.ItemsRecyclerViewAdapter;
import com.example.cinemates.databinding.FragmentListingBinding;
import com.example.cinemates.interfaces.CustomizableFragment;
import com.example.cinemates.model.data.Movie;
import com.example.cinemates.model.data.PersonalStatus;
import com.example.cinemates.util.ViewSize;
import com.example.cinemates.view.viewmodel.DbViewModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author Antonio Di Nuzzo
 * Created 25/07/2022 at 08:24
 */
public class ToSeeFragment extends Fragment  {
    private FragmentListingBinding mBinding;
    private DbViewModel mDbViewModel;
//    private MovieRecyclerViewAdapter mAdapter;
    private ItemsRecyclerViewAdapter<Movie> mAdapter;
    private ArrayList<Movie> movie_to_see;
    private Random rand;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mDbViewModel = new ViewModelProvider(getActivity()).get(DbViewModel.class);
        mAdapter = new ItemsRecyclerViewAdapter<>(ViewSize.SMALL);
        movie_to_see = new ArrayList<>();
        rand = new Random();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = FragmentListingBinding.inflate(inflater, container, false);

        mBinding.recyclerView.setEmptyView(mBinding.emptyView.getRoot());
        mBinding.recyclerView.setAdapter(mAdapter);

        mDbViewModel.getAllWithStatus(PersonalStatus.TO_SEE);
        mDbViewModel.getTo_see().observe(getViewLifecycleOwner(), new Observer<List<Movie>>() {
            @Override
            public void onChanged(List<Movie> movies) {
                mAdapter.addItems(movies);
            }
        });


        return mBinding.getRoot();
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mBinding = null;
    }


}
