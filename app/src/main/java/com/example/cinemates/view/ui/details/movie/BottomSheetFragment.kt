package com.example.cinemates.view.ui.details.movie

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import com.example.cinemates.databinding.FragmentBottomsheetBinding
import com.example.cinemates.model.data.PersonalStatus
import com.example.cinemates.view.viewmodel.DbMovieViewModel
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

/**
 * @author Antonio Di Nuzzo
 * Created 26/07/2022 at 09:01
 */
class BottomSheetFragment : BottomSheetDialogFragment() {
    private var _binding: FragmentBottomsheetBinding? = null
    private val binding: FragmentBottomsheetBinding
        get() = _binding!!
    private val dbViewModel: DbMovieViewModel by activityViewModels()
    private val movieViewModel: MovieDetailsViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBottomsheetBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState) as BottomSheetDialog
        _binding = FragmentBottomsheetBinding.inflate(layoutInflater)
        dialog.setContentView(binding.root)

        return dialog
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            //listen selected movie changes for updating UI
            movieViewModel.selectedMovie.observe(viewLifecycleOwner) { movie ->
                setMovie(movie)
            }

            favoriteBtn.setOnClickListener {
                movieViewModel.setFavorite()
                val result = dbViewModel.setAsFavorite(movieViewModel.selectedMovie.value!!)

                makeResultToast(result, "Favorites")

                dismiss()
            }

            watchlistBtn.setOnClickListener {
                movieViewModel.setPersonalStatus(PersonalStatus.TO_SEE)
                val result = dbViewModel.updatePersonalStatus(movieViewModel.selectedMovie.value!!, PersonalStatus.TO_SEE)

                makeResultToast(result, "Watch list")

                dismiss()
            }

            watchedBtn.setOnClickListener {
                movieViewModel.setPersonalStatus(PersonalStatus.SEEN)
                val result = dbViewModel.updatePersonalStatus(movieViewModel.selectedMovie.value!!, PersonalStatus.SEEN)

                makeResultToast(result, "Watched list")

                dismiss()
            }


        }
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