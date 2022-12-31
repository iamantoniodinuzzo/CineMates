package com.example.cinemates.adapter

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.cinemates.adapter.YoutubeVideoRecyclerViewAdapter.YoutubeViewHolder
import com.example.cinemates.databinding.ListItemYtVideoBinding
import com.example.cinemates.model.Video
import com.example.cinemates.util.YOUTUBE_COM_WATCH_V
import com.example.cinemates.util.inflater

/**
 * @author Antonio Di Nuzzo
 * Created 29/05/2022 at 07:59
 */
class YoutubeVideoRecyclerViewAdapter : RecyclerView.Adapter<YoutubeViewHolder>() {
    private var dataList: List<Video> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): YoutubeViewHolder {
        return YoutubeViewHolder(parent inflater ListItemYtVideoBinding::inflate)
    }

    override fun onBindViewHolder(holder: YoutubeViewHolder, position: Int) {
        val video = dataList[position]
        holder.binding.apply {
            this.video = video
            this.executePendingBindings()
            root.setOnClickListener { view ->
                val appIntent = Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:" + video.key))
                val webIntent = Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse("$YOUTUBE_COM_WATCH_V${video.key}")
                )
                try {
                    view.context.startActivity(appIntent)
                } catch (ex: ActivityNotFoundException) {
                    view.context.startActivity(webIntent)
                }
            }


        }
    }

        override fun getItemCount(): Int {
            return dataList.size
        }

        fun setDataList(dataList: List<Video>) {
            this.dataList = dataList
        }

        class YoutubeViewHolder(val binding: ListItemYtVideoBinding) : RecyclerView.ViewHolder(
            binding.root
        )
    }