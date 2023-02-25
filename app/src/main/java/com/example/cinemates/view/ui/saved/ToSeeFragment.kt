package com.example.cinemates.view.ui.saved

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import com.example.cinemates.view.ui.adapter.MovieAdapter
import com.example.cinemates.model.Movie
import com.example.cinemates.view.ui.ListFragment
import com.example.cinemates.view.dbviewmodel.DbMovieViewModel

/**
 * @author Antonio Di Nuzzo
 * Created 25/07/2022 at 08:24
 */
class ToSeeFragment : ListFragment<Movie, MovieAdapter>(MovieAdapter()) {
    private val dbViewModel: DbMovieViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
           /* dbViewModel.toSee.observe(viewLifecycleOwner){movies->
                adapter.addItems(movies)
                counter=adapter.itemCount
            }*/
        }
    }


}