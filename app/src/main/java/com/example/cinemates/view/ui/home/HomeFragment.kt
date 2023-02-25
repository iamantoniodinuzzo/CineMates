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
import com.example.cinemates.model.Movie
import com.example.cinemates.model.Section
import com.example.cinemates.model.SectionMovie
import com.example.cinemates.view.ui.adapter.MovieAdapter
import com.example.cinemates.view.ui.adapter.SectionAdapter
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
    private lateinit var sectionMoviePopular: SectionMovie
    private lateinit var sectionMovieTopRated: SectionMovie
    private lateinit var sectionMovieUpcoming: SectionMovie
    private var sections: MutableList<Section<*>> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sectionMoviePopular = SectionMovie("Movies popular", listOf())
        sectionMovieTopRated = SectionMovie("Movies Top rated", mutableListOf())
        sectionMovieUpcoming = SectionMovie("Movies Upcoming", mutableListOf())
        sections = mutableListOf(sectionMoviePopular, sectionMovieUpcoming, sectionMovieTopRated)
    }

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


            val adapter = SectionAdapter(sections)
            sectionRv.adapter = adapter
            viewModel.popularMovies.observe(requireActivity()) { popular ->
                sectionMoviePopular.items = popular
                adapter.notifyDataSetChanged()
            }
            viewModel.topRatedMovies.observe(requireActivity()) { topRated ->
                sectionMovieTopRated.items = topRated
                adapter.notifyDataSetChanged()
            }
            viewModel.upcomingMovies.observe(requireActivity()) { upcoming ->
                sectionMovieUpcoming.items = upcoming
                adapter.notifyDataSetChanged()
            }


        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}