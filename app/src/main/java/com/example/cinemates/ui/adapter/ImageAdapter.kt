package com.example.cinemates.ui.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.cinemates.data.remote.response.image.ImageType
import com.example.cinemates.databinding.ListItemBackdropBinding
import com.example.cinemates.databinding.ListItemPosterBinding
import com.example.cinemates.domain.model.Image
import com.example.cinemates.util.inflater

/**
 * @author Antonio Di Nuzzo (Indisparte)
 */
class ImageAdapter : RecyclerView.Adapter<ImageViewHolder>() {

    private val differCallback = object : DiffUtil.ItemCallback<Image>() {

        override fun areItemsTheSame(oldItem: Image, newItem: Image): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Image, newItem: Image): Boolean {
            return oldItem == newItem
        }

    }
    private val dataList = AsyncListDiffer(this, differCallback)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        return when (viewType) {
            ImageType.POSTER.value -> PosterViewHolder(
                parent inflater ListItemPosterBinding::inflate
            )
            ImageType.BACKDROP.value -> BackdropViewHolder(
                parent inflater ListItemBackdropBinding::inflate
            )
            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        holder.bind(dataList.currentList[position])
    }

    override fun getItemCount(): Int {
        return dataList.currentList.size
    }

    override fun getItemViewType(position: Int): Int {
        return dataList.currentList[position].imageType.value
    }

    var items: List<Image>
        get() = dataList.currentList
        set(value) = dataList.submitList(value)

}