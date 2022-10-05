package com.example.cinemates.adapter

import androidx.recyclerview.widget.RecyclerView
import com.example.cinemates.databinding.ListItemPersonLongBinding
import com.example.cinemates.databinding.ListItemPersonSmallBinding

/**
 * @author Antonio Di Nuzzo
 * Created 28/07/2022 at 08:51
 */
internal class PersonViewHolder : RecyclerView.ViewHolder {
    var longBinding: ListItemPersonLongBinding? = null
    var smallBinding: ListItemPersonSmallBinding? = null

    constructor(listItemPersonLongBinding: ListItemPersonLongBinding) : super(
        listItemPersonLongBinding.root
    ) {
        longBinding = listItemPersonLongBinding
    }

    constructor(listItemPersonSmallBinding: ListItemPersonSmallBinding) : super(
        listItemPersonSmallBinding.root
    ) {
        smallBinding = listItemPersonSmallBinding
    }
}