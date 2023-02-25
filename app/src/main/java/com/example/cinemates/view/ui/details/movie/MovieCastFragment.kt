package com.example.cinemates.view.ui.details.movie

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cinemates.model.Cast
import com.example.cinemates.view.ui.adapter.ActorAdapter
import com.example.cinemates.model.Person
import com.example.cinemates.util.ViewSize
import com.example.cinemates.view.ui.ListFragment
import kotlinx.coroutines.flow.collectLatest

class MovieCastFragment : ListFragment<Cast, ActorAdapter>(ActorAdapter()) {

    private val viewModel: MovieDetailsViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        adapter.viewSize = ViewSize.LONG
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            recyclerView.layoutManager = LinearLayoutManager(requireContext())
            viewLifecycleOwner.lifecycleScope.launchWhenStarted {
                viewModel.cast.collectLatest {cast->
                    adapter.addItems(cast)
                    counter = cast.size
                }
            }
        }
    }

}