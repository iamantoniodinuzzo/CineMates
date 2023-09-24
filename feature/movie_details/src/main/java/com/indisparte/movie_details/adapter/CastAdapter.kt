package com.indisparte.movie_details.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import com.indisparte.movie_details.databinding.ListItemCastLongBinding
import com.indisparte.person.Cast
import com.indisparte.ui.adapter.BaseAdapter


/**
 * @author Antonio Di Nuzzo
 */
class CastAdapter :
    BaseAdapter<Cast, ListItemCastLongBinding>(object : DiffUtil.ItemCallback<Cast>() {
        override fun areItemsTheSame(oldItem: Cast, newItem: Cast): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Cast, newItem: Cast): Boolean {
            return oldItem == newItem
        }
    }) {

    override fun bind(binding: ListItemCastLongBinding, item: Cast) {
        binding.cast = item
        binding.root.setOnClickListener {
            itemClickListener?.onItemClick(item)
        }
    }

    override fun createBinding(parent: ViewGroup, viewType: Int): ListItemCastLongBinding {
        val inflater = LayoutInflater.from(parent.context)
        return ListItemCastLongBinding.inflate(inflater, parent, false)
    }

}