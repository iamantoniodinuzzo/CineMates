package com.indisparte.movie_details.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import com.indisparte.person.Crew
import com.indisparte.movie_details.databinding.ListItemCrewBinding
import com.indisparte.ui.adapter.BaseAdapter
import com.indisparte.ui.adapter.OnItemClickListener
import timber.log.Timber

class CrewAdapter : BaseAdapter<com.indisparte.person.Crew, ListItemCrewBinding>(
    object : DiffUtil.ItemCallback<com.indisparte.person.Crew>() {
        override fun areItemsTheSame(oldItem: com.indisparte.person.Crew, newItem: com.indisparte.person.Crew): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: com.indisparte.person.Crew, newItem: com.indisparte.person.Crew): Boolean {
            return oldItem == newItem
        }

    }
) {

    private var itemClickListener: OnItemClickListener<com.indisparte.person.Crew>? = null
    fun setOnItemClickListener(listener: OnItemClickListener<com.indisparte.person.Crew>) {
        itemClickListener = listener
    }
    private val LOG = Timber.tag(CrewAdapter::class.java.simpleName)
    override fun bind(binding: ListItemCrewBinding, item: com.indisparte.person.Crew) {
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