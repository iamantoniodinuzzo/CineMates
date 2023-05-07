package com.example.cinemates.ui.discover

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.core.view.GravityCompat
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.cinemates.R
import com.example.cinemates.common.BaseFragment
import com.example.cinemates.databinding.FragmentDiscoverResultBinding


/**
 * @author Antonio Di Nuzzo (Indisparte)
 */
class DiscoverResultFragment : BaseFragment<FragmentDiscoverResultBinding>() {
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentDiscoverResultBinding
        get() = FragmentDiscoverResultBinding::inflate

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)

        // Set up the navigation drawer
        val navController = findNavController()
        binding.navView.setupWithNavController(navController)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            toolbar.apply {
                setOnMenuItemClickListener { item ->
                    when (item.itemId) {
                        R.id.menu_filters -> drawerLayout.openDrawer(GravityCompat.END)
                    }
                    false
                }
                setNavigationOnClickListener { _ ->
                    requireActivity().onBackPressed()
                }
            }
        }

    }

}