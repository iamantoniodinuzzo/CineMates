package com.example.cinemates.adapter;

import androidx.annotation.NonNull;

import com.example.cinemates.databinding.ListItemMediaPosterBinding;
import com.example.cinemates.util.RecyclerViewEmptySupport;

/**
 * @author Antonio Di Nuzzo
 * Created 28/07/2022 at 08:51
 */
class MovieViewHolder extends RecyclerViewEmptySupport.ViewHolder {
    private static final String TAG = MovieViewHolder.class.getSimpleName();
    ListItemMediaPosterBinding mBinding;

    MovieViewHolder(@NonNull ListItemMediaPosterBinding listItemMediaPosterBinding) {
        super(listItemMediaPosterBinding.getRoot());
        this.mBinding = listItemMediaPosterBinding;

    }


}
