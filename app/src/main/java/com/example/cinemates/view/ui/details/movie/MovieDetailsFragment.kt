package com.example.cinemates.view.ui.details.movie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AccelerateDecelerateInterpolator
import android.view.animation.AnticipateOvershootInterpolator
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.interpolator.view.animation.FastOutSlowInInterpolator
import androidx.navigation.fragment.navArgs
import com.example.cinemates.R
import com.example.cinemates.adapter.ViewPagerAdapter
import com.example.cinemates.databinding.FragmentMovieDetailsBinding
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
    private lateinit var movieInfoFragment: MovieInfoFragment
    private lateinit var movieCastFragment: MovieCastFragment
    private lateinit var movieImagesFragment: MovieImagesFragment
    private lateinit var viewPagerAdapter: ViewPagerAdapter
    private val viewModel: MovieDetailsViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        movieInfoFragment = MovieInfoFragment()
        movieCastFragment = MovieCastFragment()
        movieImagesFragment = MovieImagesFragment()
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


        binding.toolbar.setNavigationOnClickListener { requireActivity().onBackPressed() }
        viewModel.onDetailsFragmentReady(args.movie)

        viewModel.selectedMovie.observe(viewLifecycleOwner) { selectedMovie ->
            binding.movie = selectedMovie
        }
        initializeViewPager()

    }

    private fun initializeViewPager() {
        viewPagerAdapter = ViewPagerAdapter(childFragmentManager, lifecycle)
        viewPagerAdapter.addFragment(movieInfoFragment)
        viewPagerAdapter.addFragment(movieCastFragment)
        viewPagerAdapter.addFragment(movieImagesFragment)
        binding.apply {
            viewPager.adapter = viewPagerAdapter
            //viewPager.isUserInputEnabled = false
            TabLayoutMediator(tabLayout, viewPager) { tab, position ->
                when (position) {
                    0 -> tab.text = "Info"
                    1 -> tab.text = "Cast"
                    2 -> tab.text = "Images"
                }
            }.attach()
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        viewModel.onDestroyView()
    }

}