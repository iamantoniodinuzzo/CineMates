package com.example.cinemates.view.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.cinemates.databinding.FragmentHomeBinding
import com.example.cinemates.view.ui.adapter.MovieAdapter
import com.google.android.material.chip.Chip
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

/**
 *@author Antonio Di Nuzzo (Indisparte)
 */
@AndroidEntryPoint
class HomeFragment : Fragment() {
    private val TAG = HomeFragment::class.simpleName
    private var _binding: FragmentHomeBinding? = null
    private val binding: FragmentHomeBinding
        get() = _binding!!
    private val viewModel: HomeViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {

            initChipGroupSpecificMovieList()

            val adapter = MovieAdapter()
            sectionRv.adapter = adapter
            viewLifecycleOwner.lifecycleScope.launch {
                viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                    viewModel.movieListBySpecification.collectLatest {
                        Log.d(TAG, "onViewCreated: received list")
                        adapter.addItems(it)
                    }
                }

            }
        }
    }

    private fun FragmentHomeBinding.initChipGroupSpecificMovieList() {
        val movieListSpecifications = HomeViewModel.Companion.MovieListSpecification.values()
        chipGroup.removeAllViews()
        for (specification in movieListSpecifications) {
            val chip = Chip(context)
            chip.isCheckable = true
            chip.id = specification.ordinal
            chip.text = getString(specification.nameResource)
            chip.setOnCheckedChangeListener { _, isChecked ->
                if (isChecked)
                    viewModel.setMovieListSpecification(specification)
            }
            chipGroup.addView(chip)
        }
        val firstIndex = (movieListSpecifications.lastIndex - movieListSpecifications.size) + 1
        chipGroup.check(firstIndex)

    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}