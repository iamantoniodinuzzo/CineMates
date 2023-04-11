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
import com.example.cinemates.model.TvShow

class TvShowAdapter :
    DoubleViewSizeAdapter<TvShow, ListItemMediaLongBinding, ListItemMediaSmallBinding>(
        R.layout.list_item_media_long,
        R.layout.list_item_media_small,
    ) {


    override fun onBindLongItem(binding: ListItemMediaLongBinding, item: TvShow) {
        binding.media = item
        navigateToDetails(binding, item)
    }

    override fun onBindSmallItem(binding: ListItemMediaSmallBinding, item: TvShow) {
        binding.media = item
        navigateToDetails(binding, item)
    }

    private fun navigateToDetails(binding: ViewDataBinding, item: TvShow) {
        binding.root.setOnClickListener { view ->
            val action = NavGraphDirections.actionGlobalTvDetailsFragment(item)
            Navigation.findNavController(view).navigate(action)
        }

    }
}
