package com.example.cinemates.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.example.cinemates.databinding.ListItemPersonInformationBinding;
import com.example.cinemates.model.Cast;
import com.example.cinemates.model.Person;
import com.example.cinemates.util.RecyclerViewEmptySupport;
import com.example.cinemates.views.ActorDetailsActivity;

import java.util.ArrayList;
import java.util.List;


/**
 * @author Antonio Di Nuzzo
 * Created 15/12/2021 at 16:36
 */
public class PersonRecyclerViewAdapter<T> extends RecyclerViewEmptySupport.Adapter<PersonRecyclerViewAdapter.PersonViewHolder> {
    private final List<T> dataList = new ArrayList<>();

    @NonNull
    @Override
    public PersonViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ListItemPersonInformationBinding mediaBinding = ListItemPersonInformationBinding.inflate(layoutInflater, parent, false);
        return new PersonViewHolder(mediaBinding);
    }

    @Override
    public void onBindViewHolder(PersonViewHolder holder, int position) {
        T selected = dataList.get(position);
        holder.mBinding.setActor((Cast) selected);
        holder.mBinding.executePendingBindings();
        holder.mBinding.getRoot().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), ActorDetailsActivity.class);
                intent.putExtra("person", (Person)selected);
                view.getContext().startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public void addItems(List<T> dataList) {
        this.dataList.clear();
        this.dataList.addAll(dataList);
        notifyDataSetChanged();
    }

    static class PersonViewHolder extends RecyclerViewEmptySupport.ViewHolder {
        ListItemPersonInformationBinding mBinding;

        PersonViewHolder(@NonNull ListItemPersonInformationBinding listItemPersonInformationBinding) {
            super(listItemPersonInformationBinding.getRoot());
            this.mBinding = listItemPersonInformationBinding;


        }


    }
}