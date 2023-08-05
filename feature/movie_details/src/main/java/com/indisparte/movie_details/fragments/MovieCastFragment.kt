package com.indisparte.movie_details.fragments

import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.indisparte.model.entity.Cast
import com.indisparte.movie_details.adapter.CastAdapter
import com.indisparte.movie_details.databinding.ListItemCastLongBinding
import com.indisparte.movie_details.fragments.base.ListFragment
import com.indisparte.network.Resource
import com.indisparte.network.whenResources
import com.indisparte.util.extension.collectIn
import timber.log.Timber


/**
 * @author Antonio Di Nuzzo
 */
class MovieCastFragment : ListFragment<Cast, ListItemCastLongBinding, CastAdapter>(
    CastAdapter()
) {
    private val viewModel: MovieDetailsViewModel by viewModels({requireParentFragment()})

    override fun addItemsToTheAdapter() {
        viewModel.cast.collectIn(viewLifecycleOwner) { resources ->
            resources?.whenResources(
                onSuccess = { cast ->
                    Timber.tag("MovieCastFragment").d("Cast loaded : ${cast?.map { it.name }}")
                    hideLoading()
                    adapter.submitList(cast)
                },
                onError = { error ->
                    Timber.tag("MovieCastFragment").e("Error: ${error?.message}")
                    hideLoading()

                },
                onLoading = {
                    Timber.tag("MovieCastFragment").d("Loading cast...")
                    showLoading()
                })
        }
    }

    override fun initializeViews() {
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
    }

}