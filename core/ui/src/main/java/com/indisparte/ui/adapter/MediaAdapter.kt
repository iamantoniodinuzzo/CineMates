package com.indisparte.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import com.indisparte.model.entity.Movie
import com.indisparte.model.entity.Person
import com.indisparte.model.entity.TvShow
import com.indisparte.ui.databinding.ListItemMediaSmallBinding
import com.indisparte.ui.databinding.ListItemPersonSmallBinding

fun interface OnItemClickListener<T> {
    fun onItemClick(item: T)
}

class MovieAdapter : BaseAdapter<Movie, ListItemMediaSmallBinding>(
    object : DiffUtil.ItemCallback<Movie>() {
        override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem.title == newItem.title
        }

        override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem == newItem
        }
    }
) {

    private var itemClickListener: OnItemClickListener<Movie>? = null

    fun setOnItemClickListener(listener: OnItemClickListener<Movie>) {
        itemClickListener = listener
    }


    override fun bind(binding: ListItemMediaSmallBinding, item: Movie) {
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

class TvShowAdapter : BaseAdapter<TvShow, ListItemMediaSmallBinding>(
    object : DiffUtil.ItemCallback<TvShow>() {
        override fun areItemsTheSame(oldItem: TvShow, newItem: TvShow): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(oldItem: TvShow, newItem: TvShow): Boolean {
            return oldItem == newItem
        }
    }
) {
    override fun bind(binding: ListItemMediaSmallBinding, item: TvShow) {
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

class PeopleAdapter : BaseAdapter<Person, ListItemPersonSmallBinding>(
    object : DiffUtil.ItemCallback<Person>() {
        override fun areItemsTheSame(oldItem: Person, newItem: Person): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(oldItem: Person, newItem: Person): Boolean {
            return oldItem == newItem
        }
    }
) {

    private var itemClickListener: OnItemClickListener<Person>? = null
    fun setOnItemClickListener(listener: OnItemClickListener<Person>) {
        itemClickListener = listener
    }
    override fun bind(binding: ListItemPersonSmallBinding, item: Person) {
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
