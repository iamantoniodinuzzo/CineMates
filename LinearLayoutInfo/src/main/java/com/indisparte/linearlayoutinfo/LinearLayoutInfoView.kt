package com.indisparte.linearlayoutinfo

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.util.TypedValue
import android.view.LayoutInflater
import android.widget.TextView
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import com.borjabravo.readmoretextview.ReadMoreTextView
import com.indisparte.linearlayoutinfoview.R
import com.indisparte.linearlayoutinfoview.databinding.LayoutLinearInfoBinding

/**
 * @author Antonio Di Nuzzo (Indisparte)
 */
class LinearLayoutInfoView constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayoutCompat(context, attrs, defStyleAttr) {

    constructor(context: Context) : this(context, null, 0)
    constructor(context: Context, attrs: AttributeSet) : this(context, attrs, 0)

    companion object {
        private const val DEFAULT_STRING = ""
    }

    private val binding: LayoutLinearInfoBinding =
        LayoutLinearInfoBinding.inflate(LayoutInflater.from(context), this)

    private val textViewTitle: TextView
        get() = binding.title
    private val textViewValue: ReadMoreTextView
        get() = binding.value

    var title: String = DEFAULT_STRING
        set(value) {
            field = value
            textViewTitle.text = value
        }

    var value: String = DEFAULT_STRING
        set(value) {
            field = value
            textViewValue.text = value
            if (hideIfValueEmpty)//need to hide the view?
                binding.root.isVisible = value.isNotEmpty()
        }

    /**
     * When [true], the view is hidden. Default [false]
     */
    var hideIfValueEmpty: Boolean = false
        set(value) {
            field = value
            requestLayout()
        }

    /**
     * Default value [Color.BLACK]
     */
    var textColor: Int = Color.BLACK
        set(value) {
            field = value
            textViewValue.setTextColor(value)
            textViewTitle.setTextColor(value)
        }

    /**
     * Default value 14f
     */
    var textSize: Float = 14f
        set(value) {
            field = value
            textViewTitle.setTextSize(TypedValue.COMPLEX_UNIT_SP, value)
            textViewValue.setTextSize(TypedValue.COMPLEX_UNIT_SP, value)
        }


    init {
        requireNotNull(attrs) { "attrs cannot be null" }
        context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.LinearInfoView,
            0,
            0
        ).apply {
            try {
                hideIfValueEmpty =
                    getBoolean(R.styleable.LinearInfoView_hideIfValueEmpty, false)
                title =
                    getString(R.styleable.LinearInfoView_title) ?: DEFAULT_STRING
                value =
                    getString(R.styleable.LinearInfoView_value) ?: DEFAULT_STRING
                textColor = getColor(R.styleable.LinearInfoView_textColor, Color.BLACK)
                textSize = getDimension(R.styleable.LinearInfoView_textSize, 14f)

            } finally {
                recycle()
            }
        }
    }

}