package com.example.cinemates.view.ui.details.actor

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnticipateOvershootInterpolator
import android.widget.Toast
import androidx.core.view.doOnPreDraw
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.interpolator.view.animation.FastOutSlowInInterpolator
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.example.cinemates.R
import com.example.cinemates.adapter.MovieAdapter
import com.example.cinemates.databinding.FragmentActorDetailsBinding
import com.example.cinemates.view.viewmodel.DbPersonViewModel
import com.example.cinemates.util.getLong
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.transition.MaterialFadeThrough
import com.google.android.material.transition.MaterialSharedAxis

class ActorDetailsFragment : Fragment() {
    private var _binding: FragmentActorDetailsBinding? = null
    private val binding: FragmentActorDetailsBinding
        get() = _binding!!
    private lateinit var adapter: MovieAdapter
    private val dbViewModel: DbPersonViewModel by activityViewModels()
    private val viewModel: ActorDetailsViewModel by activityViewModels()
    private val args: ActorDetailsFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        adapter = MovieAdapter()
        enterTransition = MaterialSharedAxis(MaterialSharedAxis.Z, true).apply {
            interpolator = AnticipateOvershootInterpolator()
            duration = resources.getLong(R.integer.material_motion_duration_medium_2)
        }
        reenterTransition = MaterialSharedAxis(MaterialSharedAxis.Z, false).apply {
            interpolator = AnticipateOvershootInterpolator()
            duration = resources.getLong(R.integer.material_motion_duration_long_2)
        }
        returnTransition = MaterialFadeThrough()
        exitTransition = MaterialFadeThrough().apply {
            interpolator = FastOutSlowInInterpolator()
            duration = resources.getLong(R.integer.material_motion_duration_short_1)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentActorDetailsBinding.inflate(
            inflater,
            container,
            false
        )

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        postponeEnterTransition()
        view.doOnPreDraw { startPostponedEnterTransition() }
        viewModel.apply {
            movies.observe(viewLifecycleOwner) { moviesByActor ->
                adapter.addItems(moviesByActor)
            }
            setActorDetails(args.person.id)
        }

        binding.apply {
            customizeFab(fab)
            fab.setOnClickListener {
                updateThisPerson(fab)
            }
            recyclerView.adapter = adapter
            viewModel.actor.observe(viewLifecycleOwner) { selectedPerson ->
                person = selectedPerson
            }
            toolbar.setNavigationOnClickListener { view ->
                findNavController(
                    view
                ).navigateUp()
            }

        }

    }

    private fun updateThisPerson(fab: FloatingActionButton) {
        if (dbViewModel.setAsFavorite(args.person)) {
            Toast.makeText(context, "Added to favorites", Toast.LENGTH_SHORT).show()
            fab.setImageResource(R.drawable.ic_favorite_filled)
        } else {
            Toast.makeText(context, "Removed from favorites", Toast.LENGTH_SHORT).show()
            fab.setImageResource(R.drawable.ic_favorite_border)
        }
    }

    private fun customizeFab(fab: FloatingActionButton) {
        if (dbViewModel.isMyFavoritePerson(args.person)) {
            fab.setImageResource(R.drawable.ic_favorite_filled)
        } else {
            fab.setImageResource(R.drawable.ic_favorite_border)
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
