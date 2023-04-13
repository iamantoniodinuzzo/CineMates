package com.example.cinemates.ui.details.actor

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnticipateOvershootInterpolator
import androidx.core.view.doOnPreDraw
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.interpolator.view.animation.FastOutSlowInInterpolator
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.example.cinemates.R
import com.example.cinemates.common.BaseFragment
import com.example.cinemates.ui.adapter.ViewPagerAdapter
import com.example.cinemates.databinding.FragmentActorDetailsBinding
import com.example.cinemates.util.getLong
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.tabs.TabLayoutMediator
import com.google.android.material.transition.MaterialFadeThrough
import com.google.android.material.transition.MaterialSharedAxis

class ActorDetailsFragment : BaseFragment<FragmentActorDetailsBinding>() {

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentActorDetailsBinding
        get() = FragmentActorDetailsBinding::inflate

    private val viewModel: ActorDetailsViewModel by activityViewModels()
    private val args: ActorDetailsFragmentArgs by navArgs()
    private lateinit var viewPagerAdapter: ViewPagerAdapter
    private lateinit var actorAboutFragment: ActorAboutFragment
    private lateinit var actorMoviesFragment: ActorMoviesFragment


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        actorAboutFragment = ActorAboutFragment()
        actorMoviesFragment = ActorMoviesFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {

        initializeViewPager()
        return binding.root
    }

    private fun initializeViewPager() {
        viewPagerAdapter = ViewPagerAdapter(childFragmentManager, lifecycle)
        viewPagerAdapter.addFragment(actorAboutFragment)
        viewPagerAdapter.addFragment(actorMoviesFragment)
        binding.apply {
            viewPager.adapter = viewPagerAdapter
            TabLayoutMediator(tabLayout, viewPager) { tab, position ->
                //TODO update name of tab with StringRes
                when (position) {
                    0 -> tab.text = "About"
                    1 -> tab.text = "Movies"
                }
            }.attach()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        postponeEnterTransition()
        view.doOnPreDraw { startPostponedEnterTransition() }
        viewModel.onDetailsFragmentReady(args.person.id)

        binding.apply {
            customizeFab(fab)
            fab.setOnClickListener {
                updateThisPerson(fab)
            }
            toolbar.setNavigationOnClickListener { view ->
                findNavController(view).navigateUp()
            }
            person = args.person

        }

    }

    private fun updateThisPerson(fab: FloatingActionButton) {
       /* if (dbViewModel.setAsFavorite(args.person)) {
            Toast.makeText(context, "Added to favorites", Toast.LENGTH_SHORT).show()
            fab.setImageResource(R.drawable.ic_favorite_filled)
        } else {
            Toast.makeText(context, "Removed from favorites", Toast.LENGTH_SHORT).show()
            fab.setImageResource(R.drawable.ic_favorite_border)
        }*/
    }

    private fun customizeFab(fab: FloatingActionButton) {
      /*  if (dbViewModel.isMyFavoritePerson(args.person)) {
            fab.setImageResource(R.drawable.ic_favorite_filled)
        } else {
            fab.setImageResource(R.drawable.ic_favorite_border)
        }*/

    }

}
