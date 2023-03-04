package com.example.cinemates.view.ui.details.actor

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.cinemates.R
import com.example.cinemates.databinding.FragmentActorAboutBinding
import com.indisparte.horizontalchipview.HorizontalChipView
import com.example.cinemates.view.ui.adapter.PosterAdapter
import kotlinx.android.synthetic.main.fragment_actor_about.*

/**
 * @author Antonio Di Nuzzo (Indisparte)
 */
class ActorAboutFragment : Fragment() {
    private var _binding: FragmentActorAboutBinding? = null
    private val binding: FragmentActorAboutBinding
        get() = _binding!!
    private val viewModel: ActorDetailsViewModel by activityViewModels()
    private lateinit var posterAdapter: PosterAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        posterAdapter = PosterAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentActorAboutBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            binding.images.adapter = posterAdapter
            binding.images.setEmptyView(binding.emptyViewRecommended.root)
            val chipsGroupKnowAs: com.indisparte.horizontalchipview.HorizontalChipView<String> =
                view.findViewById(R.id.chipGroupKnownAs)

            chipsGroupKnowAs.onChipClicked = { knownAsName ->
                Toast.makeText(
                    requireContext(),
                    "Soon - Pronunciation of $knownAsName",
                    Toast.LENGTH_SHORT
                ).show()
            }
            viewModel.actor.observe(viewLifecycleOwner) { selectedPerson ->
                binding.person = selectedPerson
                chipGroupKnownAs.setChipsList(
                    selectedPerson.also_known_as,
                    textGetter = { it }
                )
            }
            viewModel.images.observe(viewLifecycleOwner) { images ->
                posterAdapter.updateItems(images)
            }
        }

    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}