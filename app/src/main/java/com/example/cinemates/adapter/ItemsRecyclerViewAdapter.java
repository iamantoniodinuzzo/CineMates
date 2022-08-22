package com.example.cinemates.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cinemates.databinding.ListItemMovieLongBinding;
import com.example.cinemates.databinding.ListItemMovieSmallBinding;
import com.example.cinemates.databinding.ListItemPersonLongBinding;
import com.example.cinemates.databinding.ListItemPersonSmallBinding;
import com.example.cinemates.model.data.Cast;
import com.example.cinemates.model.data.Movie;
import com.example.cinemates.model.data.Person;
import com.example.cinemates.view.ui.ActorDetailsActivity;
import com.example.cinemates.view.ui.MovieDetailsActivity;
import com.example.cinemates.util.MyDiffUtilCallbacks;
import com.example.cinemates.util.RecyclerViewEmptySupport;
import com.example.cinemates.util.ViewSize;

import java.util.ArrayList;
import java.util.List;


/**
 * @author Antonio Di Nuzzo
 * Created 15/12/2021 at 16:36
 */
public class ItemsRecyclerViewAdapter<T> extends RecyclerViewEmptySupport.Adapter<RecyclerView.ViewHolder> {
    private final List<T> dataList = new ArrayList<>();
    private final int PERSON = 0, MOVIE = 1;
    private final ViewSize mViewSize;

    public ItemsRecyclerViewAdapter(ViewSize viewSize) {
        mViewSize = viewSize;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        RecyclerView.ViewHolder viewHolder;

        switch (viewType) {
            case MOVIE:
                if (mViewSize == ViewSize.SMALL) {
                    ListItemMovieSmallBinding smallMovieBinding = ListItemMovieSmallBinding.inflate(layoutInflater, parent, false);
                    viewHolder = new MovieViewHolder(smallMovieBinding);
                } else {
                    ListItemMovieLongBinding longMovieBinding = ListItemMovieLongBinding.inflate(layoutInflater, parent, false);
                    viewHolder = new MovieViewHolder(longMovieBinding);
                }

                break;
            case PERSON:
                if (mViewSize == ViewSize.SMALL) {
                    ListItemPersonSmallBinding smallPersonBinding = ListItemPersonSmallBinding.inflate(layoutInflater, parent, false);
                    viewHolder = new PersonViewHolder(smallPersonBinding);
                } else {
                    ListItemPersonLongBinding defaultBinding = ListItemPersonLongBinding.inflate(layoutInflater, parent, false);
                    viewHolder = new PersonViewHolder(defaultBinding);
                }

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
                switch (mViewSize) {

                    case LONG:
                        ((MovieViewHolder) holder).mMovieLongBinding.setMovie(movie);
                        ((MovieViewHolder) holder).mMovieLongBinding.executePendingBindings();
                        ((MovieViewHolder) holder).mMovieLongBinding.getRoot().setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {

                                Intent intent = new Intent(view.getContext(), MovieDetailsActivity.class);
                                intent.putExtra("movie", movie);
                                view.getContext().startActivity(intent);

                            }
                        });
                        break;
                    case SMALL:
                        ((MovieViewHolder) holder).mMovieSmallBinding.setMovie(movie);
                        ((MovieViewHolder) holder).mMovieSmallBinding.executePendingBindings();
                        ((MovieViewHolder) holder).mMovieSmallBinding.getRoot().setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {

                                Intent intent = new Intent(view.getContext(), MovieDetailsActivity.class);
                                intent.putExtra("movie", movie);
                                view.getContext().startActivity(intent);

                            }
                        });
                        break;
                }

                break;
            case PERSON:
                switch (mViewSize) {
                    case LONG:
                        ((PersonViewHolder) holder).mLongBinding.setActor((Cast) dataList.get(position));
                        ((PersonViewHolder) holder).mLongBinding.executePendingBindings();
                        ((PersonViewHolder) holder).mLongBinding.getRoot().setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Intent intent = new Intent(view.getContext(), ActorDetailsActivity.class);
                                intent.putExtra("person", (Person) dataList.get(position));
                                view.getContext().startActivity(intent);
                            }
                        });
                        break;
                    case SMALL:
                        ((PersonViewHolder) holder).mSmallBinding.setPerson((Person) dataList.get(position));
                        ((PersonViewHolder) holder).mSmallBinding.executePendingBindings();
                        ((PersonViewHolder) holder).mSmallBinding.getRoot().setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Intent intent = new Intent(view.getContext(), ActorDetailsActivity.class);
                                intent.putExtra("person", (Person) dataList.get(position));
                                view.getContext().startActivity(intent);
                            }
                        });
                        break;
                }

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
                    switch (mViewSize) {

                        case LONG:
                            ((MovieViewHolder) holder).mMovieLongBinding.setMovie(movie);
                            ((MovieViewHolder) holder).mMovieLongBinding.executePendingBindings();
                            ((MovieViewHolder) holder).mMovieLongBinding.getRoot().setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {

                                    Intent intent = new Intent(view.getContext(), MovieDetailsActivity.class);
                                    intent.putExtra("movie", movie);
                                    view.getContext().startActivity(intent);

                                }
                            });
                            break;
                        case SMALL:
                            ((MovieViewHolder) holder).mMovieSmallBinding.setMovie(movie);
                            ((MovieViewHolder) holder).mMovieSmallBinding.executePendingBindings();
                            ((MovieViewHolder) holder).mMovieSmallBinding.getRoot().setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {

                                    Intent intent = new Intent(view.getContext(), MovieDetailsActivity.class);
                                    intent.putExtra("movie", movie);
                                    view.getContext().startActivity(intent);

                                }
                            });
                            break;
                    }
                    break;
                case PERSON:
                    switch (mViewSize) {
                        case LONG:
                            ((PersonViewHolder) holder).mLongBinding.setActor((Cast) dataList.get(position));
                            ((PersonViewHolder) holder).mLongBinding.executePendingBindings();
                            ((PersonViewHolder) holder).mLongBinding.getRoot().setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    Intent intent = new Intent(view.getContext(), ActorDetailsActivity.class);
                                    intent.putExtra("person", (Person) dataList.get(position));
                                    view.getContext().startActivity(intent);
                                }
                            });
                            break;
                        case SMALL:
                            ((PersonViewHolder) holder).mSmallBinding.setPerson((Person) dataList.get(position));
                            ((PersonViewHolder) holder).mSmallBinding.executePendingBindings();
                            ((PersonViewHolder) holder).mSmallBinding.getRoot().setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    Intent intent = new Intent(view.getContext(), ActorDetailsActivity.class);
                                    intent.putExtra("person", (Person) dataList.get(position));
                                    view.getContext().startActivity(intent);
                                }
                            });
                            break;
                    }
                    break;
            }
        }
    }

    //Returns the view type of the item at position for the purposes of view recycling.
    @Override
    public int getItemViewType(int position) {
        if (dataList.get(position) instanceof Movie) {
            return MOVIE;
        } else if ((dataList.get(position) instanceof Person)) {
            return PERSON;
        }
        return -1;
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public void addItems(List<T> dataList) {
        DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(new MyDiffUtilCallbacks(this.dataList, dataList));
        diffResult.dispatchUpdatesTo(this);

        this.dataList.clear();
        this.dataList.addAll(dataList);
        notifyDataSetChanged();
    }


}