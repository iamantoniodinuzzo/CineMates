package com.example.cinemates.view.ui.adapter

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import com.example.cinemates.databinding.ListItemYtVideoBinding
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.cinemates.model.Video
import com.example.cinemates.util.YOUTUBE_COM_WATCH_V

/**
 * @author Antonio Di Nuzzo (Indisparte)
 */
class VideoAdapter : RecyclerView.Adapter<VideoAdapter.VideoViewHolder>() {

    private val differCallback = object : DiffUtil.ItemCallback<Video>() {

        override fun areItemsTheSame(oldItem: Video, newItem: Video): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Video, newItem: Video): Boolean {
            return oldItem == newItem
        }

    }
    private val dataList = AsyncListDiffer(this, differCallback)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoViewHolder {
        val itemBinding =
            ListItemYtVideoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return VideoViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: VideoViewHolder, position: Int) {
        holder.bind(dataList.currentList[position])
    }

    override fun getItemCount(): Int {
        return dataList.currentList.size
    }

    var items: List<Video>
        get() = dataList.currentList
        set(value) = dataList.submitList(value)

    inner class VideoViewHolder(
        private val binding: ListItemYtVideoBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Video) {
            with(binding) {
                root.setOnClickListener {view->
                    val appIntent = Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:" + item.key))
                    val webIntent = Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse("$YOUTUBE_COM_WATCH_V${item.key}")
                    )
                    try {
                        view.context.startActivity(appIntent)
                    } catch (ex: ActivityNotFoundException) {
                        view.context.startActivity(webIntent)
                    }
                }
                video = item
            }
        }
    }

    private var onItemClickListener: ((Video) -> Unit)? = null

    fun setOnItemClickListener(listener: (Video) -> Unit) {
        onItemClickListener = listener
    }
}