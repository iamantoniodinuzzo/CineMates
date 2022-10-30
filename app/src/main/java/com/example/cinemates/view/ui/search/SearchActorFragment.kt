package com.example.cinemates.view.ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.cinemates.adapter.MultiViewTypeRecyclerViewAdapter
import com.example.cinemates.databinding.FragmentSearchActorBinding
import com.example.cinemates.model.data.Cast
import com.example.cinemates.util.ViewSize

class SearchActorFragment : Fragment() {
    private var _binding: FragmentSearchActorBinding? = null
    private val binding: FragmentSearchActorBinding
        get() = _binding!!
    private val viewModel: SearchViewModel by activityViewModels()
    private lateinit var adapter: MultiViewTypeRecyclerViewAdapter<Cast>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        adapter = MultiViewTypeRecyclerViewAdapter(ViewSize.LONG)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentSearchActorBinding.inflate(inflater, container, false)
        binding.apply {
            recyclerView.adapter = adapter
            recyclerView.setEmptyView(emptyView.root)
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.queriedActors.observe(viewLifecycleOwner) { persons ->
            adapter.addItems(persons.toMutableList())
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}