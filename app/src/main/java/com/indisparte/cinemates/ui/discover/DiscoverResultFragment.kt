package com.indisparte.cinemates.ui.discover

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.indisparte.cinemates.databinding.FragmentDiscoverResultBinding
import com.indisparte.ui.fragment.BaseFragment


/**
 * @author Antonio Di Nuzzo (Indisparte)
 */
class DiscoverResultFragment : BaseFragment<FragmentDiscoverResultBinding>() {
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentDiscoverResultBinding
        get() = FragmentDiscoverResultBinding::inflate

    override fun initializeViews() {
        TODO("Not yet implemented")
    }

    private val viewModel: DiscoverViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    /* override fun onCreateView(
         inflater: LayoutInflater,
         container: ViewGroup?,
         savedInstanceState: Bundle?
     ): View {
         super.onCreateView(inflater, container, savedInstanceState)

         // Set up the navigation drawer
         val navController = findNavController()
         binding.navView.setupWithNavController(navController)

         return binding.root
     }*/

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            toolbar.apply {
                setOnMenuItemClickListener { item ->
                    when (item.itemId) {
                    }
                    false
                }
                setNavigationOnClickListener { _ ->
                    requireActivity().onBackPressed()
                }
            }

            recyclerView.layoutManager =
                GridLayoutManager(requireContext(), 3)

            lifecycleScope.launchWhenCreated {

            }


        }

    }

}