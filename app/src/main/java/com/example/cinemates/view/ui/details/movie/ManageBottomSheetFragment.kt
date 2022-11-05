package com.example.cinemates.view.ui.details.movie

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import com.example.cinemates.databinding.FragmentManageBottomsheetBinding
import com.example.cinemates.model.entities.Movie
import com.example.cinemates.model.entities.PersonalStatus
import com.example.cinemates.view.viewmodel.DbMovieViewModel
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

/**
 * @author Antonio Di Nuzzo
 * Created 26/07/2022 at 09:01
 */
class ManageBottomSheetFragment : BottomSheetDialogFragment() {
    private var _binding: FragmentManageBottomsheetBinding? = null
    private val binding: FragmentManageBottomsheetBinding
        get() = _binding!!
    private val dbViewModel: DbMovieViewModel by activityViewModels()
    private val movieViewModel: MovieDetailsViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentManageBottomsheetBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState) as BottomSheetDialog
        _binding = FragmentManageBottomsheetBinding.inflate(layoutInflater)
        dialog.setContentView(binding.root)

        return dialog
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            //listen selected movie changes for updating UI
            val selectedMovie = getConsistentMovie()
            movie = selectedMovie

            favoriteBtn.setOnClickListener {
                val result = dbViewModel.setAsFavorite(selectedMovie)

                makeResultToast(result, "Favorites")

                dismiss()
            }

            watchlistBtn.setOnClickListener {
                val result = dbViewModel.updatePersonalStatus(selectedMovie, PersonalStatus.TO_SEE)

                makeResultToast(result, "Watch list")

                dismiss()
            }

            watchedBtn.setOnClickListener {
                val result = dbViewModel.updatePersonalStatus(selectedMovie, PersonalStatus.SEEN)

                makeResultToast(result, "Watched list")

                dismiss()
            }


        }
    }

    /*
     * Takes the movie indicated by the id, within the database.
     * If the movie is not present then returns the movie using the one selected in the MovieDetailsViewModel
     */
    private fun getConsistentMovie():Movie {
        val selectedMovie = movieViewModel.selectedMovie.value!!
        return selectedMovie.let { dbViewModel.getMovie(it.id) } ?: selectedMovie
    }

    private fun makeResultToast(result: Boolean, what: String) {
        val text = if (result) "Added to"
        else "Removed from"
        Toast.makeText(context, "$text $what", Toast.LENGTH_SHORT).show()
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}