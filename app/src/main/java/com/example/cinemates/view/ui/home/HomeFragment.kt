package com.example.cinemates.view.ui.home


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.doOnPreDraw
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.interpolator.view.animation.FastOutSlowInInterpolator
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI.setupWithNavController
import com.example.cinemates.R
import com.example.cinemates.adapter.SectionRecyclerViewAdapter
import com.example.cinemates.databinding.FragmentHomeBinding
import com.example.cinemates.model.entities.Movie
import com.example.cinemates.model.entities.Person
import com.example.cinemates.model.entities.Section
import com.example.cinemates.model.entities.TvShow
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

    private var _binding: FragmentHomeBinding? = null
    val binding: FragmentHomeBinding
        get() = _binding!!
    private val viewModel: HomeViewModel by viewModels()
    private lateinit var sectionAdapter: SectionRecyclerViewAdapter
    private lateinit var bottomNavigationView: BottomNavigationView

    // Sections
    private val upcomingSection: Section<Movie> =
        Section("Upcoming", "Movie", Movie::class.java, null, ViewSize.SMALL)
    private val topRatedSection: Section<Movie> =
        Section("Top Rated", "Movie", Movie::class.java, null, ViewSize.SMALL)
    private val trendingSection: Section<Movie> =
        Section("Trending this week", "Movies", Movie::class.java, null, ViewSize.SMALL)
    private val popularTvShow: Section<TvShow> =
        Section("Popular", "Tv Show", TvShow::class.java, null, ViewSize.SMALL)
    private val onTheAirTvShow: Section<TvShow> =
        Section("On The Air", "Tv Show", TvShow::class.java, null, ViewSize.SMALL)
    private val trendingTvShowSection: Section<TvShow> =
        Section("Trending this week", "Tv Show", TvShow::class.java, null, ViewSize.SMALL)
    private val trendingPerson: Section<Person> =
        Section("Trending this week", "Actors", Person::class.java, null, ViewSize.SMALL)
    private val sectionList: List<Section<*>> =
        listOf(
            upcomingSection,
            popularTvShow,
            topRatedSection,
            onTheAirTvShow,
            trendingSection,
            trendingTvShowSection,
            trendingPerson
        )


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sectionAdapter = SectionRecyclerViewAdapter(this)
        /* slideIn = AnimationUtils.loadAnimation(context, R.anim.slide_in)
         slideOut = AnimationUtils.loadAnimation(context, R.anim.slide_out)*/
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
        initSectionedRecyclerView()
    }

    private fun initSectionedRecyclerView() {
        sectionAdapter.addItems(sectionList)
        upcomingSection.liveData = viewModel.upcomingMovies
        popularTvShow.liveData = viewModel.popularTvShow
        topRatedSection.liveData = viewModel.topRatedMovies
        trendingSection.liveData = viewModel.trendingMovies
        trendingTvShowSection.liveData = viewModel.trendingTvShow
        trendingPerson.liveData = viewModel.trendingPerson
        onTheAirTvShow.liveData = viewModel.tvShowOnTheAir
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}