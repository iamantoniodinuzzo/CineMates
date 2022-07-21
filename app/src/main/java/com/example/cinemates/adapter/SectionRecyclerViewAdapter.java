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
import androidx.recyclerview.widget.RecyclerView;

import com.example.cinemates.R;
import com.example.cinemates.model.Section;
import com.example.cinemates.util.ItemMoveCallback;
import com.example.cinemates.util.RecyclerViewEmptySupport;
import  com.example.cinemates.databinding.ListItemSectionBinding;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Antonio Di Nuzzo
 * Created 15/12/2021 at 16:36
 */
public class SectionRecyclerViewAdapter<T> extends RecyclerView.Adapter<SectionRecyclerViewAdapter.SectionViewHolder> implements ItemMoveCallback.ItemTouchHelperContract {
    private final List<Section<T>> dataList = new ArrayList<>();
    private final LifecycleOwner mLifecycleOwner;
    private final Vibrator vibe;
    // TODO: 21/07/2022
    //private final RecyclerView.Adapter adapter;
    private final VibrationEffect vibrationEffect1;

    public SectionRecyclerViewAdapter(LifecycleOwner lifecycleOwner, Context context) {
        mLifecycleOwner = lifecycleOwner;
        vibe = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
        vibrationEffect1 = VibrationEffect.createOneShot(50, VibrationEffect.DEFAULT_AMPLITUDE);

    }

    @NonNull
    @Override
    public SectionViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
       ListItemSectionBinding sectionRowBinding =  ListItemSectionBinding.inflate(layoutInflater, parent, false);
        return new SectionViewHolder(sectionRowBinding);
    }

    @Override
    public void onBindViewHolder(SectionViewHolder holder, int position) {
        Section<T> section = dataList.get(position);
        holder.mBinding.setSection(section);
        holder.mBinding.executePendingBindings();

        SectionItemsRecyclerViewAdapter<T> sectionItemsRecyclerViewAdapter = new SectionItemsRecyclerViewAdapter<>();
        holder.mBinding.recyclerView.setAdapter(sectionItemsRecyclerViewAdapter);
        holder.mBinding.recyclerView.setEmptyView(holder.mBinding.emptyView.getRoot());
        section.getMutableLiveData().observe(mLifecycleOwner, new Observer<List<T>>() {
            @Override
            public void onChanged(List<T> items) {
                sectionItemsRecyclerViewAdapter.addItems((items));

            }
        });


    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public void addItems(List<Section<T>> dataList) {
        this.dataList.addAll(dataList);
        notifyDataSetChanged();
    }

    public void addItems(Section<T> section) {
        this.dataList.add(section);
        notifyDataSetChanged();
    }

    public static class SectionViewHolder extends RecyclerViewEmptySupport.ViewHolder {
        ListItemSectionBinding mBinding;

        SectionViewHolder(@NonNull ListItemSectionBinding sectionRowBinding) {
            super(sectionRowBinding.getRoot());
            this.mBinding = sectionRowBinding;

            this.mBinding.textSectionTitle.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

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