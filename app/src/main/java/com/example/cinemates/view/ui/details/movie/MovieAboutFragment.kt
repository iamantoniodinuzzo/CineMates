package com.example.cinemates.view.ui.details.movie

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.cinemates.R
import com.example.cinemates.databinding.FragmentMovieAboutBinding
import com.example.cinemates.model.Genre
import com.indisparte.horizontalchipview.HorizontalChipView
import com.example.cinemates.view.ui.adapter.MovieAdapter
import com.example.cinemates.view.ui.adapter.VideoAdapter
import kotlinx.coroutines.launch

private val TAG = MovieAboutFragment::class.simpleName

class MovieAboutFragment() : Fragment() {

    private var _binding: FragmentMovieAboutBinding? = null
    private val binding: FragmentMovieAboutBinding
        get() = _binding!!
    private lateinit var videoAdapter: VideoAdapter
    private lateinit var movieAdapter: MovieAdapter
    private val viewModel: MovieDetailsViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        videoAdapter = VideoAdapter()
        movieAdapter = MovieAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentMovieAboutBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            trailers.adapter = videoAdapter
            collectionContent.collectionParts.adapter = movieAdapter


            val chipGroupGenres: com.indisparte.horizontalchipview.HorizontalChipView<Genre> =
                view.findViewById<com.indisparte.horizontalchipview.HorizontalChipView<Genre>>(R.id.chiGroupGenres)

            chipGroupGenres.onChipClicked = { genre ->
                // TODO: Implement search on click of genre, open search view
                Toast.makeText(
                    requireContext(),
                    "Soon - Search ${genre.name} genre",
                    Toast.LENGTH_SHORT
                ).show()
            }

            collectionCover.root.setOnClickListener {
                transformationLayout.startTransform()
            }

            collectionContent.root.setOnClickListener {
                transformationLayout.finishTransform()
            }

            viewLifecycleOwner.lifecycleScope.launch {
                lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {

                    launch {
                        viewModel.selectedMovie.collect { selectedMovie ->
                            movie = selectedMovie
                            chipGroupGenres.setChipsList(
                                selectedMovie.genres,
                                textGetter = { genre -> genre.name }
                            )
                        }
                    }

                    launch {
                        viewModel.videos.collect { trailers ->
                            Log.d(TAG, "onViewCreated: getting trailers")
                            showTrailerSection(trailers.isNotEmpty())
                            if (trailers.isNotEmpty()) {
                                videoAdapter.updateItems(trailers)
                            }
                        }
                    }

                    launch {
                        viewModel.posters.collect { posters ->
                            showPostersShower(posters.isNotEmpty())
                            if (posters.isNotEmpty()) {
                                posterCounter = posters.size
                                postersShower.path = posters.random().file_path
                            }
                        }
                    }

                    launch {
                        viewModel.backdrops.collect { backdrops ->
                            showBackdropShower(backdrops.isNotEmpty())
                            if (backdrops.isNotEmpty()) {
                                backdropCounter = backdrops.size
                                backdropsShower.path = backdrops.random().file_path
                            }
                        }
                    }


                    launch {
                        viewModel.partsOfCollection.collect { parts ->
                            if (parts.isNotEmpty()) {
                                Log.d(TAG, "onViewCreated: add parts size ${parts.size}")
                                movieAdapter.updateItems(parts)
                            }

                        }
                    }
                }
            }
        }

    }

    private fun FragmentMovieAboutBinding.showBackdropShower(notEmpty: Boolean) {
        backdropsShower.root.isVisible = notEmpty
    }

    private fun FragmentMovieAboutBinding.showPostersShower(notEmpty: Boolean) {
        postersShower.root.isVisible = notEmpty
    }

    private fun FragmentMovieAboutBinding.showTrailerSection(isNotEmpty: Boolean) {
        trailerTitle.isVisible = isNotEmpty
        binding.trailers.isVisible = isNotEmpty
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}