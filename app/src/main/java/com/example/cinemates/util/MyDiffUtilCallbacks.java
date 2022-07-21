package com.example.cinemates.util;

import android.os.Bundle;
import android.os.Parcelable;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DiffUtil;

import com.example.cinemates.model.Movie;
import com.example.cinemates.model.Section;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Antonio Di Nuzzo
 * Created 21/07/2022 at 19:16
 */
public class MyDiffUtilCallbacks<T> extends DiffUtil.Callback {
    private List<Section<T>> oldList;
    private List<Section<T>> newList;

    public MyDiffUtilCallbacks(List<Section<T>> oldList, List<Section<T>> newList) {
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
        Section<T> newSection = newList.get(newItemPosition);
        Section<T> oldSection = oldList.get(oldItemPosition);
        Bundle bundle = new Bundle();

        if (!newSection.getSectionName().equals(oldSection.getSectionName())) {
            bundle.putString("section_name", newSection.getSectionName());
        }
        if (!newSection.getMutableLiveData().getValue().equals(oldSection.getMutableLiveData().getValue())) {
            bundle.putParcelableArrayList("movies", (ArrayList<? extends Parcelable>) newSection.getMutableLiveData().getValue());

        }


        return bundle.size() == 0 ? null : bundle;
    }
}
