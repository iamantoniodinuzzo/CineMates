package com.indisparte.media_discover.bottom_sheet

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.badge.BadgeDrawable
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.indisparte.media_discover.R
import com.indisparte.media_discover.databinding.BottomSheetFilterBinding
import timber.log.Timber

class BottomSheetFilter : BottomSheetDialogFragment() {
    private var _binding: BottomSheetFilterBinding? = null
    private val binding get() = _binding!!
    private val LOG = Timber.tag(BottomSheetFilter::class.java.simpleName)
    private var badgeDrawable: BadgeDrawable? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = BottomSheetFilterBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.cgSortTypes.setOnCheckedStateChangeListener { _, checkedIds ->
            when (checkedIds.first()) {
                R.id.chipPopularitySort -> {
                    LOG.d("Popularity")
                }
                R.id.chipVoteAverageSort -> {
                    LOG.d("Vote Average")
                }
            }
        }
    }
}
