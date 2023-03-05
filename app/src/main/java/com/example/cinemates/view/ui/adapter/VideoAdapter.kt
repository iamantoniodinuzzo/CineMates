package com.example.cinemates.view.ui.adapter


/**
 * @author Antonio Di Nuzzo (Indisparte)
 */
import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import com.example.cinemates.NavGraphDirections
import com.example.cinemates.R
import com.example.cinemates.databinding.ListItemMovieSmallBinding
import com.example.cinemates.databinding.ListItemYtVideoBinding
import com.example.cinemates.model.Movie
import com.example.cinemates.model.Video
import com.example.cinemates.util.YOUTUBE_COM_WATCH_V

class VideoAdapter : BaseAdapter<Video, ListItemYtVideoBinding>(R.layout.list_item_yt_video, emptyList()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<ListItemYtVideoBinding>(inflater, itemLayoutResId, parent, false)
        return BaseViewHolder(binding)
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
