package com.indisparte.movie_details.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import com.indisparte.model.entity.Backdrop
import com.indisparte.movie_details.databinding.ListItemBackdropBinding
import com.indisparte.ui.adapter.BaseAdapter


/**
 * @author Antonio Di Nuzzo (Indisparte)
 */
class BackdropAdapter :
    BaseAdapter<Backdrop, ListItemBackdropBinding>(object : DiffUtil.ItemCallback<Backdrop>() {
        override fun areItemsTheSame(oldItem: Backdrop, newItem: Backdrop): Boolean {
            return oldItem.completePosterPathW780 == newItem.completePosterPathW780
        }

        override fun areContentsTheSame(oldItem: Backdrop, newItem: Backdrop): Boolean {
            return oldItem == newItem
        }
    }) {
    override fun bind(binding: ListItemBackdropBinding, item: Backdrop) {
        binding.path = item.completePosterPathW780
    }

    override fun createBinding(parent: ViewGroup, viewType: Int): ListItemBackdropBinding {
        val inflater = LayoutInflater.from(parent.context)
        return ListItemBackdropBinding.inflate(inflater, parent, false)
    }

}