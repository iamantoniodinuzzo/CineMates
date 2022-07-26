package com.example.cinemates.ui.fragment;

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

import com.example.cinemates.adapter.MovieRecyclerViewAdapter;
import com.example.cinemates.databinding.FragmentListingBinding;
import com.example.cinemates.interfaces.CustomizableFragment;
import com.example.cinemates.model.Movie;
import com.example.cinemates.viewmodel.DbViewModel;

import java.util.List;

/**
 * @author Antonio Di Nuzzo
 * Created 25/07/2022 at 08:24
 */
public class SeenFragment extends Fragment implements CustomizableFragment {
    private FragmentListingBinding mBinding;
    private DbViewModel mDbViewModel;
    private MovieRecyclerViewAdapter mAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mDbViewModel = new ViewModelProvider(getActivity()).get(DbViewModel.class);
        mAdapter = new MovieRecyclerViewAdapter();

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = FragmentListingBinding.inflate(inflater, container, false);

        mBinding.recyclerView.setEmptyView(mBinding.emptyView.getRoot());
        mBinding.recyclerView.setAdapter(mAdapter);


        mDbViewModel.getAllWithStatus(Movie.PersonalStatus.SEEN);
        mDbViewModel.getSeen().observe(getViewLifecycleOwner(), new Observer<List<Movie>>() {
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

    @Override
    public void changeLayout(RecyclerView.LayoutManager layoutManager) {

    }

    @Override
    public void bindData(String query) {
        //Nothing
    }
}
