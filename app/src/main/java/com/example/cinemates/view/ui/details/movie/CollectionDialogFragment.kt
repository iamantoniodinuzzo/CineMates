package com.example.cinemates.view.ui.details.movie

import com.example.cinemates.adapter.ItemsRecyclerViewAdapter
import android.os.Bundle
import com.example.cinemates.util.ViewSize
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import com.example.cinemates.databinding.LayoutCollectionDialogBinding
import com.example.cinemates.model.data.Movie

/**
 * @author Antonio Di Nuzzo
 * Created 27/05/2022 at 17:56
 */
class CollectionDialogFragment : DialogFragment() {

    private lateinit var mBinding: LayoutCollectionDialogBinding
    private lateinit var mAdapter: ItemsRecyclerViewAdapter<Movie>
    private val viewModel: MovieDetailsViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mAdapter = ItemsRecyclerViewAdapter(ViewSize.SMALL)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mBinding = LayoutCollectionDialogBinding.inflate(inflater, container, false)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mBinding.apply {
            moviesIntoCollection.adapter = mAdapter
            viewModel.selectedMovie.observe(viewLifecycleOwner) {
                collectionTitle.text = it.belongs_to_collection.name
            }
            viewModel.moviesBelongsCollection.observe(viewLifecycleOwner) { moviesBelongsCollection ->
                mAdapter.addItems(moviesBelongsCollection.toMutableList())
            }
        }
    }
}