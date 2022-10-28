package com.example.cinemates.adapter

import androidx.navigation.Navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.cinemates.NavGraphDirections
import com.example.cinemates.databinding.ListItemMovieSmallBinding
import com.example.cinemates.model.data.Movie

/**
 * @author Antonio Di Nuzzo (Indisparte)
 */
class MovieSmallViewHolder(
    private val binding: ListItemMovieSmallBinding,
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(movie: Movie) {
        binding.apply {
            setMovie(movie)
            root.setOnClickListener {
                val action = NavGraphDirections.actionGlobalMovieDetailsFragment(movie)
                findNavController(it).navigate(action)
            }
        }
    }
}