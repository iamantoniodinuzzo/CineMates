package com.indisparte.ui.custom_view

import android.content.Context
import android.graphics.Color
import android.graphics.Typeface
import android.util.AttributeSet
import android.util.TypedValue
import android.view.LayoutInflater
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.view.isVisible
import com.indisparte.ui.R
import com.indisparte.ui.databinding.LayoutLinearInfoBinding


class LinearLayoutInfoView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
) : LinearLayout(context, attrs, defStyleAttr) {

    companion object {
        private const val DEFAULT_STRING = ""
    }

    private val binding: LayoutLinearInfoBinding =
        LayoutLinearInfoBinding.inflate(LayoutInflater.from(context), this)

    private val textViewTitle: TextView
        get() = binding.title
    private val textViewValue: MoreLessTextView
        get() = binding.value

    var title: String = DEFAULT_STRING
        set(value) {
            field = value
            textViewTitle.text = value
        }

    var titleStyle: Int = Typeface.BOLD
        set(value) {
            field = value
            textViewTitle.setTypeface(textViewTitle.typeface, value)
        }

    var valueStyle: Int = Typeface.NORMAL
        set(value) {
            field = value
            textViewValue.setTypeface(textViewValue.typeface, value)
        }

    var value: String? = DEFAULT_STRING
        set(value) {
            field = value
            textViewValue.text = value
            if (hideIfValueEmpty) {
                isVisible = !value.isNullOrEmpty()
            }
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
            textViewTitle.setTextSize(TypedValue.COMPLEX_UNIT_PX, value)
            textViewValue.setTextSize(TypedValue.COMPLEX_UNIT_PX, value)
        }

    init {
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
                titleStyle = getInt(R.styleable.LinearInfoView_textTitleStyle, Typeface.BOLD)
                valueStyle = getInt(R.styleable.LinearInfoView_textValueStyle, Typeface.NORMAL)
            } finally {
                recycle()
            }
        }

        orientation = VERTICAL
    }
}
