package com.indisparte.movie_details.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import com.indisparte.model.entity.Crew
import com.indisparte.movie_details.databinding.ListItemCrewBinding
import com.indisparte.ui.adapter.BaseAdapter
import timber.log.Timber

class CrewAdapter : BaseAdapter<Crew, ListItemCrewBinding>(
    object : DiffUtil.ItemCallback<Crew>() {
        override fun areItemsTheSame(oldItem: Crew, newItem: Crew): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Crew, newItem: Crew): Boolean {
            return oldItem == newItem
        }

    }
) {
    private val LOG = Timber.tag(CrewAdapter::class.java.simpleName)
    override fun bind(binding: ListItemCrewBinding, item: Crew) {
        binding.crew = item
        binding.root.setOnClickListener {
            // TODO: Open details
        }
    }

    override fun createBinding(parent: ViewGroup, viewType: Int): ListItemCrewBinding {
        val inflater = LayoutInflater.from(parent.context)
        return ListItemCrewBinding.inflate(inflater, parent, false)
    }
}