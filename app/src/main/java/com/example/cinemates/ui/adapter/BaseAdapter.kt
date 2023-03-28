package com.example.cinemates.ui.adapter

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView

/**
 * @author Antonio Di Nuzzo (Indisparte)
 */
abstract class BaseAdapter<T>(var items:List<T>) :
    RecyclerView.Adapter<BaseAdapter<T>.BaseViewHolder>() {

    abstract inner class BaseViewHolder(val binding: ViewDataBinding) :
        RecyclerView.ViewHolder(binding.root) {
        abstract fun bind(item: T)
    }

    /**
     * Updates the list of data items and notifies the adapter of the changes.
     *
     * @param newItems The new list of data items to display in the RecyclerView.
     */
    fun updateItems(newItems: List<T>){
        val diffResult = DiffUtil.calculateDiff(DiffCallback(items, newItems))
        items = newItems
        diffResult.dispatchUpdatesTo(this)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    /**
     * A callback class for DiffUtil to calculate the difference between old and new lists of data items.
     *
     * @param oldItems The old list of data items.
     * @param newItems The new list of data items.
     */
    class DiffCallback<T>(private val oldItems: List<T>, private val newItems: List<T>) :
        DiffUtil.Callback() {

        override fun getOldListSize(): Int = oldItems.size

        override fun getNewListSize(): Int = newItems.size

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            val oldItem = oldItems[oldItemPosition]
            val newItem = newItems[newItemPosition]
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            val oldItem = oldItems[oldItemPosition]
            val newItem = newItems[newItemPosition]
            return oldItem == newItem
        }
    }
}