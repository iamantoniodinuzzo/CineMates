package com.indisparte.movie_details.fragments

import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.indisparte.model.entity.person.Cast
import com.indisparte.movie_details.adapter.CastAdapter
import com.indisparte.movie_details.databinding.ListItemCastLongBinding
import com.indisparte.navigation.NavigationFlow
import com.indisparte.navigation.ToFlowNavigable
import com.indisparte.network.whenResources
import com.indisparte.ui.fragment.ListFragment
import com.indisparte.util.extension.collectIn
import timber.log.Timber


/**
 * @author Antonio Di Nuzzo
 */
class MovieCastFragment : ListFragment<Cast, ListItemCastLongBinding, CastAdapter>(
    CastAdapter()
) {
    private val LOG = Timber.tag(MovieCastFragment::class.java.simpleName)
    private val viewModel: MovieDetailsViewModel by viewModels({ requireParentFragment() })

    override fun addItemsToTheAdapter() {
        adapter.setOnItemClickListener { actor ->
            val activity = requireActivity()
            if (activity is ToFlowNavigable) {
                activity.navigateToFlow(NavigationFlow.PersonDetailsFlow(actor.id))
            }

        }
        viewModel.cast.collectIn(viewLifecycleOwner) { resources ->
            resources?.whenResources(
                onSuccess = { cast ->
                    LOG.d("Cast loaded : ${cast.map { it.name }}")
                    hideLoading()
                    adapter.submitList(cast)
                },
                onError = { error ->
                    val errorMessage = error?.message
                    LOG.e("Error: $errorMessage")
                    hideLoading()
                    binding.recyclerView.setEmptyStateSubtitle(errorMessage)

                },
                onLoading = {
                    LOG.d("Loading cast...")
                    showLoading()
                })
        }
    }

    override fun initializeViews() {
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
    }

}