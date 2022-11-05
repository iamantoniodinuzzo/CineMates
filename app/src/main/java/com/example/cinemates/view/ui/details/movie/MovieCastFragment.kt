package com.example.cinemates.view.ui.details.movie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.cinemates.adapter.PersonAdapter
import com.example.cinemates.databinding.FragmentMovieCastBinding
import com.example.cinemates.model.data.Cast
import com.example.cinemates.util.ViewSize

class MovieCastFragment : Fragment() {

    private var _binding: FragmentMovieCastBinding? = null
    private val binding: FragmentMovieCastBinding
        get() = _binding!!

    private lateinit var adapter: PersonAdapter
    private val viewModel: MovieDetailsViewModel by activityViewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        adapter = PersonAdapter()
        adapter.viewSize = ViewSize.LONG
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentMovieCastBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.castRecyclerView.adapter = adapter
        viewModel.cast.observe(viewLifecycleOwner) { cast ->
            adapter.addItems(cast)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}