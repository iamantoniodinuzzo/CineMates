package com.indisparte.movie_details.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import com.indisparte.movie_details.databinding.ListItemReleaseInfoBinding
import com.indisparte.ui.adapter.BaseAdapter


/**
 * @author Antonio Di Nuzzo (Indisparte)
 */
class ReleaseDateAdapter : BaseAdapter<com.indisparte.movie_data.ReleaseDate, ListItemReleaseInfoBinding>(object :
    DiffUtil.ItemCallback<com.indisparte.movie_data.ReleaseDate>() {
    override fun areItemsTheSame(oldItem: com.indisparte.movie_data.ReleaseDate, newItem: com.indisparte.movie_data.ReleaseDate): Boolean {
        return oldItem.releaseType == newItem.releaseType
    }

    override fun areContentsTheSame(oldItem: com.indisparte.movie_data.ReleaseDate, newItem: com.indisparte.movie_data.ReleaseDate): Boolean {
        return oldItem == newItem
    }

}) {
    override fun bind(binding: ListItemReleaseInfoBinding, item: com.indisparte.movie_data.ReleaseDate) {
        binding.releaseDate = item
    }

    override fun createBinding(parent: ViewGroup, viewType: Int): ListItemReleaseInfoBinding {
        val inflater = LayoutInflater.from(parent.context)
        return ListItemReleaseInfoBinding.inflate(inflater, parent, false)
    }

}