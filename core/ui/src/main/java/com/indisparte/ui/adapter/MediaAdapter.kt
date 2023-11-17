package com.indisparte.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import com.indisparte.base.Media
import com.indisparte.movie_data.Movie
import com.indisparte.person.Person
import com.indisparte.tv.TvShow
import com.indisparte.ui.databinding.ListItemMediaSmallBinding
import com.indisparte.ui.databinding.ListItemPersonSmallBinding


//Media Adapter
class MediaAdapter : BaseAdapter<Media, ListItemMediaSmallBinding>(
    object : DiffUtil.ItemCallback<Media>() {
        override fun areItemsTheSame(oldItem: Media, newItem: Media): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Media, newItem: Media): Boolean {
            return oldItem == newItem
        }
    }
) {

    override fun bind(binding: ListItemMediaSmallBinding, item: Media) {
        binding.apply {
            media = item
            root.setOnClickListener {
                itemClickListener?.onItemClick(item) // TODO: Show a toast if is null

            }
        }
    }

    override fun createBinding(parent: ViewGroup, viewType: Int): ListItemMediaSmallBinding {
        val inflater = LayoutInflater.from(parent.context)
        return ListItemMediaSmallBinding.inflate(inflater, parent, false)
    }
}




// Movie Adapter
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

    override fun bind(binding: ListItemMediaSmallBinding, item: Movie) {
        binding.apply {
            media = item
            root.setOnClickListener {
                itemClickListener?.onItemClick(item) // TODO: Show a toast if is null

            }
        }
    }

    override fun createBinding(parent: ViewGroup, viewType: Int): ListItemMediaSmallBinding {
        val inflater = LayoutInflater.from(parent.context)
        return ListItemMediaSmallBinding.inflate(inflater, parent, false)
    }
}

//Tv Show Adapter

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
                itemClickListener?.onItemClick(item) // TODO: Show a toast if is null
            }
        }
    }

    override fun createBinding(parent: ViewGroup, viewType: Int): ListItemMediaSmallBinding {
        val inflater = LayoutInflater.from(parent.context)
        return ListItemMediaSmallBinding.inflate(inflater, parent, false)
    }
}

//Person Adapter
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

    override fun bind(binding: ListItemPersonSmallBinding, item: Person) {
        binding.apply {
            person = item
            ivAvatar.isClickable = false
            root.setOnClickListener {
                itemClickListener?.onItemClick(item) // TODO: Show a toast if is null
            }
        }
    }

    override fun createBinding(parent: ViewGroup, viewType: Int): ListItemPersonSmallBinding {
        val inflater = LayoutInflater.from(parent.context)
        return ListItemPersonSmallBinding.inflate(inflater, parent, false)
    }
}
