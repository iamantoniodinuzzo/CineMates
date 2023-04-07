package com.example.cinemates.view.ui.details.actor

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.RecyclerView
import com.example.cinemates.R
import com.example.cinemates.databinding.FragmentActorAboutBinding
import com.indisparte.horizontalchipview.HorizontalChipView
import com.example.cinemates.view.ui.adapter.PosterAdapter
import kotlinx.android.synthetic.main.fragment_actor_about.*
import kotlinx.coroutines.launch

/**
 * @author Antonio Di Nuzzo (Indisparte)
 */
private val TAG = ActorAboutFragment::class.simpleName

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
            val chipsGroupKnowAs: HorizontalChipView<String> =
                view.findViewById(R.id.chipGroupKnownAs)

            enableInnerScrollViewPager(images)

            chipsGroupKnowAs.onChipClicked = { knownAsName ->
                Toast.makeText(
                    requireContext(),
                    "Soon - Pronunciation of $knownAsName",
                    Toast.LENGTH_SHORT
                ).show()
            }
            viewLifecycleOwner.lifecycleScope.launch {

                viewModel.actor.collect { selectedPerson ->
                    binding.person = selectedPerson
                    if (selectedPerson != null) {
                        chipGroupKnownAs.setChipsList(
                            selectedPerson.also_known_as,
                            textGetter = { it }
                        )
                    }
                }
                viewModel.images.collect { images ->
                    posterAdapter.updateItems(images)
                }
            }

        }

    }

    // Disable ViewPager2 from intercepting touch events of RecyclerView
    private fun enableInnerScrollViewPager(recyclerView: RecyclerView) {
        recyclerView.addOnItemTouchListener(object : RecyclerView.SimpleOnItemTouchListener() {
            override fun onInterceptTouchEvent(rv: RecyclerView, e: MotionEvent): Boolean {
                val action = e.actionMasked
                when (action) {
                    MotionEvent.ACTION_DOWN -> {
                        // Disallow ViewPager2 to intercept touch events of RecyclerView
                        rv.parent.requestDisallowInterceptTouchEvent(true)
                    }
                }
                return false
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}