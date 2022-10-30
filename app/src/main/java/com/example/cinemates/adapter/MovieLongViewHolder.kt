package com.example.cinemates.adapter

import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.cinemates.NavGraphDirections
import com.example.cinemates.databinding.ListItemMovieLongBinding
import com.example.cinemates.model.data.Movie

/**
 * @author Antonio Di Nuzzo (Indisparte)
 */
class MovieLongViewHolder(
    private val binding: ListItemMovieLongBinding,
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(movie: Movie) {
        binding.apply {
            setMovie(movie)
            root.setOnClickListener {
                val action = NavGraphDirections.actionGlobalMovieDetailsFragment(movie)
                Navigation.findNavController(it).navigate(action)
            }
        }
    }
}