package com.indisparte.movie_details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.RecyclerView
import com.indisparte.model.entity.Genre
import com.indisparte.movie_details.databinding.FragmentMovieAboutBinding
import com.indisparte.ui.custom_view.HorizontalChipView
import com.indisparte.ui.fragment.BaseFragment

class MovieAboutFragment : BaseFragment<FragmentMovieAboutBinding>() {

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentMovieAboutBinding
        get() = FragmentMovieAboutBinding::inflate

    private val viewModel: MovieDetailsViewModel by activityViewModels()
    private lateinit var collectionDialog: CollectionDialog


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {

            val chipGroupGenres: HorizontalChipView<Genre> =
                view.findViewById<HorizontalChipView<Genre>>(R.id.chiGroupGenres)


            chipGroupGenres.onChipClicked = { genre ->
                // TODO: Implement search on click of genre, open search view
                Toast.makeText(
                    requireContext(),
                    "Soon - Search ${genre.name} genre",
                    Toast.LENGTH_SHORT
                ).show()
            }


        }


    }

    //TODO automatize this method with data binding
    private fun FragmentMovieAboutBinding.showTrailerSection(isNotEmpty: Boolean) {
        trailerTitle.isVisible = isNotEmpty
        binding.trailers.isVisible = isNotEmpty
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