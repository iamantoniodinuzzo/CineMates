package com.example.cinemates.ui.details.actor

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.example.cinemates.common.BaseFragment
import com.example.cinemates.databinding.FragmentActorDetailsBinding
import com.example.cinemates.ui.adapter.ViewPagerAdapter
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class ActorDetailsFragment : BaseFragment<FragmentActorDetailsBinding>() {

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentActorDetailsBinding
        get() = FragmentActorDetailsBinding::inflate

    private lateinit var viewPagerAdapter: ViewPagerAdapter
    private lateinit var actorAboutFragment: ActorAboutFragment


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        actorAboutFragment = ActorAboutFragment()
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeViewPager()

        binding.apply {
            customizeFab(fab)
            fab.setOnClickListener {
                updateThisPerson(fab)
            }
            toolbar.setNavigationOnClickListener { view ->
                findNavController(view).navigateUp()
            }

        }

        lifecycleScope.launchWhenCreated {

        }

    }

    private fun initializeViewPager() {
        viewPagerAdapter = ViewPagerAdapter(childFragmentManager, lifecycle)
        viewPagerAdapter.addFragment(actorAboutFragment)
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
