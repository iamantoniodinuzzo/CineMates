package com.example.cinemates.adapter;

import android.content.Context;
import android.graphics.Color;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cinemates.R;
import com.example.cinemates.databinding.ListItemSectionBinding;
import com.example.cinemates.model.Cast;
import com.example.cinemates.model.Movie;
import com.example.cinemates.model.Person;
import com.example.cinemates.model.Section;
import com.example.cinemates.util.ItemMoveCallback;
import com.example.cinemates.util.MyDiffUtilSectionCallbacks;
import com.example.cinemates.util.RecyclerViewEmptySupport;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Antonio Di Nuzzo
 * Created 15/12/2021 at 16:36
 */
public class SectionRecyclerViewAdapter extends RecyclerView.Adapter<SectionRecyclerViewAdapter.SectionViewHolder> implements ItemMoveCallback.ItemTouchHelperContract {
    private final List<Section<?>> dataList = new ArrayList<>();
    private final LifecycleOwner mLifecycleOwner;
    private final Vibrator vibe;
    private final VibrationEffect vibrationEffect1;
    private final int PERSON = 0, MOVIE = 1;

    public SectionRecyclerViewAdapter(LifecycleOwner lifecycleOwner, Context context) {
        mLifecycleOwner = lifecycleOwner;
        vibe = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
        vibrationEffect1 = VibrationEffect.createOneShot(50, VibrationEffect.DEFAULT_AMPLITUDE);

    }

