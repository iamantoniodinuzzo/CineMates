package com.indisparte.saved.watchlist

import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.indisparte.base.Media
import com.indisparte.navigation.NavigationFlow
import com.indisparte.navigation.ToFlowNavigable
import com.indisparte.network.error.CineMatesExceptions
import com.indisparte.network.whenResources
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
class WatchlistFragment : ListFragment<Media, ListItemMediaSmallBinding, MediaAdapter>(
    MediaAdapter()
) {
    private val LOG = Timber.tag(WatchlistFragment::class.java.simpleName)
    private val viewModel: WatchListViewModel by viewModels()
    override fun addItemsToTheAdapter() {
        viewModel.watchlist.collectIn(viewLifecycleOwner) { resource ->
            resource.whenResources(
                onSuccess = { medias ->
                    LOG.d(
                        "Film watchlist : $medias"
                    )
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
        viewModel.getMovieWatchList()
        //init recyclerview
        binding.lifecycleOwner = this
        binding.recyclerView.apply {
            layoutManager = GridLayoutManager(requireContext(), 3)
            showError(CineMatesExceptions.EmptyResponse)
        }
        adapter.setOnItemClickListener { item ->
            LOG.d("Movie id: ${item.id}")
            val activity = requireActivity()
            if (activity is ToFlowNavigable) {
                activity.navigateToFlow(NavigationFlow.MovieDetailsFlow(item.id))
            }
        }

    }
}