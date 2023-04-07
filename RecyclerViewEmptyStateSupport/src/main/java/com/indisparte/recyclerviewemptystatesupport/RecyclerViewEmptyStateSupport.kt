package com.indisparte.recyclerviewemptystatesupport

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.appcompat.content.res.AppCompatResources
import androidx.recyclerview.widget.RecyclerView
import com.indisparte.recyclerviewemptystatesupport.databinding.RecyclerViewEmptyStateSupportBinding


/**
 * @author Antonio Di Nuzzo (Indisparte)
 */
@SuppressLint("NewApi")
class RecyclerViewEmptyStateSupport constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : RelativeLayout(context, attrs, defStyleAttr) {
    constructor(context: Context) : this(context, null, 0)
    constructor(context: Context, attrs: AttributeSet) : this(context, attrs, 0)

    private val binding: RecyclerViewEmptyStateSupportBinding =
        RecyclerViewEmptyStateSupportBinding.inflate(
            LayoutInflater.from(context), this
        )

    private val emptyView: LinearLayout
        get() = binding.emptyView
    private val emptyTextView: TextView
        get() = binding.noContentText
    private val emptyImageView: ImageView
        get() = binding.noContentImage
    private val recyclerViewEmptyState: RecyclerViewEmptyState
        get() = binding.recyclerView

    private var emptyText: String = context.getString(R.string.empty_result_message)
        set(value) {
            field = value
            emptyTextView.text = value
        }

    private var emptyTextSize: Float = 18f
        set(value) {
            field = value
            emptyTextView.textSize = value
        }

    private var emptyTextColor: Int = context.getColor(R.color.white)
        set(value) {
            field = value
            emptyTextView.setTextColor(value)
        }

    var adapter: RecyclerView.Adapter<*>? = null
        set(value) {
            field = value
            recyclerViewEmptyState.adapter = value
        }
    var layoutManager: RecyclerView.LayoutManager? = null
        set(value) {
            field = value
            recyclerViewEmptyState.layoutManager = value
        }

    var icon: Drawable? = null
    set(value){
        field =value
    }

    init {
        context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.RecyclerViewEmptyStateSupport,
            0,
            0
        ).apply {
            try {

                emptyText =
                    getString(R.styleable.RecyclerViewEmptyStateSupport_emptyText) ?: emptyText
                emptyTextSize =
                    getDimension(
                        R.styleable.RecyclerViewEmptyStateSupport_emptyTextSize,
                        emptyTextSize
                    )
                emptyTextColor =
                    getColor(
                        R.styleable.RecyclerViewEmptyStateSupport_emptyTextColor,
                        context.getColor(R.color.white)
                    )

               /* val drawableResId = getResourceId(R.styleable.RecyclerViewEmptyStateSupport_icon, -1);
                icon = AppCompatResources.getDrawable(context, drawableResId)*/

            } finally {
                recycle()
            }
        }

        recyclerViewEmptyState.setEmptyView(emptyView)
    }


}