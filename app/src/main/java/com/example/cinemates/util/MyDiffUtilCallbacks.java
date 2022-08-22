package com.example.cinemates.util;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DiffUtil;

import com.example.cinemates.model.data.Movie;
import com.example.cinemates.model.data.Person;

import java.util.List;

/**
 * @author Antonio Di Nuzzo
 * Created 21/07/2022 at 19:16
 */
public class MyDiffUtilCallbacks<T> extends DiffUtil.Callback {
    private List<T> oldList;
    private List<T> newList;

    public MyDiffUtilCallbacks(List<T> oldList, List<T> newList) {
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
        Bundle bundle = new Bundle();

        if (oldList.get(oldItemPosition) instanceof Movie && newList.get(newItemPosition) instanceof Movie) {
            Movie newMovie = (Movie) newList.get(newItemPosition);
            Movie oldMovie = (Movie) oldList.get(oldItemPosition);

            if (!newMovie.equals(oldMovie))
                bundle.putSerializable("movie", newMovie);

        }else if(oldList.get(oldItemPosition) instanceof Person && newList.get(newItemPosition) instanceof Person){
            Person newPerson = (Person) newList.get(newItemPosition);
            Person oldPerson = (Person) oldList.get(oldItemPosition);

            if (!newPerson.equals(oldPerson))
                bundle.putSerializable("person", newPerson);
        }


        return bundle.size() == 0 ? null : bundle;
    }
}
