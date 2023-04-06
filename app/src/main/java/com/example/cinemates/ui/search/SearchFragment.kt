package com.example.cinemates.ui.search

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnticipateOvershootInterpolator
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.widget.SearchView
import androidx.core.content.ContextCompat.getSystemService
import androidx.core.view.MenuItemCompat.setOnActionExpandListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cinemates.R
import com.example.cinemates.databinding.FragmentSearchBinding
import com.example.cinemates.ui.adapter.MovieAdapter
import com.example.cinemates.ui.adapter.PersonAdapter
import com.example.cinemates.ui.adapter.TvShowAdapter
import com.google.android.material.transition.MaterialFadeThrough
import kotlinx.coroutines.launch


private const val IS_GRID_LAYOUT = "isGridLayout"
private const val SPAN_COLUMN = 3

class SearchFragment : Fragment() {
    private var _binding: FragmentSearchBinding? = null
    private val binding: FragmentSearchBinding
        get() = _binding!!

    private lateinit var selectedLayoutManager: RecyclerView.LayoutManager
    private lateinit var movieAdapter: MovieAdapter
    private lateinit var personAdapter: PersonAdapter
    private lateinit var tvShowAdapter: TvShowAdapter
    private val viewModel: SearchViewModel by activityViewModels()
    private var isGridLayout = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

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
            toolbar.apply {
                setOnMenuItemClickListener { item ->
                    when (item.itemId) {
                        R.id.menu_switch_grid -> switchLayout()
                        R.id.menu_switch_list -> switchLayout()
                    }
                    updateToolbar()
                    false
                }

                //close keyboard when back button pressed
                setNavigationOnClickListener { view ->
                    (requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager).hideSoftInputFromWindow(
                        view.windowToken,
                        0
                    )
                    requireActivity().onBackPressed()
                }
            }

            //Set query parameter and open keyboard default
            searchView.apply {
                isIconified = false
                setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                    override fun onQueryTextSubmit(query: String): Boolean {
                        //NOTE set this query into viewModel
                        return false
                    }

                    override fun onQueryTextChange(query: String): Boolean {
                        viewModel.setQuery(query)
                        return false
                    }
                })

            }


            //listen viewModel changes
            viewLifecycleOwner.lifecycleScope.launchWhenCreated {
                launch {
                    viewModel.searchedMovies.collect { movies ->
                        movieAdapter.updateItems(movies)
                    }
                }
                launch {
                    viewModel.searchedActors.collect { actors ->
                        personAdapter.updateItems(actors)
                    }
                }

                launch {
                    viewModel.searchedTvShow.collect { tvShow ->
                        tvShowAdapter.updateItems(tvShow)
                    }
                }

            }


            //Restore the state if necessary
            if (savedInstanceState != null) {
                isGridLayout = savedInstanceState.getBoolean(IS_GRID_LAYOUT)
                selectedLayoutManager = if (isGridLayout) {
                    GridLayoutManager(requireContext(), SPAN_COLUMN)
                } else {
                    LinearLayoutManager(requireContext())
                }
                recyclerView.layoutManager = selectedLayoutManager
            }else{
                //set up RecyclerView
                recyclerView.adapter = movieAdapter
                selectedLayoutManager = GridLayoutManager(requireContext(), SPAN_COLUMN)
                recyclerView.layoutManager = selectedLayoutManager
            }


            // Set up the filter chips
            var lastCheckedId = -1
            chipGroup.setOnCheckedChangeListener { _, checkedId ->
                if (checkedId == lastCheckedId) {
                    // The same chip has been selected twice.
                    return@setOnCheckedChangeListener
                }
                lastCheckedId = checkedId
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

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putBoolean(IS_GRID_LAYOUT, selectedLayoutManager is GridLayoutManager)
    }


    //Change menu icon in toolbar showing list or grid view
    private fun updateToolbar() {
        val gridView = binding.toolbar.menu.findItem(R.id.menu_switch_grid)
        gridView.isVisible = !isGridLayout
        val listView = binding.toolbar.menu.findItem(R.id.menu_switch_list)
        listView.isVisible = isGridLayout
    }

    private fun switchLayout() {
        selectedLayoutManager = if (selectedLayoutManager is GridLayoutManager) {
            LinearLayoutManager(requireContext())
        } else {
            GridLayoutManager(requireContext(), SPAN_COLUMN)
        }
        binding.recyclerView.layoutManager = selectedLayoutManager
        tvShowAdapter.toggleLayoutType()
        movieAdapter.toggleLayoutType()
        personAdapter.toggleLayoutType()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}