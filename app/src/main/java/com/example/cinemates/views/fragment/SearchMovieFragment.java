package com.example.cinemates.views.fragment;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cinemates.adapter.MovieRecyclerViewAdapter;
import com.example.cinemates.databinding.FragmentSearchMovieBinding;
import com.example.cinemates.model.Movie;
import com.example.cinemates.util.ChangeRvLayout;
import com.example.cinemates.util.SearchData;
import com.example.cinemates.viewmodel.MovieViewModel;

import java.util.ArrayList;

public class SearchMovieFragment extends Fragment implements ChangeRvLayout, SearchData {

    private FragmentSearchMovieBinding mBinding;
    private MovieRecyclerViewAdapter mRecyclerViewAdapter;
    private MovieViewModel mViewModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mRecyclerViewAdapter = new MovieRecyclerViewAdapter();

        mViewModel = new ViewModelProvider(getActivity()).get(MovieViewModel.class);

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mBinding = FragmentSearchMovieBinding.inflate(inflater, container, false);

        mBinding.recyclerView.setAdapter(mRecyclerViewAdapter);
        mBinding.recyclerView.setEmptyView(mBinding.emptyView.getRoot());


        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mViewModel.getQueriesMovies().observe(getViewLifecycleOwner(), new Observer<ArrayList<Movie>>() {
            @Override
            public void onChanged(ArrayList<Movie> movies) {
                mRecyclerViewAdapter.addItems(movies);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mBinding = null;
    }

    @Override
    public void changeLayout(RecyclerView.LayoutManager layoutManager) {
       /* mBinding.recyclerView.setLayoutManager(layoutManager);
        mRecyclerViewAdapter.notifyItemRangeChanged(0, mRecyclerViewAdapter.getItemCount());*/
        Toast.makeText(getContext(), "Changed", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void bindData(String query) {
        if (query!= null || !TextUtils.isEmpty(query))
            mViewModel.getQueriedMovies(query);
    }
}