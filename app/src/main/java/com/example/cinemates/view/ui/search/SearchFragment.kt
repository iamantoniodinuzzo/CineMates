package com.example.cinemates.view.ui.search

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnticipateOvershootInterpolator
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cinemates.R
import com.example.cinemates.view.ui.adapter.ViewPagerAdapter
import com.example.cinemates.databinding.FragmentSearchBinding
import com.example.cinemates.view.ui.adapter.MovieAdapter
import com.example.cinemates.view.ui.adapter.PersonAdapter
import com.example.cinemates.view.ui.adapter.TvShowAdapter
import com.google.android.material.tabs.TabLayoutMediator
import com.google.android.material.transition.MaterialFadeThrough
import kotlinx.coroutines.flow.collectLatest

class SearchFragment : Fragment() {
    private var _binding: FragmentSearchBinding? = null
    private val binding: FragmentSearchBinding
        get() = _binding!!

    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var gridLayoutManager: GridLayoutManager
    private lateinit var movieAdapter: MovieAdapter
    private lateinit var personAdapter: PersonAdapter
    private lateinit var tvShowAdapter: TvShowAdapter
    private val viewModel: SearchViewModel by activityViewModels()
    private var layoutGrid = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        linearLayoutManager = LinearLayoutManager(context)
        gridLayoutManager = GridLayoutManager(context, 3)

        //init adapters
        movieAdapter = MovieAdapter()
        personAdapter = PersonAdapter()
        tvShowAdapter = TvShowAdapter()

        setupMotionAnimations()
    }

    private fun setupMotionAnimations() {
        val fadeThroughTransition: Any = MaterialFadeThrough()
            .setInterpolator(AnticipateOvershootInterpolator())
        enterTransition = fadeThroughTransition
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        updateToolbar()

        binding.apply {
            // Listen menu item click and change layout into recyclerview
            // owned by SearchActor & SearchMovie fragment
            toolbar.setOnMenuItemClickListener { item ->
                when (item.itemId) {
                    R.id.menu_switch_grid -> switchLayout(gridLayoutManager)
                    R.id.menu_switch_list -> switchLayout(linearLayoutManager)
                }
                updateToolbar()
                false
            }

            //close keyboard when back button pressed
            toolbar.setNavigationOnClickListener { view ->
                (requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager).hideSoftInputFromWindow(
                    view.windowToken,
                    0
                )
                requireActivity().onBackPressed()
            }

            //Set query parameter
            searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String): Boolean {
                    //NOTE set this query into viewModel
                    viewModel.setQuery(query)
                    return false
                }

                override fun onQueryTextChange(query: String): Boolean {
                    return false
                }
            })

            //listen viewModel changes
            viewLifecycleOwner.lifecycleScope.launchWhenStarted {
                viewModel.searchedActors.collectLatest { actors ->
                    personAdapter.updateItems(actors)
                }
                viewModel.searchedMovies.collectLatest { movies ->
                    movieAdapter.updateItems(movies)
                }
                viewModel.searchedTvShow.collectLatest { tvShow ->
                    tvShowAdapter.updateItems(tvShow)
                }
            }

            // Set up the filter chips
            chipGroup.setOnCheckedChangeListener { _, checkedId ->
                val adapter = when (checkedId) {
                    R.id.movies_chip -> movieAdapter
                    R.id.actors_chip -> personAdapter
                    R.id.tv_show_chip -> tvShowAdapter
                    else -> throw IllegalArgumentException("Invalid filter chip ID")
                }
                recyclerView.adapter = adapter
            }
        }
    }

        //Change menu icon in toolbar showing list or grid view
        private fun updateToolbar() {
            layoutGrid = !layoutGrid
            val gridView = binding.toolbar.menu.findItem(R.id.menu_switch_grid)
            gridView.isVisible = layoutGrid
            val listView = binding.toolbar.menu.findItem(R.id.menu_switch_list)
            listView.isVisible = !layoutGrid
//        val filterView = binding.toolbar.menu.findItem(R.id.menu_filter)
//        filterView.isVisible = false
        }

        private fun switchLayout(layoutManager: RecyclerView.LayoutManager?) {
            Toast.makeText(context, "Soon!", Toast.LENGTH_SHORT).show()
            /*searchMovieFragment!!.changeLayout(layoutManager)
            searchActorsFragment!!.changeLayout(layoutManager)*/
        }

        override fun onDestroyView() {
            super.onDestroyView()
            _binding = null
        }
    }