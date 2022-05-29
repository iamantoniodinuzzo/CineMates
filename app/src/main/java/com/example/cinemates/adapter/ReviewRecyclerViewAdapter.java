package com.example.cinemates.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cinemates.databinding.ListItemPersonInformationBinding;
import com.example.cinemates.databinding.ListItemReviewBinding;
import com.example.cinemates.model.Cast;
import com.example.cinemates.model.Review;
import com.example.cinemates.util.RecyclerViewEmptySupport;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Antonio Di Nuzzo
 * Created 29/05/2022 at 10:31
 */
public class ReviewRecyclerViewAdapter extends RecyclerViewEmptySupport.Adapter<ReviewRecyclerViewAdapter.ReviewViewHolder> {
    private List<Review> dataList = new ArrayList<>();

    @NonNull
    @Override
    public ReviewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ListItemReviewBinding mediaBinding = ListItemReviewBinding.inflate(layoutInflater, parent, false);
        return new ReviewViewHolder(mediaBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ReviewViewHolder holder, int position) {
        Review review = dataList.get(position);
        holder.mBinding.setReview(review);
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

    public void addItems(List<Review> dataList) {
        this.dataList.addAll(dataList);
        notifyDataSetChanged();
    }

    public class ReviewViewHolder extends RecyclerView.ViewHolder {
        ListItemReviewBinding mBinding;

        public ReviewViewHolder(ListItemReviewBinding mediaBinding) {
            super(mediaBinding.getRoot());
            mBinding = mediaBinding;
        }
    }
}
