package com.example.cinemates.views.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.cinemates.adapter.MovieRecyclerViewAdapter;
import com.example.cinemates.databinding.FragmentMediaInfoBinding;
import com.example.cinemates.model.Movie;
import com.example.cinemates.model.Video;
import com.example.cinemates.util.Constants;
import com.example.cinemates.viewmodel.MovieViewModel;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

import java.util.ArrayList;
import java.util.HashMap;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class MediaInfoFragment extends Fragment {

    private FragmentMediaInfoBinding mBinding;
    private MovieViewModel mViewModel;
    private MovieRecyclerViewAdapter mAdapter;
    private Movie mMovie;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAdapter = new MovieRecyclerViewAdapter();
        mViewModel = new ViewModelProvider(getActivity()).get(MovieViewModel.class);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mBinding = FragmentMediaInfoBinding.inflate(inflater, container, false);
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mMovie = (Movie) getArguments().getSerializable("movie");
        mBinding.recommendedRecyclerView.setAdapter(mAdapter);

        HashMap<String, String> map = new HashMap<>();
        map.put("api_key", Constants.API_KEY);
        map.put("page", "1");

        observe();
        mViewModel.getMovieDetails(mMovie.getId(), map);
        mViewModel.getRecommendations(mMovie.getId(), map);
        mViewModel.getVideos(mMovie.getId(), map);

        mBinding.collectionName.collectionName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fm = getActivity().getSupportFragmentManager();
                CollectionDialogFragment collectionDialogFragment = CollectionDialogFragment.newInstance(mMovie.getBelongs_to_collection());
                collectionDialogFragment.show(fm, "fragment_show_collection");
            }
        });
    }

    private void observe() {
        mViewModel.getMovie().observe(getViewLifecycleOwner(), new Observer<Movie>() {
            @Override
            public void onChanged(Movie movie) {
                mBinding.setMovie(movie);
                mMovie = movie;
            }
        });
        mViewModel.getMovieSimilar().observe(getViewLifecycleOwner(), new Observer<ArrayList<Movie>>() {
            @Override
            public void onChanged(ArrayList<Movie> movies) {
                mAdapter.addItems(movies);
            }
        });
        mViewModel.getMovieVideos().observe(getViewLifecycleOwner(), new Observer<ArrayList<Video>>() {
            @Override
            public void onChanged(ArrayList<Video> videos) {
                //TODO create recycler view for videos and implement youtube player
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mBinding = null;

    }
}