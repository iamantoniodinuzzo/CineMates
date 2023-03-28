package com.example.cinemates.ui.details.tvShow

import android.os.Bundle
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
import com.example.cinemates.databinding.FragmentTvAboutBinding
import com.example.cinemates.model.Genre
import com.example.cinemates.ui.adapter.EpisodeGroupAdapter
import com.example.cinemates.ui.adapter.TvShowAdapter
import com.example.cinemates.ui.adapter.VideoAdapter
import com.indisparte.horizontalchipview.HorizontalChipView
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class TvAboutFragment() : Fragment() {

    private val TAG = TvAboutFragment::class.simpleName
    private var _binding: FragmentTvAboutBinding? = null
    private val binding: FragmentTvAboutBinding
        get() = _binding!!
    private lateinit var videoAdapter: VideoAdapter
    private lateinit var episodeGroupAdapter: EpisodeGroupAdapter
    private lateinit var tvAdapter: TvShowAdapter
    private val viewModel: TvDetailsViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        videoAdapter = VideoAdapter()
        tvAdapter = TvShowAdapter()
        episodeGroupAdapter = EpisodeGroupAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentTvAboutBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            trailers.adapter = videoAdapter
            episodeGroups.adapter = episodeGroupAdapter

            val customChipsView: HorizontalChipView<Genre> =
                view.findViewById<HorizontalChipView<Genre>>(R.id.chipGroupGenres)
            customChipsView.onChipClicked = { genre ->
                Toast.makeText(
                    requireContext(),
                    "Soon - Search ${genre.name} genre",
                    Toast.LENGTH_SHORT
                ).show()
            }

            enableInnerScrollViewPager(trailers)
            enableInnerScrollViewPager(episodeGroups)

            viewLifecycleOwner.lifecycleScope.launchWhenCreated {

                launch {
                    viewModel.selectedTv.collect { selectedTv ->
                        tv = selectedTv
                        if (selectedTv != null) {
                            customChipsView.setChipsList(
                                selectedTv.genres,
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
                    viewModel.episodeGroupList.collectLatest { list ->
                        episodeGroupAdapter.updateItems(list)

                    }
                }

            }
        }

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

    private fun FragmentTvAboutBinding.showBackdropShower(notEmpty: Boolean) {
        backdropsShower.root.isVisible = notEmpty
    }

    private fun FragmentTvAboutBinding.showPostersShower(notEmpty: Boolean) {
        postersShower.root.isVisible = notEmpty
    }

    private fun FragmentTvAboutBinding.showTrailerSection(isNotEmpty: Boolean) {
        trailerTitle.isVisible = isNotEmpty
        binding.trailers.isVisible = isNotEmpty
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}