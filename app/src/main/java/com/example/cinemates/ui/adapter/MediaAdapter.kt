package com.example.cinemates.ui.adapter


import android.view.View
import androidx.navigation.Navigation
import com.example.cinemates.NavGraphDirections
import com.example.cinemates.R
import com.example.cinemates.common.DoubleViewSizeAdapter
import com.example.cinemates.databinding.ListItemMediaLongBinding
import com.example.cinemates.databinding.ListItemMediaSmallBinding
import com.example.cinemates.domain.model.common.Media
import com.example.cinemates.util.MediaType


/**
 * Represents an adapter for any episodeGroupType of [MediaType].
 * @param view An optional parameter,
 * but one that becomes necessary if the adapter is to be attached to a recyclerview of a certain dialog.
 * In this case this parameter must contain the view in which the dialog will be displayed
 * @author Antonio Di Nuzzo (Indisparte)
 */
class MediaAdapter(private val view: View? = null) :
    DoubleViewSizeAdapter<Media, ListItemMediaLongBinding, ListItemMediaSmallBinding>(
        R.layout.list_item_media_long,
        R.layout.list_item_media_small,
    ) {


    override fun onBindLongItem(binding: ListItemMediaLongBinding, item: Media) {
        binding.media = item
        binding.root.setOnClickListener {
            navigateToDetails(it, item)
        }
    }

    override fun onBindSmallItem(binding: ListItemMediaSmallBinding, item: Media) {
        binding.media = item
        binding.root.setOnClickListener {
            navigateToDetails(it, item)
        }
    }

    private fun navigateToDetails(view: View, item: Media) {
        val action = when (item.mediaType) {
            MediaType.MOVIE -> NavGraphDirections.actionGlobalMovieDetailsFragment(item)
            MediaType.TV -> NavGraphDirections.actionGlobalTvDetailsFragment(item)
        }

        /*
         A distinction is made between these two views in that if this adapter is attached to a recyclerview present in a dialog,
         the app will crash by not finding the NavController.
         This happens because the dialogs are presented in a view that is detached from the main view,
         losing the navigation references.
         So by passing as an argument to the class the view in which the adapter is instantiated, its reference is not lost.
         */
        this@MediaAdapter.view?.let {
            Navigation.findNavController(it).navigate(action)
        } ?: Navigation.findNavController(view).navigate(action)

    }
}
