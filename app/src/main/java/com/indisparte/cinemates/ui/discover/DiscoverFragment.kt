package com.indisparte.cinemates.ui.discover

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.indisparte.cinemates.databinding.FragmentDiscoverBinding
import com.indisparte.ui.fragment.BaseFragment

class DiscoverFragment : BaseFragment<FragmentDiscoverBinding>() {

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentDiscoverBinding
        get() = FragmentDiscoverBinding::inflate

    private val viewModel: DiscoverViewModel by activityViewModels()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            searchButton.setOnClickListener { _ ->

            }
            customFilter.setOnClickListener {

            }

        }

    }


}