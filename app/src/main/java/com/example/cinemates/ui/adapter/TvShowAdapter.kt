package com.example.cinemates.ui.adapter


/**
 * @author Antonio Di Nuzzo (Indisparte)
 */
import androidx.databinding.ViewDataBinding
import androidx.navigation.Navigation
import com.example.cinemates.NavGraphDirections
import com.example.cinemates.R
import com.example.cinemates.databinding.ListItemMediaLongBinding
import com.example.cinemates.databinding.ListItemTvLongBinding
import com.example.cinemates.databinding.ListItemTvSmallBinding
import com.example.cinemates.model.TvShow

class TvShowAdapter :
    DoubleViewSizeAdapter<TvShow, ListItemMediaLongBinding, ListItemTvSmallBinding>(
        R.layout.list_item_media_long,
        R.layout.list_item_tv_small,
        emptyList()
    ) {


    override fun onBindLongItem(binding: ListItemMediaLongBinding, item: TvShow) {
        binding.media = item
        navigateToDetails(binding, item)
    }

    override fun onBindSmallItem(binding: ListItemTvSmallBinding, item: TvShow) {
        binding.tv = item
        navigateToDetails(binding, item)
    }

    private fun navigateToDetails(binding: ViewDataBinding, item: TvShow) {
        binding.root.setOnClickListener { view ->
            val action = NavGraphDirections.actionGlobalTvDetailsFragment(item)
            Navigation.findNavController(view).navigate(action)
        }

    }
}
