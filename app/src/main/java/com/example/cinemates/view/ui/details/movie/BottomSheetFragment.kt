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
import com.example.cinemates.view.viewmodel.DbViewModel
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
    private val dbViewModel: DbViewModel by activityViewModels()
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
            favoriteBtn.setOnClickListener {
                movieViewModel.setFavorite()
                if (dbViewModel.setAsFavorite(movieViewModel.selectedMovie.value!!)) {
                    Toast.makeText(context, "Added to favorite", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(context, "Removed from favorite", Toast.LENGTH_SHORT).show()
                }
                dismiss()
            }

            watchlistBtn.setOnClickListener {
                movieViewModel.setPersonalStatus(PersonalStatus.TO_SEE)
                dbViewModel.updateMovie(movieViewModel.selectedMovie.value!!)
                dismiss()
            }

            watchedBtn.setOnClickListener {
                movieViewModel.setPersonalStatus(PersonalStatus.SEEN)
                dbViewModel.updateMovie(movieViewModel.selectedMovie.value!!)
                dismiss()
            }

            //listen selected movie changes for updating UI
            movieViewModel.selectedMovie.observe(viewLifecycleOwner) { movie ->
                setMovie(movie)
            }
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}