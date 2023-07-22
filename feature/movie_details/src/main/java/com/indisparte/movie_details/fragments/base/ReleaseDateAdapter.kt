package com.indisparte.movie_details.fragments.base

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import com.indisparte.model.entity.ReleaseDate
import com.indisparte.movie_details.databinding.ListItemReleaseInfoBinding
import com.indisparte.ui.adapter.BaseAdapter


/**
 * @author Antonio Di Nuzzo (Indisparte)
 */
class ReleaseDateAdapter : BaseAdapter<ReleaseDate, ListItemReleaseInfoBinding>(object :
    DiffUtil.ItemCallback<ReleaseDate>() {
    override fun areItemsTheSame(oldItem: ReleaseDate, newItem: ReleaseDate): Boolean {
        return oldItem.releaseType == newItem.releaseType
    }

    override fun areContentsTheSame(oldItem: ReleaseDate, newItem: ReleaseDate): Boolean {
        return oldItem == newItem
    }

}) {
    override fun bind(binding: ListItemReleaseInfoBinding, item: ReleaseDate) {
        binding.releaseDate = item
    }

    override fun createBinding(parent: ViewGroup, viewType: Int): ListItemReleaseInfoBinding {
        val inflater = LayoutInflater.from(parent.context)
        return ListItemReleaseInfoBinding.inflate(inflater, parent, false)
    }

}