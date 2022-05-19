package com.example.cinemates.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.cinemates.databinding.ListItemMediaPosterBinding;
import com.example.cinemates.model.Movie;
import com.example.cinemates.util.RecyclerViewEmptySupport;

import java.util.ArrayList;
import java.util.List;


/**
 * @author Antonio Di Nuzzo
 * Created 15/12/2021 at 16:36
 */
public class SectionItemsRecyclerViewAdapter extends RecyclerViewEmptySupport.Adapter<SectionItemsRecyclerViewAdapter.SectionItemViewHolder> {
    private static List<Movie> dataList = new ArrayList<>();


    @NonNull
    @Override
    public SectionItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ListItemMediaPosterBinding mediaBinding = ListItemMediaPosterBinding.inflate(layoutInflater, parent, false);
        return new SectionItemViewHolder(mediaBinding);
    }

    @Override
    public void onBindViewHolder(SectionItemViewHolder holder, int position) {
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

    static class SectionItemViewHolder extends RecyclerViewEmptySupport.ViewHolder {
        ListItemMediaPosterBinding mBinding;

        SectionItemViewHolder(@NonNull ListItemMediaPosterBinding listItemMediaPosterBinding) {
            super(listItemMediaPosterBinding.getRoot());
            this.mBinding = listItemMediaPosterBinding;


            this.mBinding.getRoot().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //TODO open details fragment
                    Movie movieSelected = dataList.get(getAdapterPosition());

                }
            });

        }


    }
}