package com.example.cinemates.ui.details.movie

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import com.example.cinemates.R
import com.example.cinemates.databinding.FragmentMovieAboutBinding
import com.example.cinemates.model.Genre
import com.example.cinemates.ui.adapter.MovieAdapter
import com.example.cinemates.ui.adapter.VideoAdapter
import com.indisparte.horizontalchipview.HorizontalChipView
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


            val chipGroupGenres: HorizontalChipView<Genre> =
                view.findViewById<HorizontalChipView<Genre>>(R.id.chiGroupGenres)

            enableInnerScrollViewPager(trailers)

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

            viewLifecycleOwner.lifecycleScope.launchWhenCreated {

                launch {
                    viewModel.selectedMovie.collect { selectedMovie ->
                        movie = selectedMovie
                        if (selectedMovie != null) {
                            selectedMovie.genres?.let {
                                chipGroupGenres.setChipsList(
                                    it,
                                    textGetter = { genre -> genre.name }
                                )
                            }
                        }
                    }
                }

                launch {
                    viewModel.videos.collect { trailers ->
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
                            postersShower.path = posters.random().filePath
                        }
                    }
                }


                launch {
                    viewModel.backdrops.collect { backdrops ->
                        showBackdropShower(backdrops.isNotEmpty())
                        if (backdrops.isNotEmpty()) {
                            backdropCounter = backdrops.size
                            backdropsShower.path = backdrops.random().filePath
                        }
                    }
                }


                launch {
                    viewModel.collection.collect { collection ->
                        if (collection.parts.isNotEmpty()) {
                            Log.d(TAG, "onViewCreated: add collection size ${collection.parts.size}")
                            movieAdapter.updateItems(collection.parts)
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

    // Disable ViewPager2 from intercepting touch events of RecyclerView
    private fun enableInnerScrollViewPager(recyclerView: RecyclerView) {
        recyclerView.addOnItemTouchListener(object : RecyclerView.SimpleOnItemTouchListener() {
            override fun onInterceptTouchEvent(rv: RecyclerView, e: MotionEvent): Boolean {
                val action = e.actionMasked
                when (action) {
                    MotionEvent.ACTION_DOWN -> {
                        // Disallow ViewPager2 to intercept touch events of RecyclerView
                        rv.parent.requestDisallowInterceptTouchEvent(true)
                    }
                }
                return false
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}