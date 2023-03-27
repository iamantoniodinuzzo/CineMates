package com.example.cinemates.view.ui.details.movie

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.cinemates.databinding.FragmentManageBottomsheetBinding
import com.example.cinemates.model.Movie
import com.example.cinemates.model.PersonalStatus
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

/**
 * @author Antonio Di Nuzzo
 * Created 26/07/2022 at 09:01
 */
class ManageBottomSheetFragment : BottomSheetDialogFragment() {
    private var _binding: FragmentManageBottomsheetBinding? = null
    private val binding: FragmentManageBottomsheetBinding
        get() = _binding!!
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
            /*Takes the movie indicated by the id, within the database.
            * If the movie is not present then returns the movie using the one selected in the [MovieDetailsViewModel]
             */
            viewLifecycleOwner.lifecycleScope.launch {
                movieViewModel.selectedMovie.collectLatest {
                    if (it != null) {
                        movie = it
                    }
                }
            }

            //listen selected movie changes for updating UI
            favoriteBtn.setOnClickListener {
                /*  val result = dbViewModel.setAsFavorite(movie)

                  makeResultToast(result, "Favorites")*/

//                dismiss()
            }

            watchlistBtn.setOnClickListener {

//                makeResultToast(result, "Watch list")

//                dismiss()
            }

            watchedBtn.setOnClickListener {

//                makeResultToast(result, "Watched list")

//                dismiss()
            }


        }
    }


    /*
     * Create a toast which, based on a boolean value outputs a user-specific message
     */
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