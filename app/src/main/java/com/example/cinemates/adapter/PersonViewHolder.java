package com.example.cinemates.adapter;

import androidx.annotation.NonNull;

import com.example.cinemates.databinding.ListItemPersonLongBinding;
import com.example.cinemates.databinding.ListItemPersonSmallBinding;
import com.example.cinemates.util.RecyclerViewEmptySupport;

/**
 * @author Antonio Di Nuzzo
 * Created 28/07/2022 at 08:51
 */
class PersonViewHolder extends RecyclerViewEmptySupport.ViewHolder {
    ListItemPersonLongBinding mLongBinding;
    ListItemPersonSmallBinding mSmallBinding;

    PersonViewHolder(@NonNull ListItemPersonLongBinding listItemPersonLongBinding) {
        super(listItemPersonLongBinding.getRoot());
        this.mLongBinding = listItemPersonLongBinding;

    }

    PersonViewHolder(@NonNull ListItemPersonSmallBinding listItemPersonSmallBinding) {
        super(listItemPersonSmallBinding.getRoot());
        this.mSmallBinding = listItemPersonSmallBinding;


    }


}
