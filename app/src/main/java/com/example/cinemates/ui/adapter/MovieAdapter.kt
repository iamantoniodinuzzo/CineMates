package com.example.cinemates.ui.adapter


/**
 * @author Antonio Di Nuzzo (Indisparte)
 */
import androidx.databinding.ViewDataBinding
import androidx.navigation.Navigation
import com.example.cinemates.NavGraphDirections
import com.example.cinemates.R
import com.example.cinemates.databinding.ListItemMovieLongBinding
import com.example.cinemates.databinding.ListItemMovieSmallBinding
import com.example.cinemates.model.Movie

class MovieAdapter :
    DoubleViewSizeAdapter<Movie, ListItemMovieLongBinding, ListItemMovieSmallBinding>(
        R.layout.list_item_movie_long,
        R.layout.list_item_movie_small,
        emptyList()
    ) {


    override fun onBindLongItem(binding: ListItemMovieLongBinding, item: Movie) {
        binding.movie = item
        navigateToDetails(binding, item)
    }

    override fun onBindSmallItem(binding: ListItemMovieSmallBinding, item: Movie) {
        binding.movie = item
        navigateToDetails(binding, item)
    }

    private fun navigateToDetails(binding: ViewDataBinding, item: Movie) {
        binding.root.setOnClickListener { view ->
            val action = NavGraphDirections.actionGlobalMovieDetailsFragment(item)
            Navigation.findNavController(view).navigate(action)
        }

    }
}
