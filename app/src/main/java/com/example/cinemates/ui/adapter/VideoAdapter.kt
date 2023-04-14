package com.example.cinemates.ui.adapter


/**
 * @author Antonio Di Nuzzo (Indisparte)
 */
import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.cinemates.R
import com.example.cinemates.common.SingleViewAdapter
import com.example.cinemates.databinding.ListItemYtVideoBinding
import com.example.cinemates.model.Video
import com.example.cinemates.data.YOUTUBE_COM_WATCH_V

class VideoAdapter : SingleViewAdapter<Video, ListItemYtVideoBinding>(R.layout.list_item_yt_video, ) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SingleViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<ListItemYtVideoBinding>(inflater, itemLayoutResId, parent, false)
        return SingleViewHolder(binding)
    }

    override fun onBindItem(binding: ListItemYtVideoBinding, item: Video) {
        binding.root.setOnClickListener {view->
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
        binding.video = item

    }
}
