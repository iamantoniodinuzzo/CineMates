package com.indisparte.movie_details.fragments

import androidx.fragment.app.activityViewModels
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
 * @author Antonio Di Nuzzo (Indisparte)
 */
class MovieCastFragment : ListFragment<Cast, ListItemCastLongBinding, CastAdapter>(
    CastAdapter()
) {
    private val viewModel: MovieDetailsViewModel by activityViewModels()

    override fun addItemsToTheAdapter() {
        viewModel.cast.collectIn(viewLifecycleOwner) { resources ->
            resources?.whenResources(
                onSuccess = { cast ->
                    Timber.tag("MovieCastFragment").d("Cast loaded : ${cast?.map { it.name }}")
                    adapter.submitList(cast)
                },
                onError = { error ->
                    Timber.tag("MovieCastFragment").e("Error: ${error?.message}")

                },
                onLoading = {
                    Timber.tag("MovieCastFragment").d("Loading cast...")
                })
        }
    }

    override fun initializeViews() {
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
    }

}