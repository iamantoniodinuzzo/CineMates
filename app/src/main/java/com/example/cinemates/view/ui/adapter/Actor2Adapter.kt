package com.example.cinemates.view.ui.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.cinemates.databinding.ListItemPersonSmallBinding
import com.example.cinemates.model.Cast
import com.example.cinemates.util.inflater


/**
 * @author Antonio Di Nuzzo (Indisparte)
 */
class Actor2Adapter(private var actors: List<Cast>) :
    RecyclerView.Adapter<Actor2Adapter.ActorViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActorViewHolder {
        val view = parent inflater ListItemPersonSmallBinding::inflate
        return ActorViewHolder(view)
    }

    override fun onBindViewHolder(holder: ActorViewHolder, position: Int) {
        val movie = actors[position]
        holder.bind(movie)
    }

    override fun getItemCount(): Int {
        return actors.size
    }

    fun updateActors(newCast: List<Cast>) {
        val diffResult = DiffUtil.calculateDiff(CastDiffCallback(actors, newCast))
        actors = newCast
        diffResult.dispatchUpdatesTo(this)
    }

    class ActorViewHolder(val binding: ListItemPersonSmallBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(actor: Cast) {
            binding.person = actor
        }
    }

    private class CastDiffCallback(
        private val oldMovies: List<Cast>,
        private val newMovies: List<Cast>
    ) : DiffUtil.Callback() {

        override fun getOldListSize(): Int {
            return oldMovies.size
        }

        override fun getNewListSize(): Int {
            return newMovies.size
        }

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldMovies[oldItemPosition].id == newMovies[newItemPosition].id
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldMovies[oldItemPosition] == newMovies[newItemPosition]
        }
    }
}
