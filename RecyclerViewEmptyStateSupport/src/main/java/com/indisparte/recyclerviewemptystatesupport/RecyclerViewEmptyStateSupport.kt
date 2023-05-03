package com.indisparte.recyclerviewemptystatesupport

import android.content.Context
import android.graphics.Color
import android.graphics.Typeface
import android.util.AttributeSet
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.annotation.ColorInt
import androidx.recyclerview.widget.RecyclerView
import com.indisparte.recyclerviewemptystatesupport.databinding.LayoutRecyclerviewEmptyStateBinding


/**
 * A recyclerview attentive to empty states by presenting the user with a customizable empty view
 * @author Antonio Di Nuzzo (Indisparte)
 */
class RecyclerViewEmptyStateSupport @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : RelativeLayout(context, attrs, defStyleAttr) {

    // Use ViewBinding to access views
    private val binding: LayoutRecyclerviewEmptyStateBinding =
        LayoutRecyclerviewEmptyStateBinding.inflate(LayoutInflater.from(context), this)

    // Get references to views using ViewBinding
    private val emptyView: LinearLayout
        get() = binding.emptyView
    private val emptyTextView: TextView
        get() = binding.noContentText
    private val emptyImageView: ImageView
        get() = binding.noContentImage
    private val recyclerView: RecyclerView
        get() = binding.recyclerView

    // Default values for icon size and text size
    private companion object {
        private const val DEFAULT_TEXT_SIZE = 18f
        private const val DEFAULT_ICON_SIZE = 200f
    }

    // Properties for customizing the empty state view
    var iconWidth: Float = DEFAULT_ICON_SIZE
        set(value) {
            field = value
            emptyImageView.layoutParams.width = value.toInt()
        }

    var iconHeight: Float = DEFAULT_ICON_SIZE
        set(value) {
            field = value
            emptyImageView.layoutParams.height = value.toInt()
        }

    var emptyText: String = context.getString(R.string.empty_result_message)
        set(value) {
            field = value
            emptyTextView.text = value
        }

    var emptyTextSize: Float = DEFAULT_TEXT_SIZE
        set(value) {
            field = value
            emptyTextView.setTextSize(TypedValue.COMPLEX_UNIT_PX, value)
        }

    @ColorInt
    var emptyTextColor: Int = Color.WHITE
        set(value) {
            field = value
            emptyTextView.setTextColor(value)
        }

    var emptyTextStyle: Int = Typeface.BOLD
        set(value) {
            field = value
            emptyTextView.setTypeface(emptyTextView.typeface, value)
        }

    var emptyTextTypeface: String? = null
        set(value) {
            field = value
            value?.let {
                val typeface = Typeface.createFromAsset(context.assets, it)
                emptyTextView.setTypeface(typeface, emptyTextStyle)
            }
        }

    var adapter: RecyclerView.Adapter<*>? = null
        set(value) {
            field = value
            recyclerView.adapter = value
            value?.registerAdapterDataObserver(adapterDataObserver)
            adapterDataObserver.onChanged()
        }

    var layoutManager: RecyclerView.LayoutManager? = null
        set(value) {
            field = value
            recyclerView.layoutManager = value
        }

    var icon: Int = R.drawable.ic_empty
        set(value) {
            field = value
            emptyImageView.setImageResource(value)
        }

    // Observer to update the empty state view when the adapter data changes
    private val adapterDataObserver = object : RecyclerView.AdapterDataObserver() {
        override fun onChanged() {
            super.onChanged()
            updateEmptyState()
        }

        override fun onItemRangeInserted(positionStart: Int, itemCount: Int) {
            super.onItemRangeInserted(positionStart, itemCount)
            updateEmptyState()
        }

        override fun onItemRangeRemoved(positionStart: Int, itemCount: Int) {
            super.onItemRangeRemoved(positionStart, itemCount)
            updateEmptyState()
        }
    }

    init {
        // Use named arguments and @JvmOverloads to generate constructors
        context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.RecyclerViewEmptyStateSupport,
            defStyleAttr,
            0
        ).apply {
            try {
                emptyText =
                    getString(R.styleable.RecyclerViewEmptyStateSupport_emptyText) ?: emptyText
                emptyTextSize = getDimension(
                    R.styleable.RecyclerViewEmptyStateSupport_emptyTextSize,
                    emptyTextSize
                )
                emptyTextColor =
                    getColor(R.styleable.RecyclerViewEmptyStateSupport_emptyTextColor, Color.WHITE)
                icon = getResourceId(
                    R.styleable.RecyclerViewEmptyStateSupport_icon,
                    R.drawable.ic_empty
                )
                iconWidth = getDimension(
                    R.styleable.RecyclerViewEmptyStateSupport_iconWidth,
                    DEFAULT_ICON_SIZE
                )
                iconHeight = getDimension(
                    R.styleable.RecyclerViewEmptyStateSupport_iconHeight,
                    DEFAULT_ICON_SIZE
                )
                emptyTextStyle =
                    getInt(R.styleable.RecyclerViewEmptyStateSupport_emptyTextStyle, Typeface.BOLD)
                emptyTextTypeface =
                    getString(R.styleable.RecyclerViewEmptyStateSupport_emptyTextTypeFace)
            } finally {
                recycle()
            }
        }
    }

    // Method to update the empty state view based on the adapter data
    private fun updateEmptyState() {
        if (adapter?.itemCount == 0) {
            emptyView.visibility = View.VISIBLE
            recyclerView.visibility = View.GONE
        } else {
            emptyView.visibility = View.GONE
            recyclerView.visibility = View.VISIBLE
        }
    }
}