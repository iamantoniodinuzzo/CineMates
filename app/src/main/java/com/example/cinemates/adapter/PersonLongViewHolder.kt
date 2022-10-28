package com.example.cinemates.adapter

import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.cinemates.NavGraphDirections
import com.example.cinemates.databinding.ListItemPersonLongBinding
import com.example.cinemates.model.data.Cast

/**
 * @author Antonio Di Nuzzo (Indisparte)
 */
class PersonLongViewHolder(
    private val binding: ListItemPersonLongBinding,
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(cast: Cast) {
        binding.apply {
            actor = cast
            root.setOnClickListener { _ ->
                val action = NavGraphDirections.actionGlobalActorDetailsFragment(cast)
                Navigation.findNavController(binding.root).navigate(action)
            }
        }
    }
}