package com.indisparte.person_details.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.android.material.tabs.TabLayoutMediator
import com.indisparte.network.whenResources
import com.indisparte.person_details.R
import com.indisparte.person_details.databinding.FragmentPersonDetailsContainerBinding
import com.indisparte.ui.adapter.ViewPagerAdapter
import com.indisparte.ui.fragment.BaseFragment
import com.indisparte.util.extension.collectIn
import com.indisparte.util.extension.gone
import com.indisparte.util.extension.visible
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber
import kotlin.math.abs

typealias FragmentTitleMap = LinkedHashMap<Fragment, @receiver:StringRes Int>


@AndroidEntryPoint
class PersonDetailsContainerFragment : BaseFragment<FragmentPersonDetailsContainerBinding>() {
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentPersonDetailsContainerBinding
        get() = FragmentPersonDetailsContainerBinding::inflate
    private val LOG = Timber.tag(PersonDetailsContainerFragment::class.java.simpleName)
    private val args: PersonDetailsContainerFragmentArgs by navArgs()
    private val viewModel: PersonDetailsViewModel by viewModels()
    private lateinit var viewPagerAdapter: ViewPagerAdapter
    private val mapOfFragments: FragmentTitleMap =
        linkedMapOf(
            PersonAboutFragment() to R.string.fragment_person_about_title,
            MovieCreditsFragment() to R.string.fragment_movie_title
        )

    override fun initializeViews() {
        //init views here
        viewModel.getPersonDetailsAndCredits(args.id)

        initViewPager()

        binding.apply {
            toolbar.setNavigationOnClickListener { requireActivity().onBackPressed() }
            fab.setOnClickListener {
                Toast.makeText(requireContext(), "Soon", Toast.LENGTH_SHORT).show()
            }

            appBarLayout.addOnOffsetChangedListener { appBarLayout, verticalOffset ->
                fab.isVisible =
                    verticalOffset == 0 || abs(verticalOffset) >= appBarLayout.totalScrollRange
            }

        }

    }

    private fun showLoading() {
        binding.loadingProgressBar.visible()
        binding.content.gone()
    }

    private fun hideLoading() {
        binding.loadingProgressBar.gone()
        binding.content.visible()
    }

    private fun initViewPager() {
        binding.apply {
            viewPagerAdapter = ViewPagerAdapter(childFragmentManager, lifecycle)

            mapOfFragments.forEach { (fragment, titleRes) ->
                val title = requireContext().getString(titleRes)
                viewPagerAdapter.addFragment(fragment, title)
            }

            viewPager.adapter = viewPagerAdapter

            TabLayoutMediator(tabLayout, viewPager) { tab, position ->
                tab.text = viewPagerAdapter.getPageTitle(position)
            }.attach()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //use views here
        viewModel.personDetails.collectIn(viewLifecycleOwner) { result ->
            result.whenResources(
                onSuccess = {personDetails->
                    hideLoading()
                    LOG.d("Person Gender: ${personDetails.formattedGender}")
                    binding.person = personDetails
                },
                onError = { exception ->
                    hideLoading()
                    val errorMessage = requireContext().getString(exception.messageRes)
                    LOG.e("Error: $errorMessage")
                    showToastMessage(errorMessage)
                    findNavController().navigateUp()
                },
                onLoading = {
                    showLoading()
                    LOG.d("Loading person details...")
                }
            )

        }
    }

}