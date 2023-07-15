package com.indisparte.cinemates.ui.details.actor

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import com.indisparte.cinemates.R
import com.indisparte.cinemates.databinding.FragmentActorAboutBinding
import com.indisparte.horizontalchipview.HorizontalChipView
import com.indisparte.ui.fragment.BaseFragment
import kotlinx.coroutines.launch

/**
 * @author Antonio Di Nuzzo (Indisparte)
 */
private val TAG = ActorAboutFragment::class.simpleName

class ActorAboutFragment : BaseFragment<FragmentActorAboutBinding>() {

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentActorAboutBinding
        get() = FragmentActorAboutBinding::inflate

    private val viewModel = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
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