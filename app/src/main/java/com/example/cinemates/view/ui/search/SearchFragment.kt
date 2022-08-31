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
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cinemates.R
import com.example.cinemates.adapter.ViewPagerAdapter
import com.example.cinemates.databinding.FragmentSearchBinding
import com.google.android.material.tabs.TabLayoutMediator
import com.google.android.material.transition.MaterialFadeThrough

class SearchFragment : Fragment() {
    private var _binding: FragmentSearchBinding? = null
    private val binding: FragmentSearchBinding
        get() = _binding!!

    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var gridLayoutManager: GridLayoutManager
    private lateinit var searchMovieFragment: SearchMovieFragment
    private lateinit var searchActorsFragment: SearchActorFragment
    private val viewModel: SearchViewModel by activityViewModels()
    private var layoutGrid = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        linearLayoutManager = LinearLayoutManager(context)
        gridLayoutManager = GridLayoutManager(context, 3)
        searchMovieFragment = SearchMovieFragment()
        searchActorsFragment = SearchActorFragment()

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
        setupTabLayout()
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
                /*searchMovieFragment!!.bindData("")
                searchActorsFragment!!.bindData("")*/
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
                    return false
                }

                override fun onQueryTextChange(query: String): Boolean {
                    viewModel.setQuery(query)
                    return false
                }
            })
        }
    }

    //Change menu icon in toolbar showing list or grid view
    private fun updateToolbar() {
        layoutGrid = !layoutGrid
        val gridView = binding.toolbar.menu.findItem(R.id.menu_switch_grid)
        gridView.isVisible = layoutGrid
        val listView = binding.toolbar.menu.findItem(R.id.menu_switch_list)
        listView.isVisible = !layoutGrid
        val filterView = binding.toolbar.menu.findItem(R.id.menu_filter)
        filterView.isVisible = false
    }

    private fun switchLayout(layoutManager: RecyclerView.LayoutManager?) {
        Toast.makeText(context, "Soon!", Toast.LENGTH_SHORT).show()
        /*searchMovieFragment!!.changeLayout(layoutManager)
        searchActorsFragment!!.changeLayout(layoutManager)*/
    }

    private fun setupTabLayout() {
        val viewPagerAdapter = ViewPagerAdapter(childFragmentManager, lifecycle)
        viewPagerAdapter.addFragment(searchMovieFragment)
        viewPagerAdapter.addFragment(searchActorsFragment)
        binding.viewPager.adapter = viewPagerAdapter
        TabLayoutMediator(binding.tabLayout, binding!!.viewPager) { tab, position ->
            when (position) {
                0 -> tab.text = "Movies"
                1 -> tab.text = "Actors"
            }
        }.attach()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}