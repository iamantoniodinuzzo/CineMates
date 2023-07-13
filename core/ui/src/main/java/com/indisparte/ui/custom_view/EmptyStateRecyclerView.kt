package com.indisparte.ui.custom_view


import android.content.Context
import android.util.AttributeSet
import android.view.Gravity
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.indisparte.ui.R
import com.indisparte.ui.databinding.ViewEmptyStateBinding
import com.indisparte.util.extension.gone
import com.indisparte.util.extension.visible

/**
 * A custom RecyclerView with support for displaying an empty state view when the adapter is empty.
 *
 * @param context The context in which the RecyclerView is created.
 * @param attrs The attribute set for the RecyclerView. (Default: null)
 * @param defStyle The default style resource ID. (Default: 0)
 * @author Antonio Di Nuzzo (Indisparte)
 */
class EmptyStateRecyclerView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0,
) : RecyclerView(context, attrs, defStyle) {

    private var emptyStateView: ViewEmptyStateBinding? = null

    private var emptyStateSubtitle: String? = null
    private var emptyStateTitle: String? = null
    private var emptyStateButtonTitle: String? = null
    private var emptyStateButtonClickListener: (() -> Unit)? = null

    private val adapterDataObserver = object : AdapterDataObserver() {
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

    init {
        val inflater = LayoutInflater.from(context)
        emptyStateView = ViewEmptyStateBinding.inflate(inflater)
        emptyStateView?.root?.gone()

        // Ottiengo gli attributi personalizzati dal contesto
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.EmptyStateRecyclerView)
        emptyStateSubtitle = typedArray.getString(R.styleable.EmptyStateRecyclerView_emptyStateSubtitle)
        emptyStateTitle = typedArray.getString(R.styleable.EmptyStateRecyclerView_emptyStateTitle)
        emptyStateButtonTitle = typedArray.getString(R.styleable.EmptyStateRecyclerView_emptyStateButtonTitle)

        typedArray.recycle()

        // Controllo se le stringhe sono vuote e rendo invisibili le viste corrispondenti
        emptyStateSubtitle?.let {
            if (it.isEmpty()) {
                emptyStateView?.tvEmptyStateSubtitle?.gone()
            } else {
                emptyStateView?.tvEmptyStateSubtitle?.visible()
                setEmptyStateSubtitle(it)
            }
        }

        emptyStateTitle?.let {
            if (it.isEmpty()) {
                emptyStateView?.tvEmptyStateTitle?.gone()
            } else {
                emptyStateView?.tvEmptyStateTitle?.visible()
                setEmptyStateTitle(it)
            }
        }

        emptyStateButtonTitle?.let {
            if (it.isEmpty()) {
                emptyStateView?.btnEmptyState?.gone()
            } else {
                emptyStateView?.btnEmptyState?.visible()
                setEmptyStateButtonTitle(it)
            }
        }


    }

    /**
     * Sets the subtitle for the empty state view.
     *
     * @param subtitle The subtitle to be displayed in the empty state view.
     */
    fun setEmptyStateSubtitle(subtitle: String) {
        emptyStateSubtitle = subtitle
        emptyStateView?.tvEmptyStateSubtitle?.text = subtitle
    }

    /**
     * Sets the title for the empty state view.
     *
     * @param title The title to be displayed in the empty state view.
     */
    fun setEmptyStateTitle(title: String) {
        emptyStateTitle = title
        emptyStateView?.tvEmptyStateTitle?.text = title
    }

    /**
     * Sets the button title for the empty state view.
     *
     * @param buttonTitle The title for the button in the empty state view.
     */
    fun setEmptyStateButtonTitle(buttonTitle: String) {
        emptyStateButtonTitle = buttonTitle
        emptyStateView?.btnEmptyState?.text = buttonTitle
    }

    /**
     * Sets the click listener for the button in the empty state view.
     *
     * @param listener The click listener to be invoked when the button in the empty state view is clicked.
     */
    fun setEmptyStateButtonClickListener(listener: () -> Unit) {
        emptyStateButtonClickListener = listener
        emptyStateView?.btnEmptyState?.setOnClickListener { listener.invoke() }
    }

    private fun checkIfEmpty() {
        val adapter = adapter
        val empty = adapter?.itemCount == 0

        if (empty) {
            emptyStateView?.root?.let { emptyView ->
                if (emptyView.parent == null) {
                    val layoutParams = LayoutParams(
                        LayoutParams.MATCH_PARENT,
                        LayoutParams.MATCH_PARENT
                    )
                    (this.parent as? ViewGroup)?.addView(emptyView, layoutParams)
                }
                emptyView.visible()

            }
        } else {
            emptyStateView?.root?.gone()
        }
    }

    override fun setAdapter(adapter: Adapter<*>?) {
        val oldAdapter = getAdapter()
        oldAdapter?.unregisterAdapterDataObserver(adapterDataObserver)

        super.setAdapter(adapter)

        adapter?.registerAdapterDataObserver(adapterDataObserver)
        adapterDataObserver.onChanged()

    }

}

