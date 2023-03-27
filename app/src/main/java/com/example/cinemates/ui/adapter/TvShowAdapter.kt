package com.example.cinemates.ui.adapter


/**
 * @author Antonio Di Nuzzo (Indisparte)
 */
import androidx.databinding.ViewDataBinding
import androidx.navigation.Navigation
import com.example.cinemates.NavGraphDirections
import com.example.cinemates.R
import com.example.cinemates.databinding.ListItemTvLongBinding
import com.example.cinemates.databinding.ListItemTvSmallBinding
import com.example.cinemates.model.TvShow

class TvShowAdapter :
    MultipleViewSizeAdapter<TvShow, ListItemTvLongBinding, ListItemTvSmallBinding>(
        R.layout.list_item_tv_long,
        R.layout.list_item_tv_small,
        emptyList()
    ) {


    override fun onBindLongItem(binding: ListItemTvLongBinding, item: TvShow) {
        binding.tv = item
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
