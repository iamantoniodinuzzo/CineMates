package com.example.cinemates.ui.adapter

import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.example.cinemates.databinding.ListItemBackdropBinding
import com.example.cinemates.databinding.ListItemPosterBinding
import com.example.cinemates.domain.model.common.Image

/**
 * @author Antonio Di Nuzzo (Indisparte)
 */
sealed class ImageViewHolder(open val binding:ViewBinding):RecyclerView.ViewHolder(binding.root){
    abstract fun bind(image: Image)
}
class PosterViewHolder(
    override val binding: ListItemPosterBinding,
) :
    ImageViewHolder(binding) {
    override fun bind(image: Image) {

        binding.path = image.filePath

        binding.root.setOnClickListener{
            // TODO: Show enlarged image
            Toast.makeText(it.context, "Soon", Toast.LENGTH_SHORT).show()
        }

    }
}
class BackdropViewHolder(
    override val binding: ListItemBackdropBinding,
) :
    ImageViewHolder(binding) {
    override fun bind(image: Image) {

        binding.path = image.filePath

        binding.root.setOnClickListener{
            // TODO: Show enlarged image
            Toast.makeText(it.context, "Soon", Toast.LENGTH_SHORT).show()
        }

    }
}


