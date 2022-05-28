package com.example.cinemates.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.cinemates.databinding.ListItemPersonInformationBinding;
import com.example.cinemates.model.Cast;
import com.example.cinemates.util.RecyclerViewEmptySupport;

import java.util.ArrayList;
import java.util.List;


/**
 * @author Antonio Di Nuzzo
 * Created 15/12/2021 at 16:36
 */
public class ActorRecyclerViewAdapter extends RecyclerViewEmptySupport.Adapter<ActorRecyclerViewAdapter.ActorViewHolder> {
    private List<Cast> dataList = new ArrayList<>();

    @NonNull
    @Override
    public ActorViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ListItemPersonInformationBinding mediaBinding = ListItemPersonInformationBinding.inflate(layoutInflater, parent, false);
        return new ActorViewHolder(mediaBinding);
    }

    @Override
    public void onBindViewHolder(ActorViewHolder holder, int position) {
        Cast personCast = dataList.get(position);
        holder.mBinding.setActor(personCast);
        holder.mBinding.executePendingBindings();
        holder.mBinding.getRoot().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(), "In fase di sviluppo", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public void addItems(List<Cast> dataList) {
        this.dataList.addAll(dataList);
        notifyDataSetChanged();
    }

    static class ActorViewHolder extends RecyclerViewEmptySupport.ViewHolder {
        ListItemPersonInformationBinding mBinding;

        ActorViewHolder(@NonNull ListItemPersonInformationBinding listItemPersonInformationBinding) {
            super(listItemPersonInformationBinding.getRoot());
            this.mBinding = listItemPersonInformationBinding;


        }


    }
}