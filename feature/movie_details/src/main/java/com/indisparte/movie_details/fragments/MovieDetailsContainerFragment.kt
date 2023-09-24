package com.indisparte.movie_details.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.viewpager2.widget.ViewPager2
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
import timber.log.Timber

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

        // Assicurati di impostare il lifecycle owner del binding
        binding.lifecycleOwner = this
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fetchMovieInfo()
    }

    private fun fetchMovieInfo() {
        viewModel.movieInfo.collectIn(viewLifecycleOwner) { resources ->
            resources?.whenResources(
                onSuccess = {
                    LOG.d("Loaded all details")
//                    Load movie details
                    binding.media = it.movieDetails
                    //Set the title here, without using data binding, because the 'app:title' attribute of the toolbar does not allow dynamic change
                    binding.toolbar.title = it.movieDetails.title
                    //check if movie is a part of collection
                    if (it.movieDetails.belongsToCollection != null) {
                        LOG.d("Movie is a part of collection, add CollectionFragment.")
                        addFragment(collectionPartsFragment, R.string.fragment_collection)
                    } else {
                        LOG.d("Movie is not a part of collection, remove CollectionFragment if present")
                        removeFragment(collectionPartsFragment)
                    }
//                    Load backdrops
                    backdropAdapter.submitList(it.backdrops)
//                    Load certification
                    binding.certification.apply {
                        text = it.latestCertification
                        if (!it.latestCertification.isNullOrEmpty()) visible() else gone()
                    }
                    hideLoading()

                },
                onError = { exception ->
                    val errorMessage = requireContext().getString(exception.messageRes)
                    LOG.e("An error occurred $errorMessage")
                    showToastMessage(errorMessage)//TODO maybe a dialog
                    findNavController().navigateUp()
                },
                onLoading = {
                    LOG.d("Loading movie info...")
                    showLoading()
                }
            )

        }
    }


}