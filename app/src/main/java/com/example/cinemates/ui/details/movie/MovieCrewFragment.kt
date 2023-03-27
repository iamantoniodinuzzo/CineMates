package com.example.cinemates.ui.details.movie

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cinemates.databinding.ListItemCrewLongBinding
import com.example.cinemates.model.Crew
import com.example.cinemates.ui.ListFragment
import com.example.cinemates.ui.adapter.CrewAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class MovieCrewFragment : ListFragment<Crew, ListItemCrewLongBinding, CrewAdapter>(CrewAdapter()) {

    private val viewModel: MovieDetailsViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            recyclerView.layoutManager = LinearLayoutManager(requireContext())
            adapter.toggleLayoutType()
            viewLifecycleOwner.lifecycleScope.launchWhenStarted {
                viewModel.crew.collectLatest {crew->
                    adapter.updateItems(crew)
                    counter = crew.size
                }
            }
        }
    }

}