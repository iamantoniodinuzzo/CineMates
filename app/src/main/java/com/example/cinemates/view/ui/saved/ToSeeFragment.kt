package com.example.cinemates.view.ui.saved

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.cinemates.adapter.MovieAdapter
import com.example.cinemates.databinding.FragmentListingItemsBinding
import com.example.cinemates.view.viewmodel.DbMovieViewModel

/**
 * @author Antonio Di Nuzzo
 * Created 25/07/2022 at 08:24
 */
class ToSeeFragment : Fragment() {
    private var _binding: FragmentListingItemsBinding? = null
    private val binding: FragmentListingItemsBinding
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
        _binding = FragmentListingItemsBinding.inflate(inflater, container, false)


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            recyclerView.adapter = adapter
            recyclerView.setEmptyView(emptyView.root)
            dbViewModel.toSee.observe(viewLifecycleOwner){movies->
                adapter.addItems(movies)
                counter=adapter.itemCount
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}