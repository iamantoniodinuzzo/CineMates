package com.indisparte.saved.history

import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.indisparte.base.Media
import com.indisparte.navigation.NavigationFlow
import com.indisparte.navigation.ToFlowNavigable
import com.indisparte.network.exception.CineMatesException
import com.indisparte.network.util.whenResources
import com.indisparte.ui.adapter.MediaAdapter
import com.indisparte.ui.custom_view.showError
import com.indisparte.ui.databinding.ListItemMediaSmallBinding
import com.indisparte.ui.fragment.ListFragment
import com.indisparte.util.extension.collectIn
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

/**
 *@author Antonio Di Nuzzo
 */

@AndroidEntryPoint
class HistoryFragment : ListFragment<Media, ListItemMediaSmallBinding, MediaAdapter>(
    MediaAdapter()
) {
    private val LOG = Timber.tag(HistoryFragment::class.java.simpleName)
    private val viewModel: HistoryViewModel by viewModels()
    override fun addItemsToTheAdapter() {
        viewModel.history.collectIn(viewLifecycleOwner) { resource ->
            resource.whenResources(
                onSuccess = { medias ->
                    hideLoading()
                    adapter.submitList(medias)

                },
                onError = {
                    showError(it)
                },
                onLoading = {
                    showLoading()
                }

            )

        }
    }

    override fun initializeViews() {
        viewModel.getMovieHistory()
        //init recyclerview
        binding.lifecycleOwner = this
        binding.recyclerView.apply {
            layoutManager = GridLayoutManager(requireContext(), 3)
            showError(CineMatesException.EmptyResponse)
        }
        adapter.setOnItemClickListener { item ->
            val activity = requireActivity()
            if (activity is ToFlowNavigable) {
                activity.navigateToFlow(NavigationFlow.MovieDetailsFlow(item.id))
            }
        }

    }
}