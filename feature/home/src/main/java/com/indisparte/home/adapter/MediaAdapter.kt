package com.indisparte.home.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DiffUtil
import com.indisparte.home.databinding.ListItemMediaSmallBinding
import com.indisparte.home.databinding.ListItemPersonSmallBinding
import com.indisparte.model.entity.Movie
import com.indisparte.model.entity.Person
import com.indisparte.model.entity.TvShow
import com.indisparte.navigation.NavigationFlow
import com.indisparte.navigation.ToFlowNavigable
import com.indisparte.ui.adapter.BaseAdapter


class MovieAdapter(val fragment: Fragment) : BaseAdapter<Movie, ListItemMediaSmallBinding>(
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
                Log.d("MovieAdapter", "Movie id: ${item.id}")
                ((fragment.requireActivity()) as ToFlowNavigable).navigateToFlow(NavigationFlow.MovieDetailsFlow(item.id))
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
    override fun bind(binding: ListItemPersonSmallBinding, item: Person) {
        binding.apply {
            person = item
            root.setOnClickListener {
                // TODO: open person details
            }
        }
    }

    override fun createBinding(parent: ViewGroup, viewType: Int): ListItemPersonSmallBinding {
        val inflater = LayoutInflater.from(parent.context)
        return ListItemPersonSmallBinding.inflate(inflater, parent, false)
    }
}
