package com.indisparte.home


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.indisparte.home.adapter.SectionAdapter
import com.indisparte.home.databinding.FragmentHomeBinding
import com.indisparte.navigation.NavigationFlow
import com.indisparte.navigation.ToFlowNavigable
import com.indisparte.ui.fragment.BaseFragment
import com.indisparte.util.extension.collectIn
import dagger.hilt.android.AndroidEntryPoint

/**
 *@author Antonio Di Nuzzo
 */
@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>() {

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentHomeBinding
        get() = FragmentHomeBinding::inflate

    private val viewModel: HomeViewModel by activityViewModels()

    private val sectionAdapter: SectionAdapter by lazy {
        SectionAdapter(this)
    }

    override fun initializeViews() {
        binding.apply {
            recyclerViewSection.adapter = sectionAdapter

            toolbar.setOnMenuItemClickListener { item ->
                when (item.itemId) {
                    R.id.search -> {
                        val activity = requireActivity()
                        if (activity is ToFlowNavigable) {
                            activity.navigateToFlow(NavigationFlow.SearchFlow)
                        }
                        true
                    }

                    else -> {
                        false
                    }
                }
            }
        }
    }



override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    viewModel.sections.collectIn(viewLifecycleOwner) {
        sectionAdapter.submitSectionList(it.toList())
    }

}


}