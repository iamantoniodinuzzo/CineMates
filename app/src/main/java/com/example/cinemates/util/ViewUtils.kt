package com.example.cinemates.util

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.viewbinding.ViewBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions

/**
 * @author Antonio Di Nuzzo
 * Created 06/10/2022
 */
inline infix fun <reified T : ViewBinding>
        ViewGroup.inflater(crossinline inflater: (LayoutInflater, ViewGroup, Boolean) -> T): T =
    LayoutInflater.from(context).let { inflater.invoke(it, this, false) }


fun ImageView.load(string : String) =
    Glide.with(context)
        .load(string)
        .transition(DrawableTransitionOptions.withCrossFade())
        .centerCrop()
        .into(this)