package com.example.cinemates.view.ui.details.actor

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.cinemates.adapter.MovieAdapter
import com.example.cinemates.databinding.FragmentActorAboutBinding
import com.example.cinemates.databinding.FragmentListingItemsBinding

/**
 * @author Antonio Di Nuzzo (Indisparte)
 */
class ActorMoviesFragment : Fragment() {
    private var _binding: FragmentListingItemsBinding? = null
    private val binding: FragmentListingItemsBinding
        get() = _binding!!
    private val viewModel: ActorDetailsViewModel by activityViewModels()
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

        binding.counter = adapter.itemCount

        viewModel.movies.observe(viewLifecycleOwner) { moviesByActor ->
            adapter.addItems(moviesByActor)
            binding.counter = adapter.itemCount
        }

        binding.apply {
            recyclerView.adapter = adapter
            recyclerView.setEmptyView(binding.emptyView.root)
        }

    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}