package com.example.cinemates.view.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.interpolator.view.animation.FastOutSlowInInterpolator
import com.example.cinemates.R
import com.example.cinemates.adapter.SectionRecyclerViewAdapter
import com.example.cinemates.databinding.FragmentProfileBinding
import com.example.cinemates.databinding.LayoutSectionStatsBinding
import com.example.cinemates.model.data.Movie
import com.example.cinemates.model.data.Person
import com.example.cinemates.model.data.PersonalStatus
import com.example.cinemates.model.data.Section
import com.example.cinemates.util.ViewSize
import com.example.cinemates.view.viewmodel.DbMovieViewModel
import com.example.cinemates.view.viewmodel.DbPersonViewModel
import com.example.cinemates.util.getLong
import com.google.android.material.transition.MaterialFade
import com.google.android.material.transition.MaterialFadeThrough
import com.google.android.material.transition.MaterialSharedAxis

/**
 * @author Antonio Di Nuzzo
 * Created 28/08/2022 at 10:13
 */
class ProfileFragment : Fragment() {
    private var _binding: FragmentProfileBinding? = null
    private val binding: FragmentProfileBinding
        get() = _binding!!
    private val dbMovieViewModel: DbMovieViewModel by activityViewModels()
    private val dbPersonViewModel: DbPersonViewModel by activityViewModels()

    private lateinit var adapter: SectionRecyclerViewAdapter

    //Sections
    private val movieSection: Section<Movie> =
        Section("Favorites", "Movies", Movie::class.java, null, ViewSize.SMALL)
    private val personSection: Section<Person> =
        Section("Favorites", "Actors", Person::class.java, null, ViewSize.SMALL)
    private val sectionList: List<Section<*>> =
        listOf(movieSection, personSection)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        adapter = SectionRecyclerViewAdapter(this)
        enterTransition = MaterialFadeThrough()
        reenterTransition = MaterialSharedAxis(MaterialSharedAxis.X, false).apply {
            interpolator = FastOutSlowInInterpolator()
            duration = resources.getLong(R.integer.material_motion_duration_long_2)
        }
        exitTransition = MaterialFade()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentProfileBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            recyclerView.adapter = adapter
            adapter.addItems(sectionList)

            setCounter(statHours, "Total Hours", dbMovieViewModel.getTotalHoursOf(PersonalStatus.EMPTY))
            setCounter(statWatchedCounter, "Movies Seen", dbMovieViewModel.getSizeOf(PersonalStatus.SEEN))
            setCounter(statToSeeCounter, "Movies To See", dbMovieViewModel.getSizeOf(PersonalStatus.TO_SEE))
        }
        movieSection.liveData = dbMovieViewModel.favorites
        personSection.liveData = dbPersonViewModel.persons

    }

    private fun setCounter(stats: LayoutSectionStatsBinding, title:String, value:Int ){
        stats.statTitle.text = title
        stats.statContent.text = value.toString()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}