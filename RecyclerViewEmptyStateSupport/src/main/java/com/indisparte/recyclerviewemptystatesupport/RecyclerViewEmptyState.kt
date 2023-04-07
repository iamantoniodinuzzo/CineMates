package com.indisparte.recyclerviewemptystatesupport

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.recyclerview.widget.RecyclerView

/**
 *
 * @author Antonio Di Nuzzo (Indisparte)
 */
internal class RecyclerViewEmptyState : RecyclerView {

    private var emptyView: View? = null

    constructor(context: Context?, attrs: AttributeSet?) : super(
        context!!, attrs
    )

    constructor(context: Context?, attrs: AttributeSet?, defStyle: Int) : super(
        context!!, attrs, defStyle
    )

    constructor(context: Context) : super(context)

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
        val oldAdapter = getAdapter()
        oldAdapter?.unregisterAdapterDataObserver(dataObserver)
        super.setAdapter(adapter)
        adapter?.registerAdapterDataObserver(dataObserver)
        checkIfEmpty()
    }


    fun setEmptyView(emptyView: View?) {
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
        adapter?.let {
            if (it.itemCount == 0) {
                emptyView?.let { view ->
                    view.visibility = View.VISIBLE
                    this@RecyclerViewEmptyState.visibility = View.GONE
                }
            } else {
                emptyView?.visibility = View.GONE
                this@RecyclerViewEmptyState.visibility = View.VISIBLE
            }
        }
    }

}