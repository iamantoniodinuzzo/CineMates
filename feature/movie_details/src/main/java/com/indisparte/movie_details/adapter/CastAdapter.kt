package com.indisparte.movie_details.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import com.indisparte.person.Cast
import com.indisparte.movie_details.databinding.ListItemCastLongBinding
import com.indisparte.ui.adapter.BaseAdapter
import com.indisparte.ui.adapter.OnItemClickListener


/**
 * @author Antonio Di Nuzzo
 */
class CastAdapter :
    BaseAdapter<com.indisparte.person.Cast, ListItemCastLongBinding>(object : DiffUtil.ItemCallback<com.indisparte.person.Cast>() {
        override fun areItemsTheSame(oldItem: com.indisparte.person.Cast, newItem: com.indisparte.person.Cast): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: com.indisparte.person.Cast, newItem: com.indisparte.person.Cast): Boolean {
            return oldItem == newItem
        }
    }) {
    private var itemClickListener: OnItemClickListener<com.indisparte.person.Cast>? = null

    fun setOnItemClickListener(listener: OnItemClickListener<com.indisparte.person.Cast>) {
        itemClickListener = listener
    }
    override fun bind(binding: ListItemCastLongBinding, item: com.indisparte.person.Cast) {
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