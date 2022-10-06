package com.example.cinemates.adapter

import android.app.DownloadManager
import android.content.Context
import android.net.Uri
import android.os.Environment
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.example.cinemates.databinding.ListItemBackdropBinding
import com.example.cinemates.databinding.ListItemPosterBinding
import com.example.cinemates.model.data.ImagesResponse
import com.example.cinemates.util.BASE_URL
import com.example.cinemates.util.DialogFactory
import com.example.cinemates.util.inflater
import java.io.File

/**
 * @author Antonio Di Nuzzo
 * Created 23/06/2022 at 17:23
 */
class ImageRecyclerViewAdapter(private val context: Context) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val dataList: MutableList<ImagesResponse.Image> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == ImagesResponse.Image.POSTER) {
            PosterViewHolder(parent inflater ListItemPosterBinding::inflate)
        } else {
            BackdropViewHolder(parent inflater ListItemBackdropBinding::inflate)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val image = dataList[position]
        if (getItemViewType(position) == ImagesResponse.Image.POSTER) {
            (holder as PosterViewHolder).bind(image)

        } else {
            (holder as BackdropViewHolder).bind(image)
        }
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    fun addItems(dataList: List<ImagesResponse.Image>?) {
        this.dataList.addAll(dataList!!)
        notifyDataSetChanged()
    }

    override fun getItemViewType(position: Int): Int {
        val image = dataList[position]
        return image.imageType
    }


    abstract inner class ImageViewHolder<Any : ViewBinding>(val binding: Any) :
        RecyclerView.ViewHolder(binding.root) {
        abstract fun bind(image: ImagesResponse.Image)
        fun onImageClick(filename: String, url: String) {
            binding.root.setOnClickListener {
                downloadImageNew(filename, url)
            }
        }

        private fun downloadImageNew(filename: String, downloadUrlOfImage: String) {
            DialogFactory.createSimpleOkCancelDialog(context,
                "Want to download this image?",
                "Download",
                "Cancel",
                { _, _ ->
                    try {
                        val dm =
                            context.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
                        val downloadUri = Uri.parse(downloadUrlOfImage)
                        val request = DownloadManager.Request(downloadUri)
                        request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI or DownloadManager.Request.NETWORK_MOBILE)
                            .setAllowedOverRoaming(false)
                            .setTitle(filename)
                            .setMimeType("image/jpeg") // Your file type. You can use this code to download other file types also.
                            .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
                            .setDestinationInExternalPublicDir(
                                Environment.DIRECTORY_PICTURES,
                                File.separator + filename + ".jpg"
                            )
                        dm.enqueue(request)
                        Toast.makeText(context, "Image download started.", Toast.LENGTH_SHORT)
                            .show()
                    } catch (e: Exception) {
                        Toast.makeText(context, "Image download failed.", Toast.LENGTH_SHORT).show()
                    }
                }) { dialogInterface, _ -> dialogInterface.dismiss() }.show()
        }
    }
    inner class PosterViewHolder(binding: ListItemPosterBinding) :
        ImageViewHolder<ListItemPosterBinding>(binding) {
        override fun bind(image: ImagesResponse.Image) {
            binding.image = image
            binding.executePendingBindings()
            val filename = (image.width + image.height).toString()
            val url = "$BASE_URL${image.file_path}"
            onImageClick(filename, url)
        }
    }
    inner class BackdropViewHolder(binding: ListItemBackdropBinding) :
        ImageViewHolder<ListItemBackdropBinding>(binding) {
        override fun bind(image: ImagesResponse.Image) {
            binding.image = image
            binding.executePendingBindings()
            val filename = (image.width + image.height).toString()
            val url = "$BASE_URL${image.file_path}"
            onImageClick(filename, url)
        }
    }
}