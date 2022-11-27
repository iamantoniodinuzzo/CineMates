package com.example.cinemates.view.ui.details.movie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.cinemates.adapter.MovieAdapter
import com.example.cinemates.adapter.PersonAdapter
import com.example.cinemates.databinding.FragmentMovieCastBinding
import com.example.cinemates.model.entities.Cast
import com.example.cinemates.model.entities.Movie
import com.example.cinemates.model.entities.Person
import com.example.cinemates.util.ViewSize
import com.example.cinemates.view.ui.ListFragment

class MovieRecommendedFragment : ListFragment<Movie,MovieAdapter>(MovieAdapter()) {

    private val viewModel: MovieDetailsViewModel by activityViewModels()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.recommendedMovies.observe(viewLifecycleOwner) { recommended ->
            adapter.addItems(recommended)
            binding.counter = recommended.size
        }
    }

}