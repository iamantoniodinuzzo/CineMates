package com.indisparte.movie_details.fragments.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.indisparte.movie_details.databinding.BottomDialogManageMediaBinding
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

/**
 *@author Antonio Di Nuzzo
 */
@AndroidEntryPoint
class ManageMediaBottomDialog : BottomSheetDialogFragment() {
    private val LOG = Timber.tag(ManageMediaBottomDialog::class.java.simpleName)
    private val manageMediaViewModel: ManageMediaViewModel by viewModels()
    private val args: ManageMediaBottomDialogArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        val binding = BottomDialogManageMediaBinding.inflate(inflater, container, false)
        val view = binding.root

        val currentMovie = args.movie


        binding.btnWatch.setOnClickListener {
            manageMediaViewModel.setMovieAsSeen(currentMovie)
        }

        binding.btnLike.setOnClickListener {
            manageMediaViewModel.setMovieAsFavorite(currentMovie)
        }

        binding.btnWatchlist.setOnClickListener {
            manageMediaViewModel.setMovieAsToSee(currentMovie)
        }

        // Aggiorna l'UI in base allo stato del media (visto, preferito, da vedere)

        return view
    }
}