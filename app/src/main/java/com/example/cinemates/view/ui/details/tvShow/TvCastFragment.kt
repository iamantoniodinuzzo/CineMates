package com.example.cinemates.view.ui.details.tvShow

import android.os.Bundle
import android.view.View
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cinemates.databinding.ListItemPersonLongBinding
import com.example.cinemates.model.Cast
import com.example.cinemates.util.ViewSize
import com.example.cinemates.view.ui.ListFragment
import com.example.cinemates.view.ui.adapter.ActorAdapter
import kotlinx.coroutines.flow.collectLatest

class TvCastFragment : ListFragment<Cast,ListItemPersonLongBinding, ActorAdapter>(ActorAdapter()) {

    private val viewModel: TvDetailsViewModel by activityViewModels()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            recyclerView.layoutManager = LinearLayoutManager(requireContext())
            adapter.toggleLayoutType()
            viewLifecycleOwner.lifecycleScope.launchWhenStarted {
                viewModel.cast.collectLatest {cast->
                    adapter.updateItems(cast)
                    counter = cast.size
                }
            }
        }
    }

}