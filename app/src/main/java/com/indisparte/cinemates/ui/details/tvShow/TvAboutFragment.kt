package com.indisparte.cinemates.ui.details.tvShow

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.RecyclerView
import com.indisparte.cinemates.R
import com.indisparte.cinemates.databinding.FragmentTvAboutBinding
import com.indisparte.cinemates.domain.model.common.Genre
import com.indisparte.ui.fragment.BaseFragment

class TvAboutFragment() : BaseFragment<FragmentTvAboutBinding>() {

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentTvAboutBinding
        get() = FragmentTvAboutBinding::inflate

    override fun initializeViews() {
        TODO("Not yet implemented")
    }

    private val viewModel: TvDetailsViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {

           /* val customChipsView: HorizontalChipView<Genre> =
                view.findViewById<HorizontalChipView<Genre>>(R.id.chipGroupGenres)*/


            enableInnerScrollViewPager(tvTrailers)
            enableInnerScrollViewPager(episodeGroups)


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