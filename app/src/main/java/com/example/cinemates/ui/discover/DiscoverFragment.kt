package com.example.cinemates.ui.discover

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.example.cinemates.R
import com.example.cinemates.common.BaseFragment
import com.example.cinemates.databinding.FragmentDiscoverBinding

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