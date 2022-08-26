package com.example.cinemates.view.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.interpolator.view.animation.FastOutSlowInInterpolator
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI.setupWithNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.cinemates.R
import com.example.cinemates.adapter.SectionRecyclerViewAdapter
import com.example.cinemates.databinding.FragmentHomeBinding
import com.example.cinemates.model.data.Movie
import com.example.cinemates.model.data.Person
import com.example.cinemates.model.data.Section
import com.example.cinemates.util.ItemMoveCallback
import com.example.cinemates.util.ViewSize
import com.example.cinemates.view.ui.MainActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.transition.MaterialElevationScale

class HomeFragment : Fragment() {
    private lateinit var mBinding: FragmentHomeBinding
    private lateinit var upcomingSection: Section<Movie>
    private lateinit var topRatedSection: Section<Movie>
    private lateinit var trendingSection: Section<Movie>
    private lateinit var trendingPerson: Section<Person>
    private val mViewModel: HomeViewModel by activityViewModels()
    private lateinit var mAdapter: SectionRecyclerViewAdapter
    private lateinit var mSectionList: MutableList<Section<*>> //TODO to add to viewModel
    private lateinit var mBottomNavigationView: BottomNavigationView
    private lateinit var slideIn: Animation
    private lateinit var slideOut: Animation


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mAdapter = SectionRecyclerViewAdapter(this, context)
        upcomingSection = Section("Upcoming", null, Movie::class.java, null, ViewSize.SMALL)
        topRatedSection = Section("Top Rated", null, Movie::class.java, null, ViewSize.SMALL)
        trendingSection =
            Section("Trending this week", "Movies", Movie::class.java, null, ViewSize.SMALL)
        trendingPerson =
            Section("Trending this week", "Actors", Person::class.java, null, ViewSize.SMALL)
        mSectionList = ArrayList()
        mSectionList.add(upcomingSection)
        mSectionList.add(topRatedSection)
        mSectionList.add(trendingSection)
        mSectionList.add(trendingPerson)
        slideIn = AnimationUtils.loadAnimation(context, R.anim.slide_in)
        slideOut = AnimationUtils.loadAnimation(context, R.anim.slide_out)

        setupMotionAnimations()
    }

    private fun setupMotionAnimations() {
        val elevationScaleTransition: Any = MaterialElevationScale(true)
            .setInterpolator(FastOutSlowInInterpolator())
        enterTransition = elevationScaleTransition
        reenterTransition = elevationScaleTransition
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mBinding = FragmentHomeBinding.inflate(inflater, container, false)

        val appBarConfiguration = AppBarConfiguration(findNavController().graph)
        setupWithNavController(mBinding.toolbar, findNavController(), appBarConfiguration)


        return mBinding.root
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        mBottomNavigationView = (requireActivity() as MainActivity).binding.bottomNavigationView
        postponeEnterTransition()
        (view.parent as ViewGroup)
            .viewTreeObserver
            .addOnPreDrawListener {
                startPostponedEnterTransition()
                true
            }
        val callback: ItemTouchHelper.Callback = ItemMoveCallback(mAdapter)
        val touchHelper = ItemTouchHelper(callback)
        touchHelper.attachToRecyclerView(mBinding.sectionRv)

        mBinding.apply {

            imageProfile.setOnClickListener {
                // TODO: Open Drawer
                Toast.makeText(context, "Soon", Toast.LENGTH_SHORT).show()
            }

            toolbar.setOnMenuItemClickListener {
                if (it.itemId == R.id.searchFragment)
                    findNavController().navigate(R.id.action_homeFragment_to_searchFragment)
                true
            }

            sectionRv.adapter = mAdapter
            //Hides bottomBar when recyclerview is scrolled
            sectionRv.addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                    super.onScrollStateChanged(recyclerView, newState)
                }

                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)
                    if (dy > 0 && mBottomNavigationView.isShown) {
                        mBottomNavigationView.startAnimation(slideOut)
                        mBottomNavigationView.visibility = View.INVISIBLE
                    } else if (dy < 0) {
                        mBottomNavigationView.startAnimation(slideIn)
                        mBottomNavigationView.visibility = View.VISIBLE
                    }
                }
            })
        }

        initSectionedRecyclerView()

    }

    private fun initSectionedRecyclerView() {
        mAdapter.addItems(mSectionList)
        upcomingSection.liveData = mViewModel.upcomingMovies
        topRatedSection.liveData = mViewModel.topRatedMovies
        trendingSection.liveData = mViewModel.trendingMovies
        trendingPerson.liveData = mViewModel.trendingPerson
    }

}