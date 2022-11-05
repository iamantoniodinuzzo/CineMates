package com.example.cinemates.adapter

import android.view.View
import androidx.databinding.ViewDataBinding
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.cinemates.NavGraphDirections
import com.example.cinemates.model.data.Movie
import com.example.cinemates.model.data.Person
import com.example.cinemates.util.ViewSize

/**
 * @author Antonio Di Nuzzo (Indisparte)
 */
abstract class MultipleViewSizeAdapter<T> : RecyclerView.Adapter<MultipleViewSizeAdapter.BaseViewHolder<T>>() {
    var viewSize: ViewSize = ViewSize.SMALL
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    abstract  class BaseViewHolder<T>(
        binding: ViewDataBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        abstract fun bind(items: T)
    }
    abstract fun addItems(items: List<T>)
}