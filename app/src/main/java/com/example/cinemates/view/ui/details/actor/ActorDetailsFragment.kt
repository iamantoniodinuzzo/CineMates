package com.example.cinemates.view.ui.details.actor

import androidx.navigation.Navigation.findNavController
import com.example.cinemates.adapter.ItemsRecyclerViewAdapter
import com.example.cinemates.view.viewmodel.DbViewModel
import android.os.Bundle
import com.example.cinemates.util.ViewSize
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.cinemates.R
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.navArgs
import com.example.cinemates.databinding.FragmentActorDetailsBinding
import com.example.cinemates.model.data.Movie
import com.example.cinemates.model.data.Person

class ActorDetailsFragment : Fragment() {
    private lateinit var mBinding: FragmentActorDetailsBinding
    private lateinit var mAdapter: ItemsRecyclerViewAdapter<Movie>
    private val dbViewModel: DbViewModel by activityViewModels()
    private val viewModel: ActorDetailsViewModel by activityViewModels()
    private lateinit var person: Person
    private val args: ActorDetailsFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        person = args.person
        mAdapter = ItemsRecyclerViewAdapter(ViewSize.SMALL)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mBinding = FragmentActorDetailsBinding.inflate(
            inflater,
            container,
            false
        )
        viewModel.setActorDetails(person.id)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.apply {
            movies.observe(viewLifecycleOwner) { moviesByActor ->
                mAdapter.addItems(moviesByActor.toMutableList())
            }

        }
        mBinding.apply {
            if (dbViewModel.getPerson(person) != null) fab.setImageDrawable(
                requireActivity().getDrawable(
                    R.drawable.ic_baseline_favorite_24
                )
            ) else fab.setImageDrawable(
                requireActivity().getDrawable(R.drawable.ic_baseline_favorite_border_24)
            )
            recyclerView.adapter = mAdapter
            viewModel.actor.observe(viewLifecycleOwner) { selectedPerson ->
                person = selectedPerson
            }
            toolbar.setNavigationOnClickListener { view ->
                findNavController(
                    view
                ).navigateUp()
            }
            fab.setOnClickListener {
                person!!.setFavorite()
                if (dbViewModel.getPerson(person) != null) { //it was already into favorite
                    dbViewModel.delete(person) //should delete it
                    fab.setImageDrawable(requireActivity().getDrawable(R.drawable.ic_baseline_favorite_border_24))
                    Toast.makeText(context, "Removed from favorite", Toast.LENGTH_SHORT).show()
                } else {
                    dbViewModel.insert(person)
                    fab.setImageDrawable(requireActivity().getDrawable(R.drawable.ic_baseline_favorite_24))
                    Toast.makeText(context, "Added to favorite", Toast.LENGTH_SHORT).show()
                }
            }
        }

    }
}