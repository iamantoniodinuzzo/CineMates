package com.example.cinemates.ui.adapter


/**
 * @author Antonio Di Nuzzo (Indisparte)
 */
import androidx.databinding.ViewDataBinding
import androidx.navigation.Navigation
import com.example.cinemates.NavGraphDirections
import com.example.cinemates.R
import com.example.cinemates.common.DoubleViewSizeAdapter
import com.example.cinemates.databinding.ListItemCrewLongBinding
import com.example.cinemates.databinding.ListItemPersonSmallBinding
import com.example.cinemates.domain.model.credits.Crew

class CrewAdapter :
    DoubleViewSizeAdapter<Crew, ListItemCrewLongBinding, ListItemPersonSmallBinding>(
        R.layout.list_item_crew_long,
        R.layout.list_item_person_small,
    ) {


    private fun navigateToDetails(binding: ViewDataBinding, item: Crew) {
        binding.root.setOnClickListener { view ->
            val action = NavGraphDirections.actionGlobalActorDetailsFragment(item)
            Navigation.findNavController(view).navigate(action)
        }

    }

    override fun onBindLongItem(binding: ListItemCrewLongBinding, item: Crew) {
        binding.crew = item
        navigateToDetails(binding, item)
    }

    override fun onBindSmallItem(binding: ListItemPersonSmallBinding, item: Crew) {
        binding.person = item
        navigateToDetails(binding, item)

    }


}
