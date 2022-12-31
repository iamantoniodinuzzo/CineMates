package com.example.cinemates.view.ui.details.movie

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cinemates.adapter.PersonAdapter
import com.example.cinemates.model.Person
import com.example.cinemates.util.ViewSize
import com.example.cinemates.view.ui.ListFragment

class MovieCastFragment : ListFragment<Person, PersonAdapter>(PersonAdapter()) {

    private val viewModel: MovieDetailsViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        adapter.viewSize = ViewSize.LONG
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recyclerView.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

        viewModel.cast.observe(viewLifecycleOwner) { cast ->
            adapter.addItems(cast)
            binding.counter = cast.size
        }
    }

}