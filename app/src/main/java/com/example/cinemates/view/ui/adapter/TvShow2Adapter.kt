package com.example.cinemates.view.ui.adapter

import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.cinemates.NavGraphDirections
import com.example.cinemates.databinding.ListItemMovieSmallBinding
import com.example.cinemates.databinding.ListItemTvSmallBinding
import com.example.cinemates.model.Movie
import com.example.cinemates.model.TvShow
import com.example.cinemates.util.inflater


/**
 * @author Antonio Di Nuzzo (Indisparte)
 */
class TvShow2Adapter(private var tvShows: List<TvShow>) :
    RecyclerView.Adapter<TvShow2Adapter.TvShowViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvShowViewHolder {
        val view = parent inflater ListItemTvSmallBinding::inflate
        return TvShowViewHolder(view)
    }

    override fun onBindViewHolder(holder: TvShowViewHolder, position: Int) {
        val movie = tvShows[position]
        holder.bind(movie)
    }

    override fun getItemCount(): Int {
        return tvShows.size
    }

    fun updateTvShows(newMovies: List<TvShow>) {
        val diffResult = DiffUtil.calculateDiff(TvShowDiffCallback(tvShows, newMovies))
        tvShows = newMovies
        diffResult.dispatchUpdatesTo(this)
    }

    class TvShowViewHolder(val binding: ListItemTvSmallBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(tvShow: TvShow) {
            binding.tv = tvShow
            binding.root.setOnClickListener {
                val action = NavGraphDirections.actionGlobalTvDetailsFragment(tvShow)
                Navigation.findNavController(it).navigate(action)
            }
        }
    }

    private class TvShowDiffCallback(
        private val oldTvShow: List<TvShow>,
        private val newTvShow: List<TvShow>
    ) : DiffUtil.Callback() {

        override fun getOldListSize(): Int {
            return oldTvShow.size
        }

        override fun getNewListSize(): Int {
            return newTvShow.size
        }

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldTvShow[oldItemPosition].id == newTvShow[newItemPosition].id
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldTvShow[oldItemPosition] == newTvShow[newItemPosition]
        }
    }
}
