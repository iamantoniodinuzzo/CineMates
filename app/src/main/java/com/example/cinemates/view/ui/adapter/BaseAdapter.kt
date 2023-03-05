package com.example.cinemates.view.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView

/**
 * A generic base adapter for RecyclerView that uses DiffUtil, Data Binding, and View Binding.
 *
 * @param T The type of data to be displayed in the RecyclerView.
 * @param VB The type of ViewDataBinding used in the RecyclerView item layout.
 * @param itemLayoutResId The layout resource ID of the RecyclerView item layout.
 * @param items The initial list of data items to display in the RecyclerView.
 *
 * @author Antonio Di Nuzzo (Indisparte)
 */
abstract class BaseAdapter<T, VB : ViewDataBinding>(
    val itemLayoutResId: Int,
    private var items: List<T>
) : RecyclerView.Adapter<BaseAdapter<T, VB>.BaseViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<VB>(inflater, itemLayoutResId, parent, false)
        return BaseViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }

    /**
     * Updates the list of data items and notifies the adapter of the changes.
     *
     * @param newItems The new list of data items to display in the RecyclerView.
     */
    fun updateItems(newItems: List<T>) {
        val diffResult = DiffUtil.calculateDiff(DiffCallback(items, newItems))
        items = newItems
        diffResult.dispatchUpdatesTo(this)
    }

    inner class BaseViewHolder(private val binding: VB) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: T) {
            onBindItem(binding, item)
            binding.executePendingBindings()
        }
    }

    abstract fun onBindItem(binding: VB, item: T)

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
