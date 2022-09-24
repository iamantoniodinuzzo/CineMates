package com.example.cinemates.view.ui.details.actor

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.example.cinemates.R
import com.example.cinemates.adapter.ItemsRecyclerViewAdapter
import com.example.cinemates.databinding.FragmentActorDetailsBinding
import com.example.cinemates.model.data.Movie
import com.example.cinemates.util.ViewSize
import com.example.cinemates.view.viewmodel.DbViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton

class ActorDetailsFragment : Fragment() {
    private var _binding: FragmentActorDetailsBinding? = null
    private val binding: FragmentActorDetailsBinding
        get() = _binding!!
    private lateinit var adapter: ItemsRecyclerViewAdapter<Movie>
    private val dbViewModel: DbViewModel by activityViewModels()
    private val viewModel: ActorDetailsViewModel by activityViewModels()
    private val args: ActorDetailsFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        adapter = ItemsRecyclerViewAdapter(ViewSize.SMALL)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
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
        viewModel.apply {
            movies.observe(viewLifecycleOwner) { moviesByActor ->
                adapter.addItems(moviesByActor.toMutableList())
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
