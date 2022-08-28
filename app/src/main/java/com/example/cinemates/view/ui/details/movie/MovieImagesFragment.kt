package com.example.cinemates.view.ui.details.movie

import com.example.cinemates.adapter.ImageRecyclerViewAdapter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.cinemates.databinding.FragmentMovieImagesBinding

/**
 * @author Antonio Di Nuzzo
 * Created 19/06/2022 at 09:54
 */
class MovieImagesFragment : Fragment() {

    private var _binding: FragmentMovieImagesBinding? = null
    private val binding get() = _binding!!
    private lateinit var posterAdapter: ImageRecyclerViewAdapter
    private lateinit var backdropAdapter: ImageRecyclerViewAdapter
    private val viewModel: MovieDetailsViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        posterAdapter = ImageRecyclerViewAdapter(context)
        backdropAdapter = ImageRecyclerViewAdapter(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMovieImagesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            postersRv.adapter = posterAdapter
            postersRv.setEmptyView(emptyBackdropView.root)
            backdropRv.adapter = backdropAdapter
            backdropRv.setEmptyView(emptyBackdropView.root)
        }

        viewModel.imagesResponse.observe(viewLifecycleOwner) { images ->
            posterAdapter.addItems(images.posters)
            backdropAdapter.addItems(images.backdrops)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}