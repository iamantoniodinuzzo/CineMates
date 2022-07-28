package com.example.cinemates.adapter;

import androidx.annotation.NonNull;

import com.example.cinemates.databinding.ListItemMovieLongBinding;
import com.example.cinemates.databinding.ListItemMovieSmallBinding;
import com.example.cinemates.util.RecyclerViewEmptySupport;

/**
 * @author Antonio Di Nuzzo
 * Created 28/07/2022 at 08:51
 */
class MovieViewHolder extends RecyclerViewEmptySupport.ViewHolder {
    ListItemMovieLongBinding mMovieLongBinding;
    ListItemMovieSmallBinding mMovieSmallBinding;

    MovieViewHolder(@NonNull ListItemMovieLongBinding listItemMovieLongBinding) {
        super(listItemMovieLongBinding.getRoot());
        this.mMovieLongBinding = listItemMovieLongBinding;

    }

    MovieViewHolder(@NonNull ListItemMovieSmallBinding listItemMovieSmallBinding) {
        super(listItemMovieSmallBinding.getRoot());
        this.mMovieSmallBinding = listItemMovieSmallBinding;

    }


}
