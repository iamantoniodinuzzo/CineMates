package com.example.cinemates.adapter;

import androidx.annotation.NonNull;

import com.example.cinemates.databinding.ListItemPersonInformationBinding;
import com.example.cinemates.util.RecyclerViewEmptySupport;

/**
 * @author Antonio Di Nuzzo
 * Created 28/07/2022 at 08:51
 */
class PersonViewHolder extends RecyclerViewEmptySupport.ViewHolder {
    ListItemPersonInformationBinding mBinding;

    PersonViewHolder(@NonNull ListItemPersonInformationBinding listItemPersonInformationBinding) {
        super(listItemPersonInformationBinding.getRoot());
        this.mBinding = listItemPersonInformationBinding;


    }


}
