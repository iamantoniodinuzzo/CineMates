package com.indisparte.ui.adapter

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.indisparte.ui.R
import com.indisparte.ui.custom_view.PosterView

/**
 * @author Antonio Di Nuzzo (Indisparte)
 */
@BindingAdapter("loadImage")
fun loadImage(view: ImageView, url: String?) {
    Glide.with(view.context)
        .load(url)
        .error(R.drawable.ic_broken_image)
        .placeholder(R.drawable.ic_image)
        .transition(DrawableTransitionOptions.withCrossFade())
        .diskCacheStrategy(DiskCacheStrategy.DATA)
        .into(view)
}

@BindingAdapter("pv_chipValue")
fun setChipValue(view: PosterView, value: String?) {
    if (!value.isNullOrEmpty())
        view.setChipValue(value)
}

@BindingAdapter("pv_imageUrl")
fun setImageUrl(view: PosterView, imageUrl: String?) {
    view.loadImage(imageUrl)
}

@BindingAdapter("pv_title")
fun setTitle(view: PosterView, title: String) {
    view.setTitle(title)
}



