package com.example.cinemates.ui.adapter


import android.view.View
import androidx.navigation.Navigation
import com.example.cinemates.R
import com.example.cinemates.common.SingleViewAdapter
import com.example.cinemates.databinding.ListItemSeasonLongBinding
import com.example.cinemates.domain.model.tv.Season
import com.example.cinemates.ui.details.tvShow.TvDetailsContainerFragmentDirections


/**
 * @author Antonio Di Nuzzo (Indisparte)
 */
class SeasonAdapter :
    SingleViewAdapter<Season, ListItemSeasonLongBinding>(
        R.layout.list_item_season_long,
    ) {


    override fun onBindItem(binding: ListItemSeasonLongBinding, item: Season) {
        binding.season = item
        binding.root.setOnClickListener {
            navigateToDetails(it, item)
        }
    }

    private fun navigateToDetails(view: View, item: Season) {
        val action =
            TvDetailsContainerFragmentDirections.actionTvDetailsFragmentToSeasonDetailsFragment(item)
        Navigation.findNavController(view).navigate(action)

    }
}
