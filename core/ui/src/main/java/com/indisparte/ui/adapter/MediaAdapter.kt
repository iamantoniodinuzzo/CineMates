package com.indisparte.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import com.indisparte.ui.databinding.ListItemMediaSmallBinding
import com.indisparte.ui.databinding.ListItemPersonSmallBinding

fun interface OnItemClickListener<T> {
    fun onItemClick(item: T)
}

class MovieAdapter : BaseAdapter<com.indisparte.movie_data.Movie, ListItemMediaSmallBinding>(
    object : DiffUtil.ItemCallback<com.indisparte.movie_data.Movie>() {
        override fun areItemsTheSame(oldItem: com.indisparte.movie_data.Movie, newItem: com.indisparte.movie_data.Movie): Boolean {
            return oldItem.title == newItem.title
        }

        override fun areContentsTheSame(oldItem: com.indisparte.movie_data.Movie, newItem: com.indisparte.movie_data.Movie): Boolean {
            return oldItem == newItem
        }
    }
) {

    private var itemClickListener: OnItemClickListener<com.indisparte.movie_data.Movie>? = null

    fun setOnItemClickListener(listener: OnItemClickListener<com.indisparte.movie_data.Movie>) {
        itemClickListener = listener
    }


    override fun bind(binding: ListItemMediaSmallBinding, item: com.indisparte.movie_data.Movie) {
        binding.apply {
            media = item
            root.setOnClickListener {
                itemClickListener?.onItemClick(item)

            }
        }
    }

    override fun createBinding(parent: ViewGroup, viewType: Int): ListItemMediaSmallBinding {
        val inflater = LayoutInflater.from(parent.context)
        return ListItemMediaSmallBinding.inflate(inflater, parent, false)
    }
}

class TvShowAdapter : BaseAdapter<com.indisparte.tv.TvShow, ListItemMediaSmallBinding>(
    object : DiffUtil.ItemCallback<com.indisparte.tv.TvShow>() {
        override fun areItemsTheSame(oldItem: com.indisparte.tv.TvShow, newItem: com.indisparte.tv.TvShow): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(oldItem: com.indisparte.tv.TvShow, newItem: com.indisparte.tv.TvShow): Boolean {
            return oldItem == newItem
        }
    }
) {
    override fun bind(binding: ListItemMediaSmallBinding, item: com.indisparte.tv.TvShow) {
        binding.apply {
            media = item
            root.setOnClickListener {
                // TODO: open tv show details
            }
        }
    }

    override fun createBinding(parent: ViewGroup, viewType: Int): ListItemMediaSmallBinding {
        val inflater = LayoutInflater.from(parent.context)
        return ListItemMediaSmallBinding.inflate(inflater, parent, false)
    }
}

class PeopleAdapter : BaseAdapter<com.indisparte.person.Person, ListItemPersonSmallBinding>(
    object : DiffUtil.ItemCallback<com.indisparte.person.Person>() {
        override fun areItemsTheSame(oldItem: com.indisparte.person.Person, newItem: com.indisparte.person.Person): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(oldItem: com.indisparte.person.Person, newItem: com.indisparte.person.Person): Boolean {
            return oldItem == newItem
        }
    }
) {

    private var itemClickListener: OnItemClickListener<com.indisparte.person.Person>? = null
    fun setOnItemClickListener(listener: OnItemClickListener<com.indisparte.person.Person>) {
        itemClickListener = listener
    }
    override fun bind(binding: ListItemPersonSmallBinding, item: com.indisparte.person.Person) {
        binding.apply {
            person = item
            ivAvatar.isClickable = false
            root.setOnClickListener {
                itemClickListener?.onItemClick(item)
            }
        }
    }

    override fun createBinding(parent: ViewGroup, viewType: Int): ListItemPersonSmallBinding {
        val inflater = LayoutInflater.from(parent.context)
        return ListItemPersonSmallBinding.inflate(inflater, parent, false)
    }
}
