package com.example.cinemates.ui.adapter


/**
 * @author Antonio Di Nuzzo (Indisparte)
 */
import androidx.databinding.ViewDataBinding
import androidx.navigation.Navigation
import com.example.cinemates.NavGraphDirections
import com.example.cinemates.R
import com.example.cinemates.databinding.ListItemPersonLongBinding
import com.example.cinemates.databinding.ListItemPersonSmallBinding
import com.example.cinemates.model.Cast
import com.example.cinemates.model.Person

class PersonAdapter :
    DoubleViewSizeAdapter<Person, ListItemPersonLongBinding, ListItemPersonSmallBinding>(
        R.layout.list_item_person_long, R.layout.list_item_person_small,
    ) {

    override fun onBindLongItem(binding: ListItemPersonLongBinding, item: Person) {
        binding.actor = item as Cast
        navigateToDetails(binding, item)
    }

    override fun onBindSmallItem(binding: ListItemPersonSmallBinding, item: Person) {
        binding.person = item
        navigateToDetails(binding, item)
    }

    private fun navigateToDetails(binding: ViewDataBinding, item: Person) {
        binding.root.setOnClickListener { view ->
            val action = NavGraphDirections.actionGlobalActorDetailsFragment(item)
            Navigation.findNavController(view).navigate(action)
        }

    }
}
