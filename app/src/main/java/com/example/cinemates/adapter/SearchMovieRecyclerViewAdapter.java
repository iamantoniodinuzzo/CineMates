package com.example.cinemates.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.example.cinemates.databinding.ListItemMediaBinding;
import com.example.cinemates.model.Movie;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Antonio Di Nuzzo
 * Created 15/12/2021 at 16:36
 */
public class SearchMovieRecyclerViewAdapter extends EmptyRecyclerView.Adapter<SearchMovieRecyclerViewAdapter.MovieViewHolder> {
    private List<Movie> dataList = new ArrayList<>();

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ListItemMediaBinding mediaBinding = ListItemMediaBinding.inflate(layoutInflater, parent, false);
        return new MovieViewHolder(mediaBinding);
    }

    @Override
    public void onBindViewHolder(MovieViewHolder holder, int position) {
        Movie movie = dataList.get(position);
        holder.mBinding.setMovie(movie);
        holder.mBinding.executePendingBindings();

    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public void addItems(List<Movie> dataList) {
        this.dataList.addAll(dataList);
        notifyDataSetChanged();
    }

    static class MovieViewHolder extends EmptyRecyclerView.ViewHolder {
        private static final String TAG = "MovieViewHolder";
        ListItemMediaBinding mBinding;

        MovieViewHolder(@NonNull ListItemMediaBinding listItemMediaBinding) {
            super(listItemMediaBinding.getRoot());
            this.mBinding = listItemMediaBinding;

            //Click on card
            this.mBinding.getRoot().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.d(TAG, "onClick: " + getAdapterPosition());
                    //TODO Open movie details activity
                }
            });

            //Click on add watched
            this.mBinding.iconAddWatched.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // TODO add to watched movie list
                }
            });

            //Click on add watch list
            this.mBinding.iconAddWatchlist.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //TODO add to movie list
                }
            });

            //Click on icon more
            this.mBinding.iconMore.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // TODO open bottom sheet dialog
                }
            });

        }


    }
}