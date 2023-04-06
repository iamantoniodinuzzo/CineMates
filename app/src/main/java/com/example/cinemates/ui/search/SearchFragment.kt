package com.example.cinemates.ui.search

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnticipateOvershootInterpolator
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.widget.SearchView
import androidx.core.content.ContextCompat.getSystemService
import androidx.core.view.MenuItemCompat.setOnActionExpandListener
import androidx.databinding.adapters.SearchViewBindingAdapter.setOnQueryTextListener
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
import com.example.cinemates.util.ViewSize
import com.google.android.material.transition.MaterialFadeThrough
import kotlinx.android.synthetic.main.fragment_profile.*
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
//    private var isGridLayout = true

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
                    viewModel.isGridLayout = !viewModel.isGridLayout
                    updateToolbar()
                    updateLayoutType()
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
                        viewModel.setQuery(query)
                        return false
                    }

                    override fun onQueryTextChange(query: String): Boolean {
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

            selectedLayoutManager = if (viewModel.isGridLayout) {
                GridLayoutManager(requireContext(), SPAN_COLUMN)
            } else {
//                    isGridLayout = false
                LinearLayoutManager(requireContext())
            }
            recyclerView.layoutManager = selectedLayoutManager

            //Restore the state if necessary
            /*if (savedInstanceState != null) {
                Log.d(SearchFragment::class.simpleName, "onViewCreated: Esiste uno stato precedente, lo ripristino")
                isGridLayout = savedInstanceState.getBoolean(IS_GRID_LAYOUT)
                selectedLayoutManager = if (isGridLayout) {
                    GridLayoutManager(requireContext(), SPAN_COLUMN)
                } else {
//                    isGridLayout = false
                    LinearLayoutManager(requireContext())
                }
                recyclerView.layoutManager = selectedLayoutManager
            }else{
                //set up RecyclerView
                Log.d(SearchFragment::class.simpleName, "onViewCreated: NON Esiste uno stato precedente")
                recyclerView.adapter = movieAdapter
                selectedLayoutManager = GridLayoutManager(requireContext(), SPAN_COLUMN)
                recyclerView.layoutManager = selectedLayoutManager
                Log.d(SearchFragment::class.simpleName, "onViewCreated: Ho aggiunto movieAdapter e GridLayout")

            }*/


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

  /*  override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        // TODO: Questo metodo nonviene mai richiamato
        Log.d(SearchFragment::class.simpleName, "onSaveInstanceState: Salvo lo stato")
        outState.putBoolean(IS_GRID_LAYOUT, isGridLayout)
    }
*/


    //Change menu icon in toolbar showing list or grid view
    private fun updateToolbar() {
        Log.d(SearchFragment::class.simpleName, "isGridLayout? ${viewModel.isGridLayout} aggiorno la toolbar")
        val gridView = binding.toolbar.menu.findItem(R.id.menu_switch_grid)
        gridView.isVisible = !viewModel.isGridLayout
        val listView = binding.toolbar.menu.findItem(R.id.menu_switch_list)
        listView.isVisible = viewModel.isGridLayout
    }

    private fun updateLayoutType() {
        if (viewModel.isGridLayout){
            Log.d(SearchFragment::class.simpleName, "updateLayoutType: Small View")
            tvShowAdapter.currentLayoutType = ViewSize.SMALL
            movieAdapter.currentLayoutType = ViewSize.SMALL
            personAdapter.currentLayoutType = ViewSize.SMALL
        }else{
            Log.d(SearchFragment::class.simpleName, "updateLayoutType: Long View")
            tvShowAdapter.currentLayoutType = ViewSize.LONG
            movieAdapter.currentLayoutType = ViewSize.LONG
            personAdapter.currentLayoutType = ViewSize.LONG
        }
    }

    private fun switchLayout() {
        Log.d(SearchFragment::class.simpleName, "switchLayout: Cambio layout manager")
        selectedLayoutManager = if (selectedLayoutManager is GridLayoutManager) {
            LinearLayoutManager(requireContext())
        } else {
            GridLayoutManager(requireContext(), SPAN_COLUMN)
        }
        binding.recyclerView.layoutManager = selectedLayoutManager
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}