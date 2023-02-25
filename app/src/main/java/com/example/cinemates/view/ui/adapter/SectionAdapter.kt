package com.example.cinemates.view.ui.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.cinemates.databinding.ListItemSectionBinding
import com.example.cinemates.model.*
import com.example.cinemates.util.inflater


/**
 * @author Antonio Di Nuzzo (Indisparte)
 */
class SectionAdapter(private val sections: MutableList<Section<*>>) : RecyclerView.Adapter<SectionViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SectionViewHolder {
        return when (viewType) {
            0 -> SectionMovieViewHolder(parent inflater ListItemSectionBinding::inflate)
            1 -> SectionTvViewHolder(parent inflater ListItemSectionBinding::inflate)
            2 -> SectionActorViewHolder(parent inflater ListItemSectionBinding::inflate)
            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    override fun onBindViewHolder(holder: SectionViewHolder, position: Int) {
        val section = sections[position]
        holder.bind(section)
    }

    override fun getItemCount(): Int {
        return sections.size
    }

    override fun getItemViewType(position: Int): Int {
        return when (sections[position]) {
            is SectionMovie -> 0
            is SectionTvShow -> 1
            is SectionPersons -> 2
            else -> throw IllegalArgumentException("Invalid data type")
        }
    }
}

