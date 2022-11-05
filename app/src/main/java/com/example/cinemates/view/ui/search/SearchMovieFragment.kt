package com.example.cinemates.view.ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.cinemates.adapter.MovieAdapter
import com.example.cinemates.databinding.FragmentSearchMovieBinding

class SearchMovieFragment : Fragment() {
    private var _binding: FragmentSearchMovieBinding? = null
    private val binding: FragmentSearchMovieBinding
        get() = _binding!!
    private val viewModel: SearchViewModel by activityViewModels()
    private lateinit var adapter: MovieAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        adapter = MovieAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentSearchMovieBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            recyclerView.adapter = adapter
            recyclerView.setEmptyView(emptyView.root)
        }
        viewModel.queriedMovies.observe(viewLifecycleOwner) { movies ->
            adapter.addItems(movies)
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}