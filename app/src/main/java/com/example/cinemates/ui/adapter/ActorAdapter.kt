package com.example.cinemates.ui.adapter


/**
 * @author Antonio Di Nuzzo (Indisparte)
 */
import androidx.databinding.ViewDataBinding
import androidx.navigation.Navigation
import com.example.cinemates.NavGraphDirections
import com.example.cinemates.R
import com.example.cinemates.common.DoubleViewSizeAdapter
import com.example.cinemates.databinding.ListItemCastLongBinding
import com.example.cinemates.databinding.ListItemPersonSmallBinding
import com.example.cinemates.domain.model.credits.Cast

class ActorAdapter :
    DoubleViewSizeAdapter<Cast, ListItemCastLongBinding, ListItemPersonSmallBinding>(
        R.layout.list_item_cast_long,
        R.layout.list_item_person_small,
    ) {


    private fun navigateToDetails(binding: ViewDataBinding, item: Cast) {
        binding.root.setOnClickListener { view ->
            val action = NavGraphDirections.actionGlobalActorDetailsFragment(item)
            Navigation.findNavController(view).navigate(action)
        }

    }

    override fun onBindLongItem(binding: ListItemCastLongBinding, item: Cast) {
        binding.actor = item
        navigateToDetails(binding, item)
    }

    override fun onBindSmallItem(binding: ListItemPersonSmallBinding, item: Cast) {
        binding.person = item
        navigateToDetails(binding, item)
    }
}
