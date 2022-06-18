package com.example.cinemates.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.cinemates.databinding.ListItemMediaPosterBinding;
import com.example.cinemates.model.Movie;
import com.example.cinemates.util.RecyclerViewEmptySupport;
import com.example.cinemates.views.MovieDetailsActivity;

import java.util.ArrayList;
import java.util.List;


/**
 * @author Antonio Di Nuzzo
 * Created 15/12/2021 at 16:36
 */
public class SectionItemsRecyclerViewAdapter<T> extends RecyclerViewEmptySupport.Adapter<SectionItemsRecyclerViewAdapter.SectionItemViewHolder> {
    private List<T> dataList = new ArrayList<>();


    @NonNull
    @Override
    public SectionItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ListItemMediaPosterBinding mediaBinding = ListItemMediaPosterBinding.inflate(layoutInflater, parent, false);
        return new SectionItemViewHolder(mediaBinding);
    }

    @Override
    public void onBindViewHolder(SectionItemViewHolder holder, int position) {
        T selected = dataList.get(position);
        holder.mBinding.setMovie((Movie) selected);
        holder.mBinding.executePendingBindings();

        holder.mBinding.getRoot().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), MovieDetailsActivity.class);
                intent.putExtra("movie", (Movie) selected);
                view.getContext().startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public void addItems(List<T> dataList) {
        this.dataList.addAll(dataList);
        notifyDataSetChanged();
    }

    static class SectionItemViewHolder extends RecyclerViewEmptySupport.ViewHolder {
        ListItemMediaPosterBinding mBinding;

        SectionItemViewHolder(@NonNull ListItemMediaPosterBinding listItemMediaPosterBinding) {
            super(listItemMediaPosterBinding.getRoot());
            this.mBinding = listItemMediaPosterBinding;

        }


    }
}