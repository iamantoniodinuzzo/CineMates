package com.example.cinemates.ui.details.tvShow

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cinemates.databinding.ListItemCrewLongBinding
import com.example.cinemates.util.ViewSize
import com.example.cinemates.common.ListFragment
import com.example.cinemates.domain.model.Crew
import com.example.cinemates.ui.adapter.CrewAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

class TvCrewFragment : ListFragment<Crew, ListItemCrewLongBinding, CrewAdapter>(CrewAdapter()) {

    private val viewModel: TvDetailsViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.layoutRecyclerView.apply {
            recyclerView.layoutManager = LinearLayoutManager(requireContext())
            adapter.currentLayoutType =ViewSize.LONG
            viewLifecycleOwner.lifecycleScope.launchWhenCreated {
                viewModel.crew.collectLatest { crew ->
                    adapter.updateItems(crew)
                }
            }
        }
    }

}