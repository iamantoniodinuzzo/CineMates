package com.example.cinemates.util

import android.os.Bundle
import androidx.recyclerview.widget.DiffUtil
import com.example.cinemates.model.entities.Movie

/**
 * @author Antonio Di Nuzzo
 * Created 21/07/2022 at 19:16
 */
class MovieDiffUtilCallbacks(
    private val oldList: List<Movie>,
    private val newList: List<Movie>
) : DiffUtil.Callback() {
    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return true
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return newList[newItemPosition] == oldList[oldItemPosition]
    }

    override fun getChangePayload(oldItemPosition: Int, newItemPosition: Int): Any? {
        val bundle = Bundle()
        val newMovie = newList[newItemPosition]
        if (newMovie != oldList[oldItemPosition]) bundle.putSerializable(
            "movie",
            newMovie
        )
        return if (bundle.size() == 0) null else bundle
    }
}