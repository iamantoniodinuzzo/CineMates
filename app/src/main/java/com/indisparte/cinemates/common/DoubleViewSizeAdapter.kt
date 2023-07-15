package com.indisparte.cinemates.common

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.indisparte.cinemates.util.ViewSize

/**
 * A generic base adapter for RecyclerView that uses DiffUtil, Data Binding, and View Binding,
 * for a recyclerview that displays objects with two view episodeGroupType.
 *
 * @param T The episodeGroupType of data to be displayed in the RecyclerView.
 * @param VDBLong The episodeGroupType of ViewDataBinding used in the RecyclerView item layout for Long view episodeGroupType.
 * @param VDBSmall The episodeGroupType of ViewDataBinding used in the RecyclerView item layout for Small view episodeGroupType.
 * @param longItemLayoutResId The layout resource ID of the RecyclerView item layout for Long view episodeGroupType.
 * @param smallItemLayoutResId The layout resource ID of the RecyclerView item layout for Small view episodeGroupType.
 *
 * @author Antonio Di Nuzzo (Indisparte)
 */
abstract class DoubleViewSizeAdapter<T, VDBLong : ViewDataBinding, VDBSmall : ViewDataBinding>(
    private val longItemLayoutResId: Int,
    private val smallItemLayoutResId: Int,
) : BaseAdapter<T>() {


    var currentLayoutType: ViewSize = ViewSize.SMALL
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MultipleViewViewHolder {
        return when (viewType) {
            ViewSize.LONG.ordinal -> {
                val inflater = LayoutInflater.from(parent.context)
                val binding =
                    DataBindingUtil.inflate<VDBLong>(inflater, longItemLayoutResId, parent, false)
                MultipleViewViewHolder(binding)
            }

            ViewSize.SMALL.ordinal -> {
                val inflater = LayoutInflater.from(parent.context)
                val binding =
                    DataBindingUtil.inflate<VDBSmall>(inflater, smallItemLayoutResId, parent, false)
                MultipleViewViewHolder(binding)
            }

            else -> throw IllegalArgumentException("Invalid view episodeGroupType")
        }
    }


    override fun getItemViewType(position: Int): Int {
        return currentLayoutType.ordinal
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        holder.bind(items[position])
    }


    /**
     * Switches between two layout
     */
    fun toggleLayoutType() {
        currentLayoutType = when (currentLayoutType) {
            ViewSize.LONG -> ViewSize.SMALL
            ViewSize.SMALL -> ViewSize.LONG
        }
    }

    inner class MultipleViewViewHolder(binding: ViewDataBinding) : BaseViewHolder(binding) {
        override fun bind(item: T) {
            when (currentLayoutType) {
                ViewSize.LONG -> {
                    val longBinding = binding as VDBLong
                    onBindLongItem(longBinding, item)
                    longBinding.executePendingBindings()
                }

                ViewSize.SMALL -> {
                    val smallBinding = binding as VDBSmall
                    onBindSmallItem(smallBinding, item)
                    smallBinding.executePendingBindings()
                }
            }
        }
    }

    abstract fun onBindLongItem(binding: VDBLong, item: T)

    abstract fun onBindSmallItem(binding: VDBSmall, item: T)

}
