package com.example.cinemates.adapter

import androidx.recyclerview.widget.RecyclerView
import com.example.cinemates.databinding.ListItemPersonLongBinding
import com.example.cinemates.databinding.ListItemPersonSmallBinding

/**
 * @author Antonio Di Nuzzo
 * Created 28/07/2022 at 08:51
 */
internal class PersonViewHolder : RecyclerView.ViewHolder {
    var mLongBinding: ListItemPersonLongBinding? = null
    var mSmallBinding: ListItemPersonSmallBinding? = null

    constructor(listItemPersonLongBinding: ListItemPersonLongBinding) : super(
        listItemPersonLongBinding.root
    ) {
        mLongBinding = listItemPersonLongBinding
    }

    constructor(listItemPersonSmallBinding: ListItemPersonSmallBinding) : super(
        listItemPersonSmallBinding.root
    ) {
        mSmallBinding = listItemPersonSmallBinding
    }
}