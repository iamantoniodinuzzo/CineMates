package com.example.cinemates.view.ui.adapter

import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cinemates.databinding.ListItemSectionBinding
import com.example.cinemates.model.SectionPersons
import com.example.cinemates.model.SectionMovie
import com.example.cinemates.model.SectionTvShow


/**
 * @author Antonio Di Nuzzo (Indisparte)
 */
sealed class SectionViewHolder(val binding: ListItemSectionBinding) :
    RecyclerView.ViewHolder(binding.root) {
    protected val titleView: TextView = binding.textSectionTitle
    protected val recyclerView: RecyclerView = binding.recyclerView
    abstract fun bind(section: Any)
}

class SectionMovieViewHolder(binding: ListItemSectionBinding) : SectionViewHolder(binding) {

    override fun bind(section: Any) {
        section as SectionMovie
        binding.section = section

        recyclerView.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = Movie2Adapter(section.items)
        }
    }
}

class SectionTvViewHolder(binding: ListItemSectionBinding) : SectionViewHolder(binding) {
    override fun bind(section: Any) {
        section as SectionTvShow
        binding.section = section

        recyclerView.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = TvShow2Adapter(section.items)
        }
    }
}

class SectionActorViewHolder(binding: ListItemSectionBinding) : SectionViewHolder(binding) {
    override fun bind(section: Any) {
        section as SectionPersons
        binding.section = section

        recyclerView.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = PersonAdapter(section.items)
        }
    }
}
