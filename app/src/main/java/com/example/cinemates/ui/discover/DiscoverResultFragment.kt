package com.example.cinemates.ui.discover

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cinemates.R
import com.example.cinemates.common.BaseFragment
import com.example.cinemates.databinding.FragmentDiscoverResultBinding
import com.example.cinemates.ui.adapter.MediaAdapter
import kotlinx.coroutines.flow.collectLatest


/**
 * @author Antonio Di Nuzzo (Indisparte)
 */
class DiscoverResultFragment : BaseFragment<FragmentDiscoverResultBinding>() {
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentDiscoverResultBinding
        get() = FragmentDiscoverResultBinding::inflate

    private val viewModel: DiscoverViewModel by activityViewModels()
    private lateinit var mediaAdapter: MediaAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mediaAdapter = MediaAdapter()
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
                        R.id.menu_filters -> findNavController().navigate(R.id.action_discoverResultFragment_to_filterDialogFragment)
                    }
                    false
                }
                setNavigationOnClickListener { _ ->
                    requireActivity().onBackPressed()
                }
            }

            recyclerView.adapter = mediaAdapter
            recyclerView.layoutManager =
                GridLayoutManager(requireContext(),3)

            lifecycleScope.launchWhenCreated {
                viewModel.discoverableMedia.collectLatest {
                    mediaAdapter.items = it
                }
            }


        }

    }

}