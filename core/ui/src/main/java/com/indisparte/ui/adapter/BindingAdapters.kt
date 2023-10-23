package com.indisparte.ui.adapter

import android.widget.ImageView
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.indisparte.ui.custom_view.PosterView
import com.indisparte.util.extension.gone
import com.indisparte.util.extension.visible
import com.indisparte.ui.R

/**
 * @author Antonio Di Nuzzo
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

@BindingAdapter("loadDrawable")
fun loadDrawable(view: ImageView, drawableResId: Int?) {
    drawableResId?.let {
        view.visible()
        val drawable = AppCompatResources.getDrawable(view.context, it)
        view.setImageDrawable(drawable)
    }?: view.gone()
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

@BindingAdapter("app:isFavorite")
fun updateFabIcon(fab: FloatingActionButton, isFavorite: Boolean) {
    val drawableResId: Int =
        if (isFavorite) R.drawable.ic_favorite_filled
        else R.drawable.ic_favorite_border

    val drawable = ContextCompat.getDrawable(
        fab.context,
        drawableResId
    )
    fab.setImageDrawable(drawable)
}



