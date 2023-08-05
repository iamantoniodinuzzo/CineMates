package com.indisparte.movie_details.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.viewpager2.widget.ViewPager2
import com.github.ajalt.timberkt.Timber
import com.indisparte.movie_details.adapter.BackdropAdapter
import com.indisparte.movie_details.fragments.base.MediaDetailsContainerFragment
import com.indisparte.navigation.NavigationFlow
import com.indisparte.navigation.ToFlowNavigable
import com.indisparte.network.whenResources
import com.indisparte.ui.R
import com.indisparte.util.extension.collectIn
import com.indisparte.util.extension.gone
import com.indisparte.util.extension.visible
import dagger.hilt.android.AndroidEntryPoint

/**
 * @author Antonio Di Nuzzo
 * @author Jon Areas
 */
@AndroidEntryPoint
class MovieDetailsContainerFragment : MediaDetailsContainerFragment(
    linkedMapOf(
        MovieAboutFragment() to R.string.fragment_media_about,
        MovieCastFragment() to R.string.fragment_media_cast,
        MovieSimilarFragment() to R.string.fragment_media_similar,
    )
) {
    private val LOG = Timber.tag(MovieDetailsContainerFragment::class.java.simpleName)
    private val viewModel: MovieDetailsViewModel by viewModels()
    private val movieIdArgs: MovieDetailsContainerFragmentArgs by navArgs()
    private lateinit var backdropAdapter: BackdropAdapter
    private lateinit var backdropViewPager: ViewPager2
    private val collectionPartsFragment: CollectionPartsFragment by lazy {
        CollectionPartsFragment()
    }

    override fun initializeViews() {
        backdropAdapter = BackdropAdapter()
        backdropAdapter.registerAdapterDataObserver(binding.circleIndicator.adapterDataObserver)
        viewModel.onDetailsFragmentReady(movieIdArgs.id)
        backdropViewPager = binding.backdropViewPager
        backdropViewPager.adapter = backdropAdapter
        binding.circleIndicator.setViewPager(backdropViewPager)

        binding.toolbar.setNavigationOnClickListener {
            ((this.requireActivity()) as ToFlowNavigable).navigateToFlow(NavigationFlow.HomeFlow)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fetchSelectedMovieDetails()
        fetchLatestCertification()
        fetchMovieBackdrops()

    }

    private fun fetchMovieBackdrops() {
        viewModel.backdrops.collectIn(viewLifecycleOwner) { resources ->
            resources?.whenResources(
                onSuccess = { backdrops ->
                    backdropAdapter.submitList(backdrops)
                },
                onError = {
                    val errorMessage = it?.message
                    LOG.e("Error: $errorMessage")
                    backdropViewPager.setBackgroundResource(R.drawable.ic_broken_image)
                },
                onLoading = {
                    LOG.d("Loading Backdrops...")
                }
            )

        }
    }

    private fun fetchLatestCertification() {
        viewModel.latestCertification.collectIn(viewLifecycleOwner) { latestCertification ->
            binding.certification.apply {
                text = latestCertification
                if (!latestCertification.isNullOrEmpty()) visible() else gone()
            }

        }
    }

    private fun fetchSelectedMovieDetails() {
        viewModel.selectedMovie.collectIn(viewLifecycleOwner) { resources ->
            resources.whenResources(
                onSuccess = { movieDetails ->
                    LOG.d("Movie details loaded: ${movieDetails.toString()}")
                    binding.media = movieDetails
                    //check if movie is a part of collection
                    if (movieDetails?.belongsToCollection != null) {
                        LOG.d("Movie is a part of collection, add CollectionFragment.")
                        addFragment(collectionPartsFragment, R.string.fragment_collection)
                    } else {
                        LOG.d("Movie is not a part of collection, remove CollectionFragment if present")
                        removeFragment(collectionPartsFragment)
                    }
                },
                onError = { error ->
                    val errorMessage = error?.message
                    LOG.d("Error: $errorMessage)")
                    showToastMessage("$errorMessage")
                    findNavController().navigateUp()
                },
                onLoading = {
                    LOG.d("Movie details loading...")
                })
        }
    }


}