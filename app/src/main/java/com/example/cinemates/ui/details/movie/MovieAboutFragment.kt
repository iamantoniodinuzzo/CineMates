package com.example.cinemates.ui.details.movie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import com.example.cinemates.R
import com.example.cinemates.common.BaseFragment
import com.example.cinemates.databinding.FragmentMovieAboutBinding
import com.example.cinemates.domain.model.Genre
import com.example.cinemates.domain.model.Image
import com.example.cinemates.ui.adapter.MediaAdapter
import com.example.cinemates.ui.adapter.VideoAdapter
import com.indisparte.horizontalchipview.HorizontalChipView
import kotlinx.coroutines.launch

private val TAG = MovieAboutFragment::class.simpleName

class MovieAboutFragment : BaseFragment<FragmentMovieAboutBinding>() {
    
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentMovieAboutBinding
        get() = FragmentMovieAboutBinding::inflate

    private lateinit var videoAdapter: VideoAdapter
    private lateinit var mediaAdapter: MediaAdapter
    private val viewModel: MovieDetailsViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        videoAdapter = VideoAdapter()
        mediaAdapter = MediaAdapter()
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            trailers.adapter = videoAdapter
            collectionContent.collectionParts.adapter = mediaAdapter


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
                        selectedMovie?.genres?.let {
                            chipGroupGenres.setChipsList(
                                it,
                                textGetter = { genre -> genre.name }
                            )
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
                    viewModel.collection.collect { collection ->
                        collection.let {
                            mediaAdapter.updateItems(it.parts)
                        }



                    }
                }

            }

        }

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


}