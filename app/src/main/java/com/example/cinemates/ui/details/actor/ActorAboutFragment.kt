package com.example.cinemates.ui.details.actor

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import com.example.cinemates.R
import com.example.cinemates.common.BaseFragment
import com.example.cinemates.databinding.FragmentActorAboutBinding
import com.example.cinemates.ui.adapter.ImageAdapter
import com.indisparte.horizontalchipview.HorizontalChipView
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

/**
 * @author Antonio Di Nuzzo (Indisparte)
 */
private val TAG = ActorAboutFragment::class.simpleName

class ActorAboutFragment : BaseFragment<FragmentActorAboutBinding>() {

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentActorAboutBinding
        get() = FragmentActorAboutBinding::inflate

    private val viewModel: ActorDetailsViewModel by activityViewModels()
    private lateinit var posterAdapter: ImageAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        posterAdapter = ImageAdapter()
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            images.adapter = posterAdapter
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
            viewLifecycleOwner.lifecycleScope.launchWhenCreated {

                launch {
                    viewModel.personDetails.collect { selectedPerson ->
                        binding.person = selectedPerson
                        if (selectedPerson != null) {
                            chipGroupKnownAs.setChipsList(
                                selectedPerson.alsoKnownAs,
                                textGetter = { it }
                            )
                        }
                    }
                }
                launch {
                    viewModel.images.collect { images ->
                        posterAdapter.items = images
                    }
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


}