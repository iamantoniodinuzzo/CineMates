package com.example.cinemates.view.ui.details.movie

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cinemates.model.Crew
import com.example.cinemates.view.ui.adapter.PersonAdapter
import com.example.cinemates.model.Person
import com.example.cinemates.util.ViewSize
import com.example.cinemates.view.ui.ListFragment
import com.example.cinemates.view.ui.adapter.CrewAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class MovieCrewFragment : ListFragment<Crew, CrewAdapter>(CrewAdapter()) {

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
                viewModel.crew.collectLatest {crew->
                    adapter.addItems(crew)
                    counter = crew.size
                }
            }
        }
    }

}