package com.example.cinemates.view.ui.details.movie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.cinemates.R
import com.example.cinemates.adapter.MovieAdapter
import com.example.cinemates.adapter.YoutubeVideoRecyclerViewAdapter
import com.example.cinemates.databinding.FragmentMovieAboutBinding
import com.example.cinemates.databinding.FragmentMovieInfoBinding

class MovieAboutFragment : Fragment() {

    private var _binding: FragmentMovieAboutBinding? = null
    private val binding: FragmentMovieAboutBinding
        get() = _binding!!
    private lateinit var movieIntoCollectionAdapter: MovieAdapter
    private lateinit var videoAdapter: YoutubeVideoRecyclerViewAdapter
    private val viewModel: MovieDetailsViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        videoAdapter = YoutubeVideoRecyclerViewAdapter()
        movieIntoCollectionAdapter = MovieAdapter()
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

            collectionView.apply {
                moviesIntoCollection.adapter = movieIntoCollectionAdapter
                collectionCover.coverTitle.setOnClickListener {
                    //Remove collection cover
                    collectionCover.root.visibility = View.INVISIBLE

                }
                contentTitle.setOnClickListener {
                    //Add collection cover
                    collectionCover.root.visibility = View.VISIBLE
                }
            }


            viewModel.apply {
                selectedMovie.observe(viewLifecycleOwner) { selectedMovie ->
                    movie = selectedMovie
                }
                videos.observe(viewLifecycleOwner) { videos ->
                    if (videos.isEmpty()) {
                        trailerTitle.visibility = View.GONE
                        trailers.visibility = View.GONE
                    } else {
                        trailerTitle.visibility = View.VISIBLE
                        trailers.visibility = View.VISIBLE
                        videoAdapter.setDataList(videos)
                    }
                }
                moviesBelongsCollection.observe(viewLifecycleOwner) { collection ->
                    movieIntoCollectionAdapter.addItems(collection.parts)
                }
            }

        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}