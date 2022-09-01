package com.example.cinemates.view.ui.filterable

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.cinemates.NavGraphDirections
import com.example.cinemates.R
import com.example.cinemates.adapter.ItemsRecyclerViewAdapter
import com.example.cinemates.databinding.FragmentFilterableBinding
import com.example.cinemates.databinding.LayoutCustomDialogRandomBinding
import com.example.cinemates.model.data.Movie
import com.example.cinemates.util.ViewSize

class FilterableFragment : Fragment() {
    private var _binding: FragmentFilterableBinding? = null
    private val binding: FragmentFilterableBinding
        get() = _binding!!
    private val viewModel: FilterableViewModel by activityViewModels()
    private val args: FilterableFragmentArgs by navArgs()
    private lateinit var adapter: ItemsRecyclerViewAdapter<Movie>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        adapter = ItemsRecyclerViewAdapter(ViewSize.SMALL)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentFilterableBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.setFilter(args.filter)
        Toast.makeText(context, args.filter.withGenres, Toast.LENGTH_SHORT).show()
        binding.apply {
            toolbar.setNavigationOnClickListener { requireActivity().onBackPressed() }
            recyclerView.adapter = adapter
            recyclerView.setEmptyView(emptyView.root)
            viewModel.movies.observe(viewLifecycleOwner) { movies ->
                adapter.addItems(movies.toMutableList())
                setFabVisibility(movies)
            }

            shuffle.root.setOnClickListener {
                showCustomDialog()
            }
        }

    }

    private fun FragmentFilterableBinding.setFabVisibility(movies: List<Movie>) {
        if (movies.isNotEmpty()) {
            shuffle.root.visibility = View.VISIBLE
        } else {
            shuffle.root.visibility = View.INVISIBLE
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
        poster.movie = movie
        dialog.setContentView(poster.root)
        poster.root.setOnClickListener {
            val action = NavGraphDirections.actionGlobalMovieDetailsFragment(movie)
            findNavController().navigate(action)
            dialog.dismiss()
        }
        dialog.show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}