package com.example.cinemates.view.ui.adapter


/**
 * @author Antonio Di Nuzzo (Indisparte)
 */
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import com.example.cinemates.NavGraphDirections
import com.example.cinemates.R
import com.example.cinemates.databinding.ListItemMovieSmallBinding
import com.example.cinemates.model.Movie

class MovieAdapter : BaseAdapter<Movie, ListItemMovieSmallBinding>(R.layout.list_item_movie_small, emptyList()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<ListItemMovieSmallBinding>(inflater, itemLayoutResId, parent, false)
        return BaseViewHolder(binding)
    }

    override fun onBindItem(binding: ListItemMovieSmallBinding, item: Movie) {
        binding.movie = item
        binding.root.setOnClickListener {view->
            val action = NavGraphDirections.actionGlobalMovieDetailsFragment(item)
            Navigation.findNavController(view).navigate(action)
        }

    }
}
