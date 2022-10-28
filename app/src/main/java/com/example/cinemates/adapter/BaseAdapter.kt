package com.example.cinemates.adapter

import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

/**
 * @author Antonio Di Nuzzo (Indisparte)
 */
class BaseAdapter<T, VB: ViewDataBinding>(
    private val expressionOnCreateViewHolder: ((ViewGroup) -> VB),
    private val expressionViewHolderBinding: ((T, VB) -> Unit)? = null
) : RecyclerView.Adapter<BaseAdapter.Companion.BaseViewHolder<T, VB>>() {
    var dataList: List<T>? = mutableListOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    companion object{
        class BaseViewHolder<T, VB : ViewDataBinding>
        internal constructor(
            private val binding: VB,
            private val expression: ((T, VB) -> Unit)?  = null
        ) : RecyclerView.ViewHolder(binding.root) {
            fun bind(item: T) {
                expression?.let { it(item, binding) }
            }
        }

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<T, VB> {
        return BaseViewHolder(
            expressionOnCreateViewHolder(parent),
            expressionViewHolderBinding
        )
    }

    override fun onBindViewHolder(holder: BaseViewHolder<T, VB>, position: Int) {
        holder.bind(dataList!![position])
    }

    override fun getItemCount(): Int {
        return dataList!!.size
    }


}