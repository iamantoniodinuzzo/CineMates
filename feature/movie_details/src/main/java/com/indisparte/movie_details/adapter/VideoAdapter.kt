package com.indisparte.movie_details.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import com.indisparte.common.Video
import com.indisparte.movie_details.databinding.ListItemYtVideoBinding
import com.indisparte.ui.adapter.BaseAdapter


/**
 * @author Antonio Di Nuzzo (Indisparte)
 */
class VideoAdapter : BaseAdapter<Video, ListItemYtVideoBinding>(object :
    DiffUtil.ItemCallback<Video>() {
    override fun areItemsTheSame(oldItem: Video, newItem: Video): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Video, newItem: Video): Boolean {
        return oldItem == newItem
    }
}) {
    override fun bind(binding: ListItemYtVideoBinding, item: Video) {
        binding.apply {
            video = item
            root.setOnClickListener {
                // TODO: Open youtube if is youtube
            }
        }
    }

    override fun createBinding(parent: ViewGroup, viewType: Int): ListItemYtVideoBinding {
        val inflater = LayoutInflater.from(parent.context)
        return ListItemYtVideoBinding.inflate(inflater, parent, false)
    }

}