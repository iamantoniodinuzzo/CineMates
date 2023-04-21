package com.example.cinemates.ui.adapter


/**
 * @author Antonio Di Nuzzo (Indisparte)
 */
import android.util.Log
import android.view.View
import androidx.navigation.Navigation
import com.example.cinemates.NavGraphDirections
import com.example.cinemates.R
import com.example.cinemates.common.DoubleViewSizeAdapter
import com.example.cinemates.databinding.ListItemPersonLongBinding
import com.example.cinemates.databinding.ListItemPersonSmallBinding
import com.example.cinemates.domain.model.Cast
import com.example.cinemates.domain.model.Person

class PersonAdapter :
    DoubleViewSizeAdapter<Person, ListItemPersonLongBinding, ListItemPersonSmallBinding>(
        R.layout.list_item_person_long, R.layout.list_item_person_small,
    ) {

    override fun onBindLongItem(binding: ListItemPersonLongBinding, item: Person) {
        binding.actor = item as Cast
        binding.root.setOnClickListener {

            navigateToDetails(it, item)
        }
    }

    override fun onBindSmallItem(binding: ListItemPersonSmallBinding, item: Person) {
        binding.person = item
        binding.root.setOnClickListener {
            navigateToDetails(it, item)

        }
    }

    private fun navigateToDetails(view: View, item: Person) {
        val action = NavGraphDirections.actionGlobalActorDetailsFragment(item)
        Navigation.findNavController(view).navigate(action)

    }
}
