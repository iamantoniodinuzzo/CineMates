package com.indisparte.movie_details.fragments.dialog

import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.indisparte.movie_data.Movie
import com.indisparte.movie_details.databinding.BottomDialogManageMediaBinding
import com.indisparte.navigation.NavigationFlow
import com.indisparte.navigation.ToFlowNavigable
import com.indisparte.network.whenResources
import com.indisparte.ui.adapter.MediaListAdapter
import com.indisparte.util.extension.collectIn
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber
import kotlin.properties.Delegates

internal fun interface BottomDialogListener {
    fun onBottomDialogClosed(movieUpdated: Movie)
}

/**
 *@author Antonio Di Nuzzo
 */
@AndroidEntryPoint
class ManageMediaBottomDialog : BottomSheetDialogFragment() {
    private var _binding: BottomDialogManageMediaBinding? = null
    private val binding: BottomDialogManageMediaBinding
        get() = _binding!!
    private val LOG = Timber.tag(ManageMediaBottomDialog::class.java.simpleName)
    private val manageMediaViewModel: ManageMediaViewModel by viewModels()
    private val args: ManageMediaBottomDialogArgs by navArgs()
    private var listener: BottomDialogListener? = null
    private lateinit var currentMovie: Movie
    private var customListsNextIndex by Delegates.notNull<Int>()
    private val mediaListAdapter: MediaListAdapter by lazy {
        MediaListAdapter()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        listener = requireActivity() as? BottomDialogListener

        mediaListAdapter.setOnItemClickListener { mediaList ->
            manageMediaViewModel.addMovieToList(mediaList.id, currentMovie, customListsNextIndex)
        }
        binding.personalLists.adapter = mediaListAdapter

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = BottomDialogManageMediaBinding.inflate(inflater, container, false)
        val view = binding.root

        currentMovie = args.movie

        // Aggiorna l'UI in base allo stato del media (visto, preferito, da vedere)
        updateUI(currentMovie)

        binding.btnWatch.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                manageMediaViewModel.setMovieAsSeen(currentMovie)
            } else {
                manageMediaViewModel.removeMovieFromSeen(currentMovie)
            }
            currentMovie.isSeen = isChecked
            updateUI(currentMovie)
        }

        binding.btnLike.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                manageMediaViewModel.setMovieAsFavorite(currentMovie)
            } else {
                manageMediaViewModel.removeMovieFromFavorite(currentMovie)
            }
            currentMovie.isFavorite = isChecked
            updateUI(currentMovie)

        }

        binding.btnWatchlist.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                manageMediaViewModel.setMovieAsToSee(currentMovie)

            } else {
                manageMediaViewModel.removeMovieFromToSee(currentMovie)
            }
            currentMovie.isToSee = isChecked
            updateUI(currentMovie)
        }

        binding.createListBtn.setOnClickListener {
            val activity = requireActivity()
            if (activity is ToFlowNavigable) {
                activity.navigateToFlow(NavigationFlow.MediaListCreationFlow)
            }
        }

        manageMediaViewModel.list.collectIn(viewLifecycleOwner) { result ->
            result.whenResources(
                onSuccess = { mediaLists ->
                    LOG.d("Media lists: $mediaLists")
                    customListsNextIndex = mediaLists.size + 1
                    mediaListAdapter.submitList(mediaLists)

                },
                onError = { exception ->
                    LOG.e(exception)
                },
                onLoading = {
                    LOG.i("Loading private lists...")
                }
            )

        }

        manageMediaViewModel.movieListInsertion.collectIn(viewLifecycleOwner) { result ->
            result?.let {
                val message = if (it) {
                    "Inserito"
                } else {
                    "Non inserito"
                }

                Toast.makeText(requireContext(), "Film $message nella lista", Toast.LENGTH_SHORT)
                    .show()
            }
        }


        return view
    }

    override fun onDismiss(dialog: DialogInterface) {
        super.onDismiss(dialog)
        // Chiamata al callback quando il BottomDialogFragment viene chiuso
        listener?.onBottomDialogClosed(currentMovie)
    }

    private fun updateUI(currentMovie: Movie) {
        binding.btnLike.isChecked = currentMovie.isFavorite
        binding.btnWatch.isChecked = currentMovie.isSeen
        binding.btnWatchlist.isChecked = currentMovie.isToSee
        binding.executePendingBindings()
        binding.personalLists.setEmptyStateSubtitle("Nessuna lista qui")
    }
}