package com.example.cinemates.view.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.cinemates.adapter.SectionRecyclerViewAdapter
import com.example.cinemates.databinding.FragmentProfileBinding
import com.example.cinemates.model.data.Movie
import com.example.cinemates.model.data.Person
import com.example.cinemates.model.data.PersonalStatus
import com.example.cinemates.model.data.Section
import com.example.cinemates.util.ViewSize
import com.example.cinemates.view.viewmodel.DbViewModel
import com.google.android.material.transition.MaterialFadeThrough

/**
 * @author Antonio Di Nuzzo
 * Created 28/08/2022 at 10:13
 */
class ProfileFragment : Fragment() {
    private var _binding: FragmentProfileBinding? = null
    private val binding: FragmentProfileBinding
        get() = _binding!!
    private val dbViewModel: DbViewModel by activityViewModels()

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
        adapter = SectionRecyclerViewAdapter(this, context)
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
            statHours.statTitle.text = "Hours"
            statHours.statContent.text = dbViewModel.getTotalHours()
            statWatchedCounter.statTitle.text = "Movies Seen"
            statWatchedCounter.statContent.text = dbViewModel.getTotalHoursOf(PersonalStatus.SEEN)
            statToSeeCounter.statTitle.text = "Movies To See"
            statToSeeCounter.statTitle.text = dbViewModel.getTotalHoursOf(PersonalStatus.TO_SEE)
        }
        movieSection.liveData = dbViewModel.movies
        personSection.liveData = dbViewModel.persons

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}