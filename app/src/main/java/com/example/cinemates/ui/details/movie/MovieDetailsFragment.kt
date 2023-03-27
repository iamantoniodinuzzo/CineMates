package com.example.cinemates.ui.details.movie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.interpolator.view.animation.FastOutSlowInInterpolator
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.cinemates.R
import com.example.cinemates.ui.adapter.ViewPagerAdapter
import com.example.cinemates.databinding.FragmentMovieDetailsBinding
import com.example.cinemates.model.Movie
import com.google.android.material.tabs.TabLayoutMediator
import com.google.android.material.transition.MaterialSharedAxis

/**
 * @author Antonio Di Nuzzo
 * @author Jon Areas
 * Created 26/05/2022 at 15:44
 */
class MovieDetailsFragment : Fragment() {

    private var _binding: FragmentMovieDetailsBinding? = null
    private val binding: FragmentMovieDetailsBinding
        get() = _binding!!
    private val args: MovieDetailsFragmentArgs by navArgs()
    private lateinit var movieAboutFragment: MovieAboutFragment
    private lateinit var movieCastFragment: MovieCastFragment
    private lateinit var movieCrewFragment: MovieCrewFragment
    private lateinit var movieSimilarFragment: MovieSimilarFragment
    private lateinit var movieRecommendedFragment: MovieRecommendedFragment
    private lateinit var viewPagerAdapter: ViewPagerAdapter
    private val viewModel: MovieDetailsViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        movieAboutFragment = MovieAboutFragment()
        movieCastFragment = MovieCastFragment()
        movieCrewFragment = MovieCrewFragment()
        movieSimilarFragment = MovieSimilarFragment()
        movieRecommendedFragment = MovieRecommendedFragment()

        enterTransition = MaterialSharedAxis(MaterialSharedAxis.Z, true).apply {
            interpolator = FastOutSlowInInterpolator()
            duration = resources.getInteger(R.integer.material_motion_duration_long_1).toLong()
        }
        exitTransition = MaterialSharedAxis(MaterialSharedAxis.Z, false).apply {
            interpolator = FastOutSlowInInterpolator()
            duration = resources.getInteger(R.integer.material_motion_duration_long_1).toLong()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentMovieDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val selectedMovie: Movie = args.movie

        binding.apply {
            movie = selectedMovie
            toolbar.setNavigationOnClickListener { requireActivity().onBackPressed() }
            fab.setOnClickListener {
                //Open bottomSheetFragment
                findNavController().navigate(R.id.action_movieDetailsFragment_to_bottomSheetFragment)
            }
            watchProviders.setOnClickListener { _ ->
                Toast.makeText(requireContext(), "Soon", Toast.LENGTH_SHORT).show()
            }
            viewModel.onDetailsFragmentReady(selectedMovie.id)
            initializeViewPager()
        }



    }

    private fun initializeViewPager() {
        viewPagerAdapter = ViewPagerAdapter(childFragmentManager, lifecycle)
        viewPagerAdapter.addFragment(movieAboutFragment)
        viewPagerAdapter.addFragment(movieCastFragment)
        viewPagerAdapter.addFragment(movieCrewFragment)
        viewPagerAdapter.addFragment(movieSimilarFragment)
        viewPagerAdapter.addFragment(movieRecommendedFragment)
        binding.apply {
            viewPager.adapter = viewPagerAdapter
            //viewPager.isUserInputEnabled = false
            TabLayoutMediator(tabLayout, viewPager) { tab, position ->
                when (position) {
                    0 -> tab.text = "Info"
                    1 -> tab.text = "Cast"
                    2 -> tab.text = "Crew"
                    3 -> tab.text = "Similar"
                    4 -> tab.text = "Recommendations"
                }
            }.attach()
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}