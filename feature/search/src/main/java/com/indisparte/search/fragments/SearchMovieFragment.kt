package com.indisparte.search.fragments

import androidx.recyclerview.widget.GridLayoutManager
import com.indisparte.model.entity.Movie
import com.indisparte.ui.adapter.MovieAdapter
import com.indisparte.ui.adapter.OnItemClickListener
import com.indisparte.ui.databinding.ListItemMediaSmallBinding
import com.indisparte.ui.fragment.ListFragment


/**
 * @author Antonio Di Nuzzo
 */
class SearchMovieFragment :
    ListFragment<Movie, ListItemMediaSmallBinding, MovieAdapter>(MovieAdapter()) {
    override fun addItemsToTheAdapter() {
        TODO("Not yet implemented")
    }

    override fun initializeViews() {
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = GridLayoutManager(requireContext(), 3)
        adapter.setOnItemClickListener(object : OnItemClickListener<Movie> {
            override fun onItemClick(item: Movie) {
                TODO("Not yet implemented")
            }

        })
    }

}