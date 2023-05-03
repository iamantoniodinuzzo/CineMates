package com.indisparte.recyclerviewemptystatesupport

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.recyclerview.widget.RecyclerView

/**
 *
 * @author Antonio Di Nuzzo (Indisparte)
 */
internal class RecyclerViewEmptyState(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) :RecyclerView(context, attrs, defStyle) {
    constructor(context: Context) : this(context, null, 0)
    constructor(context: Context, attrs: AttributeSet) : this(context, attrs, 0)

    private var emptyView: View? = null

    private val dataObserver: AdapterDataObserver = object : AdapterDataObserver() {
        override fun onChanged() {
            super.onChanged()
            checkIfEmpty()
        }

        override fun onItemRangeInserted(positionStart: Int, itemCount: Int) {
            super.onItemRangeInserted(positionStart, itemCount)
            checkIfEmpty()
        }

        override fun onItemRangeRemoved(positionStart: Int, itemCount: Int) {
            super.onItemRangeRemoved(positionStart, itemCount)
            checkIfEmpty()
        }
    }

    override fun setAdapter(adapter: Adapter<*>?) {
        getAdapter()?.unregisterAdapterDataObserver(dataObserver)
        super.setAdapter(adapter)
        adapter?.registerAdapterDataObserver(dataObserver)
        checkIfEmpty()
    }

    fun setEmptyView(emptyView: View) {
        this.emptyView = emptyView
        checkIfEmpty()
    }

    /**
     * Checks if the Adapter contains no elements and, if so,
     * makes the RecyclerView invisible and shows emptyView.
     * If there are elements, the RecyclerView is displayed and emptyView is hidden.
     */
    private fun checkIfEmpty() {
        val adapter = adapter
        val emptyView = emptyView

        if (adapter != null && adapter.itemCount == 0) {
            if (emptyView != null) {
                emptyView.visibility = View.VISIBLE
            }
            visibility = View.GONE
        } else {
            if (emptyView != null) {
                emptyView.visibility = View.GONE
            }
            visibility = View.VISIBLE
        }
    }
}