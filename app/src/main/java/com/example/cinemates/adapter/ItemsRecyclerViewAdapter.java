package com.example.cinemates.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cinemates.databinding.ListItemMediaPosterBinding;
import com.example.cinemates.databinding.ListItemPersonInformationBinding;
import com.example.cinemates.model.Cast;
import com.example.cinemates.model.Movie;
import com.example.cinemates.model.Person;
import com.example.cinemates.ui.ActorDetailsActivity;
import com.example.cinemates.ui.MovieDetailsActivity;
import com.example.cinemates.util.MyDiffUtilMovieCallbacks;
import com.example.cinemates.util.RecyclerViewEmptySupport;

import java.util.ArrayList;
import java.util.List;


/**
 * @author Antonio Di Nuzzo
 * Created 15/12/2021 at 16:36
 */
public class ItemsRecyclerViewAdapter<T> extends RecyclerViewEmptySupport.Adapter<RecyclerView.ViewHolder> {
    private final List<T> dataList = new ArrayList<>();
    private final int PERSON = 0, MOVIE = 1;

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        RecyclerView.ViewHolder viewHolder;

        switch (viewType) {
            case MOVIE:
                ListItemMediaPosterBinding mediaBinding = ListItemMediaPosterBinding.inflate(layoutInflater, parent, false);
                viewHolder = new MovieViewHolder(mediaBinding);
                break;
            case PERSON:
                ListItemPersonInformationBinding personBinding = ListItemPersonInformationBinding.inflate(layoutInflater, parent, false);
                viewHolder = new PersonViewHolder(personBinding);
                break;

            default:
                throw new IllegalStateException("Unexpected value: " + viewType);
        }

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        switch (holder.getItemViewType()) {
            case MOVIE:
                Movie movie = (Movie) dataList.get(position);
                ((MovieViewHolder) holder).mBinding.setMovie(movie);
                ((MovieViewHolder) holder).mBinding.executePendingBindings();
                ((MovieViewHolder) holder).mBinding.getRoot().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        Intent intent = new Intent(view.getContext(), MovieDetailsActivity.class);
                        intent.putExtra("movie", movie);
                        view.getContext().startActivity(intent);

                    }
                });
                break;
            case PERSON:
                Cast selected = (Cast) dataList.get(position);
                ((PersonViewHolder) holder).mBinding.setActor(selected);
                ((PersonViewHolder) holder).mBinding.executePendingBindings();
                ((PersonViewHolder) holder).mBinding.getRoot().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(view.getContext(), ActorDetailsActivity.class);
                        intent.putExtra("person", (Person) selected);
                        view.getContext().startActivity(intent);
                    }
                });
                break;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position, @NonNull List<Object> payloads) {
        if (payloads.isEmpty())
            super.onBindViewHolder(holder, position, payloads);
        else {
            switch (holder.getItemViewType()) {
                case MOVIE:
                    Movie movie = (Movie) dataList.get(position);
                    ((MovieViewHolder) holder).mBinding.setMovie(movie);
                    ((MovieViewHolder) holder).mBinding.executePendingBindings();
                    ((MovieViewHolder) holder).mBinding.getRoot().setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                            Intent intent = new Intent(view.getContext(), MovieDetailsActivity.class);
                            intent.putExtra("movie", movie);
                            view.getContext().startActivity(intent);

                        }
                    });
                    break;
                case PERSON:
                    Cast selected = (Cast) dataList.get(position);
                    ((PersonViewHolder) holder).mBinding.setActor(selected);
                    ((PersonViewHolder) holder).mBinding.executePendingBindings();
                    ((PersonViewHolder) holder).mBinding.getRoot().setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intent = new Intent(view.getContext(), ActorDetailsActivity.class);
                            intent.putExtra("person", (Person) selected);
                            view.getContext().startActivity(intent);
                        }
                    });
                    break;
            }
        }
    }

    //Returns the view type of the item at position for the purposes of view recycling.
    @Override
    public int getItemViewType(int position) {
        if (dataList.get(position) instanceof Movie) {
            return MOVIE;
        } else if (dataList.get(position) instanceof Cast) {
            return PERSON;
        }
        return -1;
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public void addItems(List<T> dataList) {
        DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(new MyDiffUtilMovieCallbacks(this.dataList, dataList));

        this.dataList.clear();
        this.dataList.addAll(dataList);
        diffResult.dispatchUpdatesTo(this);
        notifyDataSetChanged();
    }


}