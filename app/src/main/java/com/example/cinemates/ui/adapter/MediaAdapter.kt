package com.example.cinemates.ui.adapter


/**
 * @author Antonio Di Nuzzo (Indisparte)
 */
import androidx.databinding.ViewDataBinding
import androidx.navigation.Navigation
import com.example.cinemates.NavGraphDirections
import com.example.cinemates.R
import com.example.cinemates.common.DoubleViewSizeAdapter
import com.example.cinemates.databinding.ListItemMediaLongBinding
import com.example.cinemates.databinding.ListItemMediaSmallBinding
import com.example.cinemates.domain.model.Media
import com.example.cinemates.util.MediaType

class MediaAdapter :
    DoubleViewSizeAdapter<Media, ListItemMediaLongBinding, ListItemMediaSmallBinding>(
        R.layout.list_item_media_long,
        R.layout.list_item_media_small,
    ) {


    override fun onBindLongItem(binding: ListItemMediaLongBinding, item: Media) {
        binding.media = item
        navigateToDetails(binding, item)
    }

    override fun onBindSmallItem(binding: ListItemMediaSmallBinding, item: Media) {
        binding.media = item
        navigateToDetails(binding, item)
    }

    private fun navigateToDetails(binding: ViewDataBinding, item: Media) {
        binding.root.setOnClickListener { view ->
            val action = when (item.mediaType) {
                MediaType.MOVIE -> NavGraphDirections.actionGlobalMovieDetailsFragment(item)
                MediaType.TV -> NavGraphDirections.actionGlobalTvDetailsFragment(item)
                else -> {
                    throw UnsupportedOperationException()
                }
            }
            Navigation.findNavController(view).navigate(action)
        }

    }
}
