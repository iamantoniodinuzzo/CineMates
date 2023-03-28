package com.example.cinemates.ui.details.tvShow

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cinemates.databinding.ListItemCrewLongBinding
import com.example.cinemates.model.Crew
import com.example.cinemates.util.ViewSize
import com.example.cinemates.ui.ListFragment
import com.example.cinemates.ui.adapter.CrewAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class TvCrewFragment : ListFragment<Crew, ListItemCrewLongBinding, CrewAdapter>(CrewAdapter()) {

    private val viewModel: TvDetailsViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            recyclerView.layoutManager = LinearLayoutManager(requireContext())
            adapter.currentLayoutType =ViewSize.LONG
            viewLifecycleOwner.lifecycleScope.launchWhenCreated {
                viewModel.crew.collectLatest { crew ->
                    adapter.updateItems(crew)
                    counter = crew.size
                }
            }
        }
    }

}