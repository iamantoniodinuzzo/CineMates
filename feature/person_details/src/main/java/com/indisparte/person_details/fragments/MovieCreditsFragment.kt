package com.indisparte.person_details.fragments

import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.indisparte.navigation.NavigationFlow
import com.indisparte.navigation.ToFlowNavigable
import com.indisparte.network.whenResources
import com.indisparte.person_details.adapter.MovieCreditAdapter
import com.indisparte.person_details.databinding.ListItemMediaCreditsLongBinding
import com.indisparte.ui.fragment.ListFragment
import com.indisparte.util.extension.collectIn
import timber.log.Timber

/**
 *@author Antonio Di Nuzzo
 */
class MovieCreditsFragment :
    ListFragment<com.indisparte.movie_data.MovieCredit, ListItemMediaCreditsLongBinding, MovieCreditAdapter>(
        MovieCreditAdapter()
    ) {
    private val LOG = Timber.tag(MovieCreditsFragment::class.java.simpleName)
    private val viewModel: PersonDetailsViewModel by viewModels({ requireParentFragment() })

    override fun addItemsToTheAdapter() {
        adapter.setOnItemClickListener { movieCredit ->
            val activity = requireActivity()
            if (activity is ToFlowNavigable) {
                activity.navigateToFlow(NavigationFlow.MovieDetailsFlow(movieId = movieCredit.id))
            }
        }
        viewModel.movieCredits.collectIn(viewLifecycleOwner) { result ->
            result.whenResources(
                onSuccess = { list ->
                    LOG.d("Success!Movie credits: $list")
                    hideLoading()
                    adapter.submitList(list)
                },
                onError = { exception ->
                    hideLoading()
                    LOG.e("Error: $exception")


                },
                onLoading = {
                    showLoading()
                    LOG.d("Loading movie credits...")
                }
            )
        }
    }

    override fun initializeViews() {
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            setEmptyStateTitle("No movies")
        }
    }
}