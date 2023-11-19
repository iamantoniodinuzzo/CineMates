package com.indisparte.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import com.indisparte.media_list.MediaList
import com.indisparte.ui.databinding.ListItemMediaListBinding

/**
 *@author Antonio Di Nuzzo
 */
class MediaListAdapter :
    BaseAdapter<MediaList, ListItemMediaListBinding>(object : DiffUtil.ItemCallback<MediaList>() {
        override fun areItemsTheSame(oldItem: MediaList, newItem: MediaList): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: MediaList, newItem: MediaList): Boolean {
            return oldItem == newItem
        }

    }) {
    override fun bind(binding: ListItemMediaListBinding, item: MediaList) {
        with(binding) {
            list = item
            root.setOnClickListener {
                itemClickListener?.onItemClick(item)
            }
        }
    }

    override fun createBinding(parent: ViewGroup, viewType: Int): ListItemMediaListBinding {
        val inflater = LayoutInflater.from(parent.context)
        return ListItemMediaListBinding.inflate(inflater, parent, false)
    }

}