package com.example.cinemates.view.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
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
import com.google.android.material.transition.MaterialFadeThrough

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
        setupMotionAnimations()
    }

    private fun setupMotionAnimations() {
        enterTransition = MaterialFadeThrough()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
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

            setCounter(statHours, "Hours", dbMovieViewModel.getTotalHoursOf(PersonalStatus.EMPTY))
            setCounter(statWatchedCounter, "Movies Seen", dbMovieViewModel.getTotalHoursOf(PersonalStatus.SEEN))
            setCounter(statToSeeCounter, "Movies To See", dbMovieViewModel.getTotalHoursOf(PersonalStatus.TO_SEE))
        }
        movieSection.liveData = dbMovieViewModel.favorites
        personSection.liveData = dbPersonViewModel.persons

    }

    private fun setCounter(stats: LayoutSectionStatsBinding, title:String, value:String ){
        stats.statTitle.text = title
        stats.statContent.text = value
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}