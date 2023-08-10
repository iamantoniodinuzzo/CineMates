package com.indisparte.media_discover


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.indisparte.media_discover.databinding.FragmentDiscoverBinding
import com.indisparte.navigation.NavigationFlow
import com.indisparte.navigation.ToFlowNavigable
import com.indisparte.ui.fragment.BaseFragment

/**
 *@author Antonio Di Nuzzo
 */
class DiscoverFragment : BaseFragment<FragmentDiscoverBinding>() {
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentDiscoverBinding
        get() = FragmentDiscoverBinding::inflate

    override fun initializeViews() {
        //todo
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            searchButton.setOnClickListener {
                val activity = requireActivity()
                if (activity is ToFlowNavigable) {
                    activity.navigateToFlow(NavigationFlow.SearchFlow)
                }
            }

            filtersButton.setOnClickListener {
                //todo navigate to filterable view
            }
        }
    }

}