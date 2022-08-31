package com.example.cinemates.view.ui.details.movie

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import com.example.cinemates.adapter.ItemsRecyclerViewAdapter
import com.example.cinemates.databinding.LayoutCollectionDialogBinding
import com.example.cinemates.model.data.Movie
import com.example.cinemates.util.ViewSize

/**
 * @author Antonio Di Nuzzo
 * Created 27/05/2022 at 17:56
 */
class CollectionDialogFragment : DialogFragment() {

    private var _binding: LayoutCollectionDialogBinding? = null
    private val binding: LayoutCollectionDialogBinding
        get() = _binding!!
    private lateinit var adapter: ItemsRecyclerViewAdapter<Movie>
    private val viewModel: MovieDetailsViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        adapter = ItemsRecyclerViewAdapter(ViewSize.SMALL)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = LayoutCollectionDialogBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            moviesIntoCollection.adapter = adapter
            viewModel.selectedMovie.observe(viewLifecycleOwner) { selectedMovie ->
                collection = selectedMovie.belongs_to_collection
            }
            viewModel.moviesBelongsCollection.observe(viewLifecycleOwner) { moviesBelongsCollection ->
                Log.d("CollectionDialog", moviesBelongsCollection.toString())
                adapter.addItems(moviesBelongsCollection.toMutableList())
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}