    @NonNull
    @Override
    public SectionViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ListItemSectionBinding sectionRowBinding = ListItemSectionBinding.inflate(layoutInflater, parent, false);
        return new SectionViewHolder(sectionRowBinding);
    }

    @Override
    public void onBindViewHolder(SectionViewHolder holder, int position) {


        switch (holder.getItemViewType()) {
            case MOVIE:
                Section<Movie> movie_section = (Section<Movie>) dataList.get(position);
                holder.mBinding.setSection(movie_section);
                holder.mBinding.executePendingBindings();

                ItemsRecyclerViewAdapter<Movie> section_items_movie = new ItemsRecyclerViewAdapter<>(movie_section.getViewSize());
                holder.mBinding.recyclerView.setAdapter(section_items_movie);
                holder.mBinding.recyclerView.setEmptyView(holder.mBinding.emptyView.getRoot());
                movie_section.getMutableLiveData().observe(mLifecycleOwner, new Observer<List<Movie>>() {
                    @Override
                    public void onChanged(List<Movie> items) {
                        section_items_movie.addItems((items));

                    }
                });
                break;
            case PERSON:
                Section<Person> person_section = (Section<Person>) dataList.get(position);
                holder.mBinding.setSection(person_section);
                holder.mBinding.executePendingBindings();

                ItemsRecyclerViewAdapter<Person> sectionItemsPerson = new ItemsRecyclerViewAdapter<>(person_section.getViewSize());
                holder.mBinding.recyclerView.setAdapter(sectionItemsPerson);
                holder.mBinding.recyclerView.setEmptyView(holder.mBinding.emptyView.getRoot());
                person_section.getMutableLiveData().observe(mLifecycleOwner, new Observer<List<Person>>() {
                    @Override
                    public void onChanged(List<Person> items) {
                        sectionItemsPerson.addItems((items));

                    }
                });
                break;
        }


    }

    @Override
    public void onBindViewHolder(@NonNull SectionViewHolder holder, int position, @NonNull List<Object> payloads) {
        if (payloads.isEmpty())
            super.onBindViewHolder(holder, position, payloads);
        else {
            switch (holder.getItemViewType()) {
                case MOVIE:
                    Section<Movie> movie_section = (Section<Movie>) dataList.get(position);
                    holder.mBinding.setSection(movie_section);
                    holder.mBinding.executePendingBindings();

                    ItemsRecyclerViewAdapter<Movie> section_items_movie = new ItemsRecyclerViewAdapter<>(movie_section.getViewSize());
                    holder.mBinding.recyclerView.setAdapter(section_items_movie);
                    holder.mBinding.recyclerView.setEmptyView(holder.mBinding.emptyView.getRoot());
                    movie_section.getMutableLiveData().observe(mLifecycleOwner, new Observer<List<Movie>>() {
                        @Override
                        public void onChanged(List<Movie> items) {
                            section_items_movie.addItems((items));

                        }
                    });
                    break;
                case PERSON:
                    Section<Person> person_section = (Section<Person>) dataList.get(position);

                    holder.mBinding.setSection(person_section);
                    holder.mBinding.executePendingBindings();

                    ItemsRecyclerViewAdapter<Person> sectionItemsPerson = new ItemsRecyclerViewAdapter<>(person_section.getViewSize());
                    holder.mBinding.recyclerView.setAdapter(sectionItemsPerson);
                    holder.mBinding.recyclerView.setEmptyView(holder.mBinding.emptyView.getRoot());
                    person_section.getMutableLiveData().observe(mLifecycleOwner, new Observer<List<Person>>() {
                        @Override
                        public void onChanged(List<Person> items) {
                            sectionItemsPerson.addItems((items));

                        }
                    });
                    break;
            }
        }
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public void addItems(List<Section<?>> dataList) {
        DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(new MyDiffUtilSectionCallbacks(this.dataList, dataList));
        this.dataList.clear();
        this.dataList.addAll(dataList);
        diffResult.dispatchUpdatesTo(this);
        notifyDataSetChanged();

    }

    public void addItems(Section<Object> section) {
        this.dataList.add(section);
        notifyDataSetChanged();
    }

    //Returns the view type of the item at position for the purposes of view recycling.
    @Override
    public int getItemViewType(int position) {
        if (dataList.get(position).getGenericType().equals(Movie.class)) {
            return MOVIE;
        } else if (dataList.get(position).getGenericType().equals(Cast.class)) {
            return PERSON;
        }
        return -1;
    }


    public static class SectionViewHolder extends RecyclerViewEmptySupport.ViewHolder {
        ListItemSectionBinding mBinding;

        SectionViewHolder(@NonNull ListItemSectionBinding sectionRowBinding) {
            super(sectionRowBinding.getRoot());
            this.mBinding = sectionRowBinding;

            this.mBinding.textSectionTitle.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // TODO: 22/07/2022 open detail section
                  /*  HomeFragmentDirections.ActionHomeFragmentToDetailedViewFragment action =
                            HomeFragmentDirections.actionHomeFragmentToDetailedViewFragment();
                    action.setSection(mBinding.textSectionTitle.getText().toString());
                    Navigation.findNavController(view).navigate(action);*/
                    Toast.makeText(view.getContext(), "Soon", Toast.LENGTH_SHORT).show();

                }
            });


        }
    }

    @Override
    public void onRowMoved(int fromPosition, int toPosition) {
        if (fromPosition < toPosition) {
            for (int i = fromPosition; i < toPosition; i++) {
                Collections.swap(dataList, i, i + 1);
            }
        } else {
            for (int i = fromPosition; i > toPosition; i--) {
                Collections.swap(dataList, i, i - 1);
            }
        }
        notifyItemMoved(fromPosition, toPosition);
    }

    @Override
    public void onRowSelected(SectionViewHolder myViewHolder) {
        myViewHolder.itemView.setBackgroundColor(ContextCompat.getColor(myViewHolder.itemView.getContext(), R.color.geyser));
        // it is safe to cancel other vibrations currently taking place
        vibe.cancel();
        vibe.vibrate(vibrationEffect1);


    }

    @Override
    public void onRowClear(SectionViewHolder myViewHolder) {
        myViewHolder.itemView.setBackgroundColor(Color.TRANSPARENT);

    }
}