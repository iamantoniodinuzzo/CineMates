package com.example.cinemates.util;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DiffUtil;

import com.example.cinemates.model.Movie;

import java.util.List;

/**
 * @author Antonio Di Nuzzo
 * Created 21/07/2022 at 19:16
 */
public class MyDiffUtilMovieCallbacks<T> extends DiffUtil.Callback {
    private List<Movie> oldList;
    private List<Movie> newList;

    public MyDiffUtilMovieCallbacks(List<Movie> oldList, List<Movie> newList) {
        this.oldList = oldList;
        this.newList = newList;
    }

    @Override
    public int getOldListSize() {
        return oldList != null ? oldList.size() : 0;
    }

    @Override
    public int getNewListSize() {
        return newList != null ? newList.size() : 0;
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        return true;
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        return newList.get(newItemPosition).equals(oldList.get(oldItemPosition));
    }

    @Nullable
    @Override
    public Object getChangePayload(int oldItemPosition, int newItemPosition) {
        Movie newMovie = newList.get(newItemPosition);
        Movie oldMovie = oldList.get(oldItemPosition);
        Bundle bundle = new Bundle();

        if(!newMovie.equals(oldMovie))
            bundle.putSerializable("movie", newMovie);


        return bundle.size() == 0 ? null : bundle;
    }
}
