package com.indisparte.home


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.indisparte.home.adapter.SectionAdapter
import com.indisparte.home.databinding.FragmentHomeBinding
import com.indisparte.ui.fragment.BaseFragment
import com.indisparte.util.extension.collectIn
import dagger.hilt.android.AndroidEntryPoint

/**
 *@author Antonio Di Nuzzo (Indisparte)
 */
@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>() {

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentHomeBinding
        get() = FragmentHomeBinding::inflate

    private val viewModel: HomeViewModel by viewModels()
    private val sectionAdapter: SectionAdapter by lazy {
        SectionAdapter(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            recyclerViewSection.adapter = sectionAdapter
        }

        viewModel.sections.collectIn(viewLifecycleOwner) {
            sectionAdapter.submitSectionList(it)
        }

    }


}