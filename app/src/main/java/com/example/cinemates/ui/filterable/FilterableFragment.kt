package com.example.cinemates.ui.filterable

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.core.view.doOnPreDraw
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.navArgs
import com.example.cinemates.R
import com.example.cinemates.databinding.FragmentFilterableBinding
import com.example.cinemates.databinding.LayoutCustomDialogRandomBinding
import com.example.cinemates.model.Movie
import com.example.cinemates.ui.adapter.MovieAdapter
import com.example.cinemates.common.BaseFragment

class FilterableFragment : BaseFragment<FragmentFilterableBinding>() {

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentFilterableBinding
        get() = FragmentFilterableBinding::inflate

    private val viewModel: FilterableViewModel by activityViewModels()
    private val args: FilterableFragmentArgs by navArgs()
    private lateinit var adapter: MovieAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        adapter = MovieAdapter()
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        postponeEnterTransition()
        view.doOnPreDraw { startPostponedEnterTransition() }
        viewModel.setFilter(args.filter)
        binding.apply {
            filter = args.filter

            toolbar.setNavigationOnClickListener { requireActivity().onBackPressed() }

            recyclerView.adapter = adapter

            viewModel.movies.observe(viewLifecycleOwner) { movies ->
                adapter.updateItems(movies)
                setFabVisibility(movies)
            }

            shuffle.setOnClickListener {
                showCustomDialog()
            }
        }

    }

    private fun FragmentFilterableBinding.setFabVisibility(movies: List<Movie>) {
        if (movies.isNotEmpty()) {
            shuffle.visibility = View.VISIBLE
        } else {
            shuffle.visibility = View.INVISIBLE
        }
    }

    private fun showCustomDialog() {

        val dialog = Dialog(requireContext(), R.style.AppDialogTheme)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(true)
        dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        val poster = LayoutCustomDialogRandomBinding.inflate(
            layoutInflater
        )
        val movie: Movie = viewModel.getRandomMovie()!!
        poster.media = movie
        dialog.setContentView(poster.root)
        poster.root.setOnClickListener {
           /* val action = NavGraphDirections.actionGlobalMovieDetailsFragment(movie)
            findNavController().navigate(action)
            dialog.dismiss()*/
        }
        dialog.show()
    }


}