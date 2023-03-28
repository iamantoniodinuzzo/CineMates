package com.example.cinemates.ui.details.tvShow

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.interpolator.view.animation.FastOutSlowInInterpolator
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.example.cinemates.R
import com.example.cinemates.databinding.FragmentTvDetailsBinding
import com.example.cinemates.ui.adapter.ViewPagerAdapter
import com.google.android.material.tabs.TabLayoutMediator
import com.google.android.material.transition.MaterialSharedAxis
import kotlinx.coroutines.flow.collectLatest

private val TAG = TvDetailsFragment::class.simpleName

/**
 * @author Antonio Di Nuzzo
 * @author Jon Areas
 * Created 26/05/2022 at 15:44
 */
class TvDetailsFragment : Fragment() {

    private var _binding: FragmentTvDetailsBinding? = null
    private val binding: FragmentTvDetailsBinding
        get() = _binding!!
    private val args: TvDetailsFragmentArgs by navArgs()
    private lateinit var tvAboutFragment: TvAboutFragment
    private lateinit var tvCastFragment: TvCastFragment
    private lateinit var tvCrewFragment: TvCrewFragment
    private lateinit var tvSimilarFragment: TvSimilarFragment
    private lateinit var tvCreatedByFragment: TvCreatedByFragment
    private lateinit var viewPagerAdapter: ViewPagerAdapter
    private val viewModel: TvDetailsViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        tvAboutFragment = TvAboutFragment()
        tvCastFragment = TvCastFragment()
        tvCrewFragment = TvCrewFragment()
        tvSimilarFragment = TvSimilarFragment()
        tvCreatedByFragment = TvCreatedByFragment()

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
        _binding = FragmentTvDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.onDetailsFragmentReady(args.tv.id)

        viewLifecycleOwner.lifecycleScope.launchWhenCreated {
            viewModel.selectedTv.collectLatest { selectedTv ->
                binding.tv = selectedTv
            }
        }

        binding.apply {
            toolbar.setNavigationOnClickListener { requireActivity().onBackPressed() }
            fab.setOnClickListener {
                //Open bottomSheetFragment
                Toast.makeText(requireContext(), "Soon", Toast.LENGTH_SHORT).show()
            }
            watchProviders.setOnClickListener { _ ->
                Toast.makeText(requireContext(), "Soon", Toast.LENGTH_SHORT).show()
            }


            initializeViewPager()
        }


    }

    private fun initializeViewPager() {
        viewPagerAdapter = ViewPagerAdapter(childFragmentManager, lifecycle)
        viewPagerAdapter.addFragment(tvAboutFragment)
        viewPagerAdapter.addFragment(tvCastFragment)
        viewPagerAdapter.addFragment(tvCrewFragment)
        viewPagerAdapter.addFragment(tvSimilarFragment)
        viewPagerAdapter.addFragment(tvCreatedByFragment)
        binding.apply {
            viewPager.adapter = viewPagerAdapter
            //viewPager.isUserInputEnabled = false
            TabLayoutMediator(tabLayout, viewPager) { tab, position ->
                when (position) {
                    0 -> tab.text = "Info"
                    1 -> tab.text = "Cast"
                    2 -> tab.text = "Crew"
                    3 -> tab.text = "Similar"
                    4 -> tab.text = "Created by"
                }
            }.attach()
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}