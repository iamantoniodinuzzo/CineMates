package com.indisparte.saved

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.indisparte.network.whenResources
import com.indisparte.saved.databinding.FragmentSavedBinding
import com.indisparte.ui.fragment.BaseFragment
import com.indisparte.util.extension.collectIn
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

/**
 *@author Antonio Di Nuzzo
 */
@AndroidEntryPoint
class SavedFragment : BaseFragment<FragmentSavedBinding>() {
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentSavedBinding
        get() = FragmentSavedBinding::inflate
    private val LOG = Timber.tag(SavedFragment::class.java.simpleName)
    private val viewModel: SavedViewModel by viewModels()

    override fun initializeViews() {
        //init views here
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //use views here

        viewModel.history.collectIn(viewLifecycleOwner) { resource ->
            resource.whenResources(
                onSuccess = { mediaList ->
                    LOG.d("Media: $mediaList")

                },
                onError = {
                    LOG.e(it)
                },
                onLoading = {
                    LOG.i("Caricando la lista dei visti")
                }
            )

        }

        viewModel.watchlist.collectIn(viewLifecycleOwner) { resource ->
            resource.whenResources(
                onSuccess = {mediaList->
                    LOG.d("Media: $mediaList")

                },
                onError = {
                    LOG.e(it)

                },
                onLoading = {
                    LOG.i("Caricando la lista dei da vedere")

                }
            )
        }

    }

}