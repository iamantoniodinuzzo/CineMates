package com.example.cinemates.ui.details.tvShow

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
import com.example.cinemates.databinding.FragmentTvAboutBinding
import com.example.cinemates.domain.model.Genre
import com.example.cinemates.domain.model.Image
import com.example.cinemates.ui.adapter.EpisodeGroupAdapter
import com.example.cinemates.ui.adapter.MediaAdapter
import com.example.cinemates.ui.adapter.VideoAdapter
import com.indisparte.horizontalchipview.HorizontalChipView
import kotlinx.android.synthetic.main.fragment_movie_about.*
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class TvAboutFragment() : BaseFragment<FragmentTvAboutBinding>() {

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentTvAboutBinding
        get() = FragmentTvAboutBinding::inflate

    private lateinit var videoAdapter: VideoAdapter
    private lateinit var episodeGroupAdapter: EpisodeGroupAdapter
    private lateinit var mediaAdapter: MediaAdapter
    private val viewModel: TvDetailsViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        videoAdapter = VideoAdapter()
        mediaAdapter = MediaAdapter()
        episodeGroupAdapter = EpisodeGroupAdapter()
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            tvTrailers.adapter = videoAdapter
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

            enableInnerScrollViewPager(tvTrailers)
            enableInnerScrollViewPager(episodeGroups)

            viewLifecycleOwner.lifecycleScope.launchWhenCreated {

                launch {
                    viewModel.selectedTv.collect { selectedTv ->
                        selectedTv?.let {
                            tv = it
                            it.genres?.let { it1 ->
                                customChipsView.setChipsList(
                                    it1,
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

    private fun FragmentTvAboutBinding.showTrailerSection(isNotEmpty: Boolean) {
        trailerTitle.isVisible = isNotEmpty
        binding.tvTrailers.isVisible = isNotEmpty
    }



}