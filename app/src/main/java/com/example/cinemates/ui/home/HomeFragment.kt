package com.example.cinemates.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.cinemates.R
import com.example.cinemates.common.BaseFragment
import com.example.cinemates.databinding.FragmentHomeBinding
import com.example.cinemates.model.section.Section
import com.example.cinemates.model.section.SectionMovie
import com.example.cinemates.model.section.SectionPersons
import com.example.cinemates.model.section.SectionTvShow
import com.example.cinemates.ui.adapter.OnStartDragListener
import com.example.cinemates.ui.adapter.ReorderHelperCallback
import com.example.cinemates.ui.adapter.SectionAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.layout_tv_info.*
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlinx.coroutines.withTimeout

/**
 *@author Antonio Di Nuzzo (Indisparte)
 */
private val TAG = HomeFragment::class.simpleName

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>(), OnStartDragListener {

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentHomeBinding
        get() = FragmentHomeBinding::inflate

    private val viewModel: HomeViewModel by viewModels()
    private var mItemTouchHelper: ItemTouchHelper? = null
    private lateinit var sectionMoviePopular: SectionMovie
    private lateinit var sectionMovieTopRated: SectionMovie
    private lateinit var sectionMovieUpcoming: SectionMovie
    private lateinit var sectionTrendingPerson: SectionPersons
    private lateinit var sectionTrendingMovie: SectionMovie
    private lateinit var sectionTrendingTvShow: SectionTvShow
    private lateinit var sectionPopularTvShow: SectionTvShow
    private lateinit var sectionTvShowOnAir: SectionTvShow
    private var sections: MutableList<Section<*>> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sectionMoviePopular = SectionMovie("Movies popular", listOf())
        sectionMovieTopRated = SectionMovie("Movies Top rated", mutableListOf())
        sectionMovieUpcoming = SectionMovie("Movies Upcoming", mutableListOf())
        sectionTrendingPerson = SectionPersons("Trending persons", mutableListOf())
        sectionTrendingMovie = SectionMovie("Trending Movie", mutableListOf())
        sectionTrendingTvShow = SectionTvShow("Trending TvShow", mutableListOf())
        sectionPopularTvShow = SectionTvShow("Popular TvShow", mutableListOf())
        sectionTvShowOnAir = SectionTvShow("TvShow On Air", mutableListOf())
        sections = mutableListOf(
            sectionMoviePopular,
            sectionMovieUpcoming,
            sectionTvShowOnAir,
            sectionMovieTopRated,
            sectionTrendingTvShow,
            sectionTrendingPerson,
            sectionPopularTvShow,
            sectionTrendingMovie
        )
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {

            val adapter = SectionAdapter(sections, this@HomeFragment)
            sectionRv.adapter = adapter

            val callback: ItemTouchHelper.Callback = ReorderHelperCallback(adapter)
            mItemTouchHelper = ItemTouchHelper(callback)
            mItemTouchHelper?.attachToRecyclerView(sectionRv)

            toolbar.setOnMenuItemClickListener { item ->
                when (item.itemId) {
                    R.id.search -> Navigation.findNavController(view)
                        .navigate(R.id.action_homeFragment_to_searchFragment)

                }
                false
            }


            observeViewModel().invokeOnCompletion {
                Log.d(TAG, "Observation terminated, updating adapter")
                adapter.updateItems(sections)
            }


        }
    }

    private fun observeViewModel() =
        lifecycleScope.launchWhenCreated {

            withTimeout(1000) {
                launch {
                    viewModel.popularMovies.collectLatest { popular ->
                        sectionMoviePopular.items = popular
                    }
                }

                launch {
                    viewModel.topRatedMovies.collectLatest { topRated ->
                        sectionMovieTopRated.items = topRated
                    }
                }

                launch {
                    viewModel.upcomingMovies.collectLatest { upcoming ->
                        sectionMovieUpcoming.items = upcoming
                    }
                }

                launch {
                    viewModel.trendingPerson.collectLatest { trendingPersons ->
                        sectionTrendingPerson.items = trendingPersons
                    }
                }

                launch {
                    viewModel.trendingMovies.collectLatest { trendingMovies ->
                        sectionTrendingMovie.items = trendingMovies
                    }
                }
                launch {
                    viewModel.trendingTvShow.collectLatest { trendingTvShow ->
                        sectionTrendingTvShow.items = trendingTvShow
                    }
                }

                launch {
                    viewModel.popularTvShow.collectLatest { popularTvShow ->
                        sectionPopularTvShow.items = popularTvShow
                    }
                }
                launch {
                    viewModel.tvShowOnTheAir.collectLatest { tvShowOnTheAir ->
                        sectionTvShowOnAir.items = tvShowOnTheAir
                    }
                }
            }

        }


    override fun onStartDrag(viewHolder: RecyclerView.ViewHolder?) {
        viewHolder?.let {
            mItemTouchHelper?.startDrag(it)
        }
    }

}