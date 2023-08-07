package com.indisparte.movie_details.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.github.ajalt.timberkt.Timber
import com.indisparte.model.entity.Movie
import com.indisparte.movie_details.databinding.FragmentCollectionPartsBinding
import com.indisparte.movie_details.util.enableInnerScrollViewPager
import com.indisparte.network.whenResources
import com.indisparte.ui.adapter.MovieAdapter
import com.indisparte.ui.adapter.OnItemClickListener
import com.indisparte.ui.fragment.BaseFragment
import com.indisparte.util.extension.collectIn


/**
 * @author Antonio Di Nuzzo
 */
class CollectionPartsFragment : BaseFragment<FragmentCollectionPartsBinding>() {

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentCollectionPartsBinding
        get() = FragmentCollectionPartsBinding::inflate

    private val LOG = Timber.tag(CollectionPartsFragment::class.java.simpleName)
    private val viewModel: MovieDetailsViewModel by viewModels({ requireParentFragment() })
    private val adapter: MovieAdapter by lazy {
        MovieAdapter()
    }


    override fun initializeViews() {
        binding.collectionParts.enableInnerScrollViewPager()
        binding.collectionParts.adapter = adapter
        adapter.setOnItemClickListener(object :OnItemClickListener<Movie>{
            override fun onItemClick(item: Movie) {
                //update view model selected movie
                viewModel.onDetailsFragmentReady(item.id)
            }

        })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel.collectionParts.collectIn(viewLifecycleOwner) { resource ->
            resource?.whenResources(
                onSuccess = { collectionDetails ->
                    LOG.d("Collection details loaded! $collectionDetails")
                    binding.progress.hide()
                    binding.collection = collectionDetails
                    adapter.submitList(collectionDetails.parts)

                },
                onError = { error ->
                    val errorMessage = error?.message
                    LOG.e("Error: $errorMessage")
                    binding.progress.hide()
                },
                onLoading = {
                    LOG.d("Loading Collection Details...")
                    binding.progress.show()
                }
            )

        }
    }
}
