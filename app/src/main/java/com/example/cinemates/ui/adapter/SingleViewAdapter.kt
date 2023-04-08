package com.example.cinemates.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

/**
 * A generic base adapter for RecyclerView that uses DiffUtil, Data Binding, and View Binding
 * for a recyclerview that displays objects with a single view type
 *
 * @param T The type of data to be displayed in the RecyclerView.
 * @param VB The type of ViewDataBinding used in the RecyclerView item layout.
 * @param itemLayoutResId The layout resource ID of the RecyclerView item layout.
 *
 * @author Antonio Di Nuzzo (Indisparte)
 */
abstract class SingleViewAdapter<T, VB : ViewDataBinding>(
    val itemLayoutResId: Int,
) : BaseAdapter<T>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SingleViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<VB>(inflater, itemLayoutResId, parent, false)
        return SingleViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class SingleViewHolder( binding: VB) : BaseViewHolder(binding) {
        override fun bind(item: T) {
            onBindItem(binding as VB, item)
            binding.executePendingBindings()
        }
    }

    abstract fun onBindItem(binding: VB, item: T)

}
