package com.example.cinemates.util

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding

/**
 * @author Antonio Di Nuzzo
 * Created 06/10/2022
 */
inline infix fun <reified T : ViewBinding>
        ViewGroup.inflater(crossinline inflater: (LayoutInflater, ViewGroup, Boolean) -> T): T =
    LayoutInflater.from(context).let { inflater.invoke(it, this, false) }

