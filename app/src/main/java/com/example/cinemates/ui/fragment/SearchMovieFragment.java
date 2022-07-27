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
import com.example.cinemates.databinding.FragmentSearchMovieBinding;
import com.example.cinemates.interfaces.CustomizableFragment;
import com.example.cinemates.model.Movie;
import com.example.cinemates.viewmodel.MovieViewModel;

import java.util.List;

public class SearchMovieFragment extends Fragment implements CustomizableFragment {

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

        mViewModel.getQueriedMovies().observe(getViewLifecycleOwner(), new Observer<List<Movie>>() {
            @Override
            public void onChanged(List<Movie> movies) {
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
    }

    @Override
    public void bindData(String query) {

        try {

            mViewModel.getQueriedMovies(query);
        } catch (NullPointerException exception) {

        }


    }
}