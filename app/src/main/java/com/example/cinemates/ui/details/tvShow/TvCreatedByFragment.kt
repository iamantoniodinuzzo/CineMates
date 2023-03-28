package com.example.cinemates.ui.details.tvShow

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cinemates.databinding.ListItemPersonLongBinding
import com.example.cinemates.model.Cast
import com.example.cinemates.model.CreatedBy
import com.example.cinemates.model.Person
import com.example.cinemates.util.ViewSize
import com.example.cinemates.ui.ListFragment
import com.example.cinemates.ui.adapter.ActorAdapter
import com.example.cinemates.ui.adapter.PersonAdapter
import kotlinx.coroutines.flow.collectLatest

class TvCreatedByFragment : ListFragment<Cast, ListItemPersonLongBinding, ActorAdapter>(ActorAdapter()) {

    private val viewModel: TvDetailsViewModel by activityViewModels()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            recyclerView.layoutManager = LinearLayoutManager(requireContext())
            adapter.currentLayoutType = ViewSize.LONG
            viewLifecycleOwner.lifecycleScope.launchWhenCreated {
                viewModel.selectedTv.collectLatest {tv->
                    if (tv != null) {
                        adapter.updateItems(tv.createdBy)
                    }
                    counter = tv?.createdBy?.size
                }
            }
        }
    }

}