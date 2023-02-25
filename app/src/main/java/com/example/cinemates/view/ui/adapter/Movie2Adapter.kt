package com.example.cinemates.view.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.cinemates.NavGraphDirections
import com.example.cinemates.R
import com.example.cinemates.databinding.ListItemMovieSmallBinding
import com.example.cinemates.model.Movie
import com.example.cinemates.util.inflater


/**
 * @author Antonio Di Nuzzo (Indisparte)
 */
class Movie2Adapter(private var movies: List<Movie>) : RecyclerView.Adapter<Movie2Adapter.MovieViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = parent inflater ListItemMovieSmallBinding::inflate
        return MovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = movies[position]
        holder.bind(movie)
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    fun updateMovies(newMovies: List<Movie>) {
        val diffResult = DiffUtil.calculateDiff(MoviesDiffCallback(movies, newMovies))
        movies = newMovies
        diffResult.dispatchUpdatesTo(this)
    }

    class MovieViewHolder(val binding : ListItemMovieSmallBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(movie: Movie) {
            binding.movie = movie
            binding.root.setOnClickListener {
                val action = NavGraphDirections.actionGlobalMovieDetailsFragment(movie)
                Navigation.findNavController(it).navigate(action)
            }
        }
    }

    private class MoviesDiffCallback(private val oldMovies: List<Movie>, private val newMovies: List<Movie>) : DiffUtil.Callback() {

        override fun getOldListSize(): Int {
            return oldMovies.size
        }

        override fun getNewListSize(): Int {
            return newMovies.size
        }

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldMovies[oldItemPosition].id == newMovies[newItemPosition].id
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldMovies[oldItemPosition] == newMovies[newItemPosition]
        }
    }
}
