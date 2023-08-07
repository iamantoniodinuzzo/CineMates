package com.indisparte.cinemates.common

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView

/**
 * A generic base adapter class for RecyclerView.
 *
 * @param T The episodeGroupType of the items in the adapter.
 * @author Antonio Di Nuzzo (Indisparte)
 */
abstract class BaseAdapter<T> :
    RecyclerView.Adapter<BaseAdapter<T>.BaseViewHolder>() {

    /**
     * The list of items in the adapter.
     */
    var items: List<T> = emptyList()
        set(value) {
            // Calculate the difference between the old and new list of items using DiffUtil.
            val diffResult = DiffUtil.calculateDiff(
                DiffCallback(
                    field,
                    value
                )
            )
            // Update the field with the new list of items.
            field = value
            // Dispatch the updates to the adapter.
            diffResult.dispatchUpdatesTo(this)
        }

    /**
     * An abstract inner class representing a view holder for the adapter.
     *
     * @param binding The data binding for the view holder.
     */
    abstract inner class BaseViewHolder(val binding: ViewDataBinding) :
        RecyclerView.ViewHolder(binding.root) {

        /**
         * Binds the item to the view holder.
         *
         * @param item The item to bind.
         */
        abstract fun bind(item: T)
    }

    /**
     * Returns the number of items in the adapter.
     */
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

        /**
         * Returns the size of the old list.
         */
        override fun getOldListSize(): Int = oldItems.size

        /**
         * Returns the size of the new list.
         */
        override fun getNewListSize(): Int = newItems.size

        /**
         * Returns whether the old and new items at the given positions are the same.
         */
        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            val oldItem = oldItems[oldItemPosition]
            val newItem = newItems[newItemPosition]
            return oldItem == newItem
        }

        /**
         * Returns whether the contents of the old and new items at the given positions are the same.
         */
        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            val oldItem = oldItems[oldItemPosition]
            val newItem = newItems[newItemPosition]
            // Check if the items are equal, based on their content.
            return oldItem == newItem
        }
    }
}