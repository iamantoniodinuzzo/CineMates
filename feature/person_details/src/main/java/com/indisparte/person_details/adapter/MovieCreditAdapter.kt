package com.indisparte.person_details.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import com.indisparte.movie_data.MovieCredit
import com.indisparte.person_details.databinding.ListItemMediaCreditsLongBinding
import com.indisparte.ui.adapter.BaseAdapter

/**
 *@author Antonio Di Nuzzo
 */
class MovieCreditAdapter : BaseAdapter<MovieCredit, ListItemMediaCreditsLongBinding>(object :
    DiffUtil.ItemCallback<MovieCredit>() {
    override fun areItemsTheSame(oldItem: MovieCredit, newItem: MovieCredit): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: MovieCredit, newItem: MovieCredit): Boolean {
        return oldItem.id == newItem.id
    }

}) {


    override fun bind(binding: ListItemMediaCreditsLongBinding, item: MovieCredit) {
        binding.apply {
            movieCredit = item
            root.setOnClickListener {
                itemClickListener?.onItemClick(item)
            }
        }
    }

    override fun createBinding(parent: ViewGroup, viewType: Int): ListItemMediaCreditsLongBinding {
        val inflater = LayoutInflater.from(parent.context)
        return ListItemMediaCreditsLongBinding.inflate(inflater, parent, false)
    }
}