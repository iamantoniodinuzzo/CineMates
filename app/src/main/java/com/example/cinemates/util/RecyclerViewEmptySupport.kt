package com.example.cinemates.util

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.recyclerview.widget.RecyclerView

/**
 * @author Antonio Di Nuzzo
 * Created 28/03/2022 at 17:36
 */
class RecyclerViewEmptySupport : RecyclerView {
    private var emptyView: LinearLayoutCompat? = null
    private val emptyObserver: AdapterDataObserver = object : AdapterDataObserver() {
        override fun onChanged() {
            val adapter = adapter
            if (adapter != null && emptyView != null) {
                if (adapter.itemCount == 0) {
                    emptyView!!.visibility = VISIBLE
                    this@RecyclerViewEmptySupport.visibility = GONE
                } else {
                    emptyView!!.visibility = GONE
                    this@RecyclerViewEmptySupport.visibility = VISIBLE
                }
            }
        }
    }

    constructor(context: Context?, attrs: AttributeSet?) : super(
        context!!, attrs
    )

    constructor(context: Context?, attrs: AttributeSet?, defStyle: Int) : super(
        context!!, attrs, defStyle
    )

    constructor(context: Context) : super(context)

    override fun setAdapter(adapter: Adapter<*>?) {
        super.setAdapter(adapter)
        adapter?.registerAdapterDataObserver(emptyObserver)
        emptyObserver.onChanged()
    }

    fun setEmptyView(emptyView: LinearLayoutCompat) {
        this.emptyView = emptyView
    }
}