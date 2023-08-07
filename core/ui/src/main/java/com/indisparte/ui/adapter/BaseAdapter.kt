package com.indisparte.ui.adapter

import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView


/**
 * This is a base adapter class for RecyclerViews, which uses data binding to bind items to views.
 * It is a generic class that takes in the type of data to be displayed, and the type of the view binding.
 *
 * @param T The type of data to be displayed
 * @param VB The type of the view binding
 * @param diffCallback The callback to compare items in the list for optimization purposes
 * @author Antonio Di Nuzzo (Indisparte)
 */
abstract class BaseAdapter<T, VB : ViewDataBinding>(
    diffCallback: DiffUtil.ItemCallback<T>,
) : ListAdapter<T, BaseAdapter<T, VB>.BaseViewHolder>(diffCallback) {


    /**
     * This abstract method is used to bind the data to the view.
     *
     * @param binding The view binding to bind the data to
     * @param item The data to be displayed
     */
    protected abstract fun bind(binding: VB, item: T)

    /**
     * This abstract method is used to create the view binding for the item view.
     *
     * @param parent The parent ViewGroup of the item view
     * @param viewType The type of the view to be created
     * @return The view binding for the item view
     */
    protected abstract fun createBinding(parent: ViewGroup, viewType: Int): VB

    /**
     * This method is called by the RecyclerView to display the data at the specified position.
     * It uses the BaseViewHolder to bind the view to the data.
     *
     * @param holder The view holder for the item view
     * @param position The position of the item in the list
     */
    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    /**
     * This method is called by the RecyclerView when it needs a new view holder for a new item view.
     * It creates a new view binding using the createBinding method, and returns a new BaseViewHolder
     * with the binding.
     *
     * @param parent The parent ViewGroup of the item view
     * @param viewType The type of the view to be created
     * @return The new view holder for the item view
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        val binding = createBinding(parent, viewType)
        return BaseViewHolder(binding)
    }

    /**
     * This inner class is the view holder for the item view. It uses the view binding to bind the data
     * to the view, and executes any pending bindings.
     *
     * @param binding The view binding for the item view
     */
    inner class BaseViewHolder(
        private val binding: ViewDataBinding,
    ) : RecyclerView.ViewHolder(binding.root) {

        /**
         * This method is used to bind the data to the view using the bind method, and executes any
         * pending bindings.
         *
         * @param item The data to be displayed
         */
        fun bind(item: T) {
            bind(binding as VB, item)
            binding.executePendingBindings()
        }
    }
}