package com.indisparte.movie_details.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.indisparte.movie_details.databinding.FragmentCollectionPartsBinding
import com.indisparte.network.util.whenResources
import com.indisparte.ui.adapter.MovieAdapter
import com.indisparte.ui.fragment.BaseFragment
import com.indisparte.util.extension.collectIn
import com.indisparte.util.extension.enableInnerScrollViewPager
import timber.log.Timber


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
        adapter.setOnItemClickListener { item ->
            viewModel.onDetailsFragmentReady(item.id)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel.collectionParts.collectIn(viewLifecycleOwner) { resource ->
            resource?.whenResources(
                onSuccess = { collectionDetails ->
                    binding.collectionParts.hideLoading()
                    binding.collection = collectionDetails
                    adapter.submitList(collectionDetails.parts)
                },
                onError = { error ->
//                    binding.collectionParts.showError(error)
                },
                onLoading = {
                    binding.collectionParts.showLoading()
                }
            )

        }
    }
}
