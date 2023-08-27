package com.indisparte.person_details.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import com.indisparte.person_details.databinding.ListItemMediaCreditsLongBinding
import com.indisparte.ui.adapter.BaseAdapter
import com.indisparte.ui.adapter.OnItemClickListener
import timber.log.Timber

/**
 *@author Antonio Di Nuzzo
 */
class MovieCreditAdapter : BaseAdapter<com.indisparte.movie_data.MovieCredit, ListItemMediaCreditsLongBinding>(object :
    DiffUtil.ItemCallback<com.indisparte.movie_data.MovieCredit>() {
    override fun areItemsTheSame(oldItem: com.indisparte.movie_data.MovieCredit, newItem: com.indisparte.movie_data.MovieCredit): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: com.indisparte.movie_data.MovieCredit, newItem: com.indisparte.movie_data.MovieCredit): Boolean {
        return oldItem.id == newItem.id
    }

}) {
    private val LOG = Timber.tag(MovieCreditAdapter::class.java.simpleName)
    private var itemClickListener: OnItemClickListener<com.indisparte.movie_data.MovieCredit>? = null
    fun setOnItemClickListener(listener: OnItemClickListener<com.indisparte.movie_data.MovieCredit>) {
        itemClickListener = listener
    }

    override fun bind(binding: ListItemMediaCreditsLongBinding, item: com.indisparte.movie_data.MovieCredit) {
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