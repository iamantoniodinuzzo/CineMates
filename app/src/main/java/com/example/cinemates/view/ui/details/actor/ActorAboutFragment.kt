package com.example.cinemates.view.ui.details.actor

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.cinemates.adapter.BaseAdapter
import com.example.cinemates.databinding.FragmentActorAboutBinding
import com.example.cinemates.databinding.ListItemPosterBinding
import com.example.cinemates.model.entities.Image
import com.example.cinemates.util.inflater

/**
 * @author Antonio Di Nuzzo (Indisparte)
 */
class ActorAboutFragment : Fragment() {
    private var _binding: FragmentActorAboutBinding? = null
    private val binding: FragmentActorAboutBinding
        get() = _binding!!
    private val viewModel: ActorDetailsViewModel by activityViewModels()
    private lateinit var posterAdapter: BaseAdapter<Image, com.example.cinemates.databinding.ListItemPosterBinding>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        posterAdapter = BaseAdapter(
            expressionOnCreateViewHolder = { viewGroup ->
                viewGroup inflater ListItemPosterBinding::inflate
            },
            expressionViewHolderBinding = { poster, binding ->
                binding.apply {
                    path = poster.file_path
                    root.setOnClickListener {
                        //TODO open download page
                    }
                }
            })
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
        }
        viewModel.apply {
            actor.observe(viewLifecycleOwner) { selectedPerson ->
                binding.person = selectedPerson
            }
            images.observe(viewLifecycleOwner) { images ->
                posterAdapter.dataList = images
            }
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}