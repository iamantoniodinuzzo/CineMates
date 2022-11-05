package com.example.cinemates.view.ui.saved

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.cinemates.adapter.MovieAdapter
import com.example.cinemates.databinding.FragmentListingBinding
import com.example.cinemates.view.viewmodel.DbMovieViewModel

/**
 * @author Antonio Di Nuzzo
 * Created 25/07/2022 at 08:24
 */
class SeenFragment : Fragment() {
    private var _binding: FragmentListingBinding? = null
    private val binding: FragmentListingBinding
        get() = _binding!!
    private val dbViewModel: DbMovieViewModel by activityViewModels()
    private lateinit var adapter: MovieAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        adapter = MovieAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentListingBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            recyclerView.adapter = adapter
            recyclerView.setEmptyView(emptyView.root)
            dbViewModel.seen.observe(viewLifecycleOwner){movies->
                adapter.addItems(movies)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}