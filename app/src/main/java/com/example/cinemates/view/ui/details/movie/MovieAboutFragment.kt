package com.example.cinemates.view.ui.details.movie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.cinemates.R
import com.example.cinemates.view.ui.adapter.MovieAdapter
import com.example.cinemates.view.ui.adapter.YoutubeVideoRecyclerViewAdapter
import com.example.cinemates.databinding.FragmentMovieAboutBinding

class MovieAboutFragment : Fragment() {

    private var _binding: FragmentMovieAboutBinding? = null
    private val binding: FragmentMovieAboutBinding
        get() = _binding!!
    private lateinit var videoAdapter: YoutubeVideoRecyclerViewAdapter
    private val viewModel: MovieDetailsViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        videoAdapter = YoutubeVideoRecyclerViewAdapter()
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
            collectionView.root.setOnClickListener { _ ->
                Toast.makeText(requireContext(), "Soon", Toast.LENGTH_SHORT).show()
                //TODO show dialog with movies belongs collection
            }

        }

        viewModel.apply {
            selectedMovie.observe(viewLifecycleOwner) { selectedMovie ->
                binding.movie = selectedMovie
            }
            videos.observe(viewLifecycleOwner) { videos ->
                if (videos.isEmpty()) {
                    binding.trailerTitle.visibility = View.GONE
                    binding.trailers.visibility = View.GONE
                } else {
                    binding.trailerTitle.visibility = View.VISIBLE
                    binding.trailers.visibility = View.VISIBLE
                    videoAdapter.setDataList(videos)
                }
            }

            posters.observe(viewLifecycleOwner) { posters ->
                if (posters.isNotEmpty()) {
                    binding.posterCounter = posters.size
                    binding.posters.path = posters.random().file_path
                }
            }
            backdrops.observe(viewLifecycleOwner) { backdrops ->
                if (backdrops.isNotEmpty()) {
                    binding.backdropCounter = backdrops.size
                    binding.backdrops.path = backdrops.random().file_path
                }
            }
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}