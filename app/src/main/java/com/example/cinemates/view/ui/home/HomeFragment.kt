package com.example.cinemates.view.ui.home


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.core.view.doOnPreDraw
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.interpolator.view.animation.FastOutSlowInInterpolator
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI.setupWithNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.cinemates.R
import com.example.cinemates.adapter.SectionRecyclerViewAdapter
import com.example.cinemates.databinding.FragmentHomeBinding
import com.example.cinemates.model.entities.Movie
import com.example.cinemates.model.entities.Person
import com.example.cinemates.model.entities.Section
import com.example.cinemates.util.ViewSize
import com.example.cinemates.view.ui.MainActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.transition.MaterialElevationScale
import dagger.hilt.android.AndroidEntryPoint

/**
 * @author Antonio Di Nuzzo
 * @author Jon Areas
 * Created 24/08/2022 at 09:03
 */
@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding : FragmentHomeBinding? = null
     val binding : FragmentHomeBinding
        get() = _binding!!
    private val viewModel: HomeViewModel by viewModels()
    private lateinit var sectionAdapter: SectionRecyclerViewAdapter
    private lateinit var bottomNavigationView: BottomNavigationView
    private lateinit var slideIn: Animation
    private lateinit var slideOut: Animation

    // Sections
    private val upcomingSection: Section<Movie> =
        Section("Upcoming", null, Movie::class.java, null, ViewSize.SMALL)
    private val topRatedSection: Section<Movie> =
        Section("Top Rated", null, Movie::class.java, null, ViewSize.SMALL)
    private var trendingSection: Section<Movie> =
        Section("Trending this week", "Movies", Movie::class.java, null, ViewSize.SMALL)
    private var trendingPerson: Section<Person> =
        Section("Trending this week", "Actors", Person::class.java, null, ViewSize.SMALL)
    private var sectionList: List<Section<*>> =
        listOf(upcomingSection, topRatedSection, trendingSection, trendingPerson)



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sectionAdapter = SectionRecyclerViewAdapter(this)
        slideIn = AnimationUtils.loadAnimation(context, R.anim.slide_in)
        slideOut = AnimationUtils.loadAnimation(context, R.anim.slide_out)
        setupTransitions()
    }

    private fun setupTransitions() {
        val elevationScaleTransition = MaterialElevationScale(true)
            .setInterpolator(FastOutSlowInInterpolator())
        enterTransition = elevationScaleTransition
        reenterTransition = elevationScaleTransition
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        val appBarConfiguration = AppBarConfiguration(findNavController().graph)
        setupWithNavController(binding.toolbar, findNavController(), appBarConfiguration)

        return binding.root
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?,
    ) {
        postponeEnterTransition()
        view.doOnPreDraw { startPostponedEnterTransition() }
        bottomNavigationView = (activity as MainActivity).binding.bottomNavigationView
        setupListeners()
        setupRecyclerView()
    }

    private fun setupListeners() = binding.run {
        imageProfile.setOnClickListener {
            Toast.makeText(context, "Soon", Toast.LENGTH_SHORT).show()
        }

        toolbar.setOnMenuItemClickListener {
            if (it.itemId == R.id.searchFragment)
                findNavController().navigate(R.id.action_homeFragment_to_searchFragment)
            true
        }
    }

    private fun setupRecyclerView() = binding.sectionRv.run {
        adapter = sectionAdapter
        // Hides BottomNavigationView OnScroll
        addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (dy > 0 && bottomNavigationView.isShown) {
                        bottomNavigationView.startAnimation(slideOut)
                        bottomNavigationView.visibility = View.INVISIBLE
                    } else if (dy < 0) {
                        bottomNavigationView.startAnimation(slideIn)
                        bottomNavigationView.visibility = View.VISIBLE
                    }
            }
        })
        initSectionedRecyclerView()
    }

    private fun initSectionedRecyclerView() {
        sectionAdapter.addItems(sectionList)
        upcomingSection.liveData = viewModel.upcomingMovies
        topRatedSection.liveData = viewModel.topRatedMovies
        trendingSection.liveData = viewModel.trendingMovies
        trendingPerson.liveData = viewModel.trendingPerson
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}