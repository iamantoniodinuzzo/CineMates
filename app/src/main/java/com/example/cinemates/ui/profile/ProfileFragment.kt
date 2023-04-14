package com.example.cinemates.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.interpolator.view.animation.FastOutSlowInInterpolator
import com.example.cinemates.R
import com.example.cinemates.common.BaseFragment
import com.example.cinemates.databinding.FragmentProfileBinding
import com.example.cinemates.databinding.LayoutSectionStatsBinding
import com.example.cinemates.util.getLong
import com.google.android.material.transition.MaterialFade
import com.google.android.material.transition.MaterialFadeThrough
import com.google.android.material.transition.MaterialSharedAxis

/**
 * @author Antonio Di Nuzzo
 * Created 28/08/2022 at 10:13
 */
class ProfileFragment : BaseFragment<FragmentProfileBinding>() {

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentProfileBinding
        get() = FragmentProfileBinding::inflate
    /*private lateinit var adapter: SectionRecyclerViewAdapter

    //Sections
    private val movieSection: Section<MovieDTO> =
        Section("Favorites", "Movies", MovieDTO::class.java, null, ViewSize.SMALL)
    private val personSection: Section<PersonDTO> =
        Section("Favorites", "Actors", PersonDTO::class.java, null, ViewSize.SMALL)
    private val sectionList: List<Section<*>> =
        listOf(movieSection, personSection)*/


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
          /*  recyclerView.adapter = adapter
            adapter.addItems(sectionList)*/

        }
//        movieSection.liveData = dbMovieViewModel.favorites
//        personSection.liveData = dbPersonViewModel.persons

    }

    private fun setCounter(stats: LayoutSectionStatsBinding, title:String, value:Int ){
        stats.statTitle.text = title
        stats.statContent.text = value.toString()
    }




}