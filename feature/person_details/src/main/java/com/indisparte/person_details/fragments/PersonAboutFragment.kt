package com.indisparte.person_details.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.google.android.material.chip.Chip
import com.indisparte.network.whenResources
import com.indisparte.person_details.databinding.FragmentPersonAboutBinding
import com.indisparte.ui.databinding.LayoutChoiceChipBinding
import com.indisparte.ui.fragment.BaseFragment
import com.indisparte.util.extension.collectIn
import com.indisparte.util.extension.gone
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class PersonAboutFragment : BaseFragment<FragmentPersonAboutBinding>() {
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentPersonAboutBinding
        get() = FragmentPersonAboutBinding::inflate
    private val LOG = Timber.tag(PersonAboutFragment::class.java.simpleName)
    private val viewModel: PersonDetailsViewModel by viewModels({ requireParentFragment() })

    override fun initializeViews() {
        //init views here

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //use views here
        viewModel.personDetails.collectIn(viewLifecycleOwner) { result ->
            result.whenResources(
                onSuccess = {
                    binding.person = it
                    populateKnownAsChipGroup(it.alsoKnownAs)
                },
                onError = { exception ->
                    val errorMessage = requireContext().getString(exception.messageRes)
                    LOG.e("Error: $errorMessage ")
                },
                onLoading = {
                    LOG.d("Loading person details...")
                })
        }
    }

    private fun populateKnownAsChipGroup(alsoKnownAs: List<String>) {
        val chipGroup = binding.alsoKnownValues
        if (alsoKnownAs.isNotEmpty()) {
            chipGroup.removeAllViews()
            alsoKnownAs.forEach { otherName ->

                val chipBinding = LayoutChoiceChipBinding.inflate(layoutInflater)
                val chip: Chip = chipBinding.root

                chip.text = otherName

                chipGroup.addView(chip)
            }
        } else {
            chipGroup.gone()
            binding.alsoKnownTitle.gone()
        }
    }

}