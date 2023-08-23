package com.indisparte.movie_details.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import com.indisparte.model.entity.Crew
import com.indisparte.movie_details.databinding.ListItemCrewBinding
import com.indisparte.ui.adapter.BaseAdapter
import com.indisparte.ui.adapter.OnItemClickListener
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

    private var itemClickListener: OnItemClickListener<Crew>? = null
    fun setOnItemClickListener(listener: OnItemClickListener<Crew>) {
        itemClickListener = listener
    }
    private val LOG = Timber.tag(CrewAdapter::class.java.simpleName)
    override fun bind(binding: ListItemCrewBinding, item: Crew) {
        binding.crew = item
        binding.root.setOnClickListener {
            itemClickListener?.onItemClick(item)
        }
    }

    override fun createBinding(parent: ViewGroup, viewType: Int): ListItemCrewBinding {
        val inflater = LayoutInflater.from(parent.context)
        return ListItemCrewBinding.inflate(inflater, parent, false)
    }
}