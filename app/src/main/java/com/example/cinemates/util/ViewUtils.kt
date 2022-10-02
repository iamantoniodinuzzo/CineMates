package com.example.cinemates.util

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding

/**
 * @author Antonio Di Nuzzo
 * Created 02/10/2022
 */
inline infix fun<reified T : ViewBinding>
        ViewGroup.help(crossinline inflater : (LayoutInflater, ViewGroup, Boolean) -> T): T =
    LayoutInflater.from(context).let { inflater.invoke(it, this, false) }