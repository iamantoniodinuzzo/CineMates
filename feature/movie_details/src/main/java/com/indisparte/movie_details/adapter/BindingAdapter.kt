package com.indisparte.movie_details.adapter

import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.google.android.material.floatingactionbutton.FloatingActionButton

/**
 *@author Antonio Di Nuzzo
 */
@BindingAdapter("app:isFavorite")
fun updateFabIcon(fab: FloatingActionButton, isFavorite: Boolean) {
    val drawableResId: Int =
        if (isFavorite) com.indisparte.movie_details.R.drawable.ic_favorite_filled
        else com.indisparte.movie_details.R.drawable.ic_favorite_border

    val drawable = ContextCompat.getDrawable(
        fab.context,
        drawableResId
    )
    fab.setImageDrawable(drawable)
}