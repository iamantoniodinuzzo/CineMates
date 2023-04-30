package com.example.cinemates.ui.details.tvShow

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cinemates.common.ListFragment
import com.example.cinemates.databinding.ListItemMediaSmallBinding
import com.example.cinemates.domain.model.tv.Season
import com.example.cinemates.ui.adapter.SeasonAdapter

class TvSeasonsFragment :
    ListFragment<Season, ListItemMediaSmallBinding, SeasonAdapter>(SeasonAdapter()) {

    private val viewModel: TvDetailsViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.layoutRecyclerView.recyclerView.layoutManager = LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false)
        viewLifecycleOwner.lifecycleScope.launchWhenCreated {
            viewModel.selectedTv.collect { tvShow ->
                tvShow?.let {
                    adapter.items = it.seasons
                }
            }
        }

    }


}