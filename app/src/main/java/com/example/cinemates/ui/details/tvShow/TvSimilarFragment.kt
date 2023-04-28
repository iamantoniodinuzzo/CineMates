package com.example.cinemates.ui.details.tvShow

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import com.example.cinemates.databinding.ListItemMediaSmallBinding
import com.example.cinemates.common.ListFragment
import com.example.cinemates.domain.model.Media
import com.example.cinemates.ui.adapter.MediaAdapter
import kotlinx.android.synthetic.main.fragment_profile.*

class TvSimilarFragment : ListFragment<Media, ListItemMediaSmallBinding, MediaAdapter>(MediaAdapter()) {

    private val viewModel: TvDetailsViewModel by activityViewModels()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewLifecycleOwner.lifecycleScope.launchWhenCreated {
            viewModel.similarTvShow.collect { similar ->
                adapter.items =similar
            }
        }

    }



}