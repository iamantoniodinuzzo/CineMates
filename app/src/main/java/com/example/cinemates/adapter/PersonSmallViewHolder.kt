package com.example.cinemates.adapter

import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.cinemates.NavGraphDirections
import com.example.cinemates.databinding.ListItemPersonSmallBinding
import com.example.cinemates.model.data.Person

/**
 * @author Antonio Di Nuzzo (Indisparte)
 */
class PersonSmallViewHolder(
    private val binding: ListItemPersonSmallBinding,
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(person: Person) {
        binding.apply {
            setPerson( person)
            root.setOnClickListener { _ ->
                val action = NavGraphDirections.actionGlobalActorDetailsFragment(person)
                Navigation.findNavController(binding.root).navigate(action)
            }
        }
    }
}