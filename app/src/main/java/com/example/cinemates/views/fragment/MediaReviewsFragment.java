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

import com.example.cinemates.adapter.ReviewRecyclerViewAdapter;
import com.example.cinemates.databinding.FragmentMediaReviewsBinding;
import com.example.cinemates.model.Movie;
import com.example.cinemates.model.Review;
import com.example.cinemates.viewmodel.MovieViewModel;

import java.util.ArrayList;

/**
 * @author Antonio Di Nuzzo
 * Created 29/05/2022 at 10:27
 */
public class MediaReviewsFragment extends Fragment {
    private FragmentMediaReviewsBinding mBinding;
    private MovieViewModel mViewModel;
    private ReviewRecyclerViewAdapter mAdapter;
    private Movie mMovie;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAdapter = new ReviewRecyclerViewAdapter();
        mViewModel = new ViewModelProvider(getActivity()).get(MovieViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mBinding = FragmentMediaReviewsBinding.inflate(inflater, container, false);

        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mMovie = (Movie) getArguments().getSerializable("movie");

        mBinding.reviewsRecyclerView.setAdapter(mAdapter);


        mViewModel.getMovieReviews().observe(getViewLifecycleOwner(), new Observer<ArrayList<Review>>() {
            @Override
            public void onChanged(ArrayList<Review> casts) {
                mAdapter.addItems(casts);
            }
        });
        mViewModel.getReviews(mMovie.getId());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mBinding = null;
    }
}
