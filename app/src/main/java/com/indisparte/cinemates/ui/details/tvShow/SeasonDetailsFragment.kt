package com.indisparte.cinemates.ui.details.tvShow


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.indisparte.cinemates.databinding.FragmentSeasonDetailsBinding
import com.indisparte.ui.fragment.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

private val TAG = EpisodeGroupDetailsFragment::class.simpleName

/**
 *@author Antonio Di Nuzzo (Indisparte)
 */
@AndroidEntryPoint
class SeasonDetailsFragment : BaseFragment<FragmentSeasonDetailsBinding>() {

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentSeasonDetailsBinding
        get() = FragmentSeasonDetailsBinding::inflate

    private val tvDetailsViewModel: TvDetailsViewModel by activityViewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }

}