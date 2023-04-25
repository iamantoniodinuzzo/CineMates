package com.example.cinemates.ui.adapter


import android.view.View
import android.widget.Toast
import com.example.cinemates.R
import com.example.cinemates.common.SingleViewAdapter
import com.example.cinemates.databinding.ListItemSeasonLongBinding
import com.example.cinemates.domain.model.Season


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
        Toast.makeText(view.context, "Soon", Toast.LENGTH_SHORT).show()
        /*  val action = //todo
          Navigation.findNavController(view).navigate(action)*/

    }
}
