package com.example.cinemates.view.ui.adapter

import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.cinemates.NavGraphDirections
import com.example.cinemates.databinding.ListItemPersonSmallBinding
import com.example.cinemates.model.Cast
import com.example.cinemates.model.Person
import com.example.cinemates.util.inflater


/**
 * @author Antonio Di Nuzzo (Indisparte)
 */
class PersonAdapter(private var people: List<Person>) :
    RecyclerView.Adapter<PersonAdapter.ActorViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActorViewHolder {
        val view = parent inflater ListItemPersonSmallBinding::inflate
        return ActorViewHolder(view)
    }

    override fun onBindViewHolder(holder: ActorViewHolder, position: Int) {
        val person = people[position]
        holder.bind(person)
    }

    override fun getItemCount(): Int {
        return people.size
    }

    fun updateActors(newCast: List<Person>) {
        val diffResult = DiffUtil.calculateDiff(PersonDiffCallback(people, newCast))
        people = newCast
        diffResult.dispatchUpdatesTo(this)
    }

    class ActorViewHolder(val binding: ListItemPersonSmallBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(actor: Person) {
            binding.person = actor
            binding.root.setOnClickListener {
                val action = NavGraphDirections.actionGlobalActorDetailsFragment(actor)
                Navigation.findNavController(it).navigate(action)
            }
        }
    }

    private class PersonDiffCallback(
        private val oldPerson: List<Person>,
        private val newPerson: List<Person>
    ) : DiffUtil.Callback() {

        override fun getOldListSize(): Int {
            return oldPerson.size
        }

        override fun getNewListSize(): Int {
            return newPerson.size
        }

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldPerson[oldItemPosition].id == newPerson[newItemPosition].id
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldPerson[oldItemPosition] == newPerson[newItemPosition]
        }
    }
}
