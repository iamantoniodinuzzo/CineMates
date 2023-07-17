package com.indisparte.cinemates.ui.details.tvShow


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.Navigation
import com.indisparte.cinemates.databinding.FragmentEpisodeGroupDetailsBinding
import com.indisparte.home.adapter.SectionAdapter
import com.indisparte.ui.fragment.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

private val TAG = EpisodeGroupDetailsFragment::class.simpleName

/**
 *@author Antonio Di Nuzzo (Indisparte)
 */
@AndroidEntryPoint
class EpisodeGroupDetailsFragment : BaseFragment<FragmentEpisodeGroupDetailsBinding>() {

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentEpisodeGroupDetailsBinding
        get() = FragmentEpisodeGroupDetailsBinding::inflate

    override fun initializeViews() {
        TODO("Not yet implemented")
    }

    private val tvDetailsViewModel: TvDetailsViewModel by activityViewModels()
    private lateinit var groupSectionAdapter: SectionAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.toolbar.setNavigationOnClickListener {
            Navigation.findNavController(it).navigateUp()
        }
        binding.recyclerView.adapter = groupSectionAdapter


    }

}