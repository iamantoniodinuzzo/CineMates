package com.example.cinemates.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.example.cinemates.databinding.ListItemPersonInformationBinding;

import java.util.ArrayList;
import java.util.List;

import info.movito.themoviedbapi.model.people.PersonCast;

/**
 * @author Antonio Di Nuzzo
 * Created 15/12/2021 at 16:36
 */
public class SearchActorRecyclerViewAdapter extends EmptyRecyclerView.Adapter<SearchActorRecyclerViewAdapter.ActorViewHolder> {
    private List<PersonCast> dataList = new ArrayList<>();

    @NonNull
    @Override
    public ActorViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ListItemPersonInformationBinding mediaBinding = ListItemPersonInformationBinding.inflate(layoutInflater, parent, false);
        return new ActorViewHolder(mediaBinding);
    }

    @Override
    public void onBindViewHolder(ActorViewHolder holder, int position) {
        PersonCast personCast = dataList.get(position);
        holder.mBinding.setActor(personCast);
        holder.mBinding.executePendingBindings();

    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public void addItems(List<PersonCast> dataList) {
        this.dataList.addAll(dataList);
        notifyDataSetChanged();
    }

    static class ActorViewHolder extends EmptyRecyclerView.ViewHolder {
        ListItemPersonInformationBinding mBinding;

        ActorViewHolder(@NonNull ListItemPersonInformationBinding listItemPersonInformationBinding) {
            super(listItemPersonInformationBinding.getRoot());
            this.mBinding = listItemPersonInformationBinding;


        }


    }
}