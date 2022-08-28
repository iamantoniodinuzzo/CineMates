package com.example.cinemates.view.ui.search

import com.example.cinemates.adapter.ItemsRecyclerViewAdapter
import android.os.Bundle
import com.example.cinemates.util.ViewSize
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.cinemates.databinding.FragmentSearchActorBinding
import com.example.cinemates.model.data.Cast

class SearchActorFragment : Fragment() {
    private var _binding: FragmentSearchActorBinding? = null
    private val binding get() = _binding!!
    private val viewModel: SearchViewModel by activityViewModels()
    private lateinit var adapter: ItemsRecyclerViewAdapter<Cast>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        adapter = ItemsRecyclerViewAdapter(ViewSize.LONG)
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