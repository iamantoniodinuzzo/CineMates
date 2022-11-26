package com.indisparte.linearlayoutinfo

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.TextView
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.core.view.isVisible
import com.borjabravo.readmoretextview.ReadMoreTextView
import com.indisparte.linearlayoutinfo.databinding.LayoutLinearInfoBinding

/**
 * @author Antonio Di Nuzzo (Indisparte)
 */
class LinearInfoView constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayoutCompat(context, attrs, defStyleAttr) {
    constructor(context: Context) : this(context, null, 0)
    constructor(context: Context, attrs: AttributeSet) : this(context, attrs, 0)

    companion object {
        private const val DEFAULT_VALUE = "Not specified"
    }

    private val binding: LayoutLinearInfoBinding =
        LayoutLinearInfoBinding.inflate(LayoutInflater.from(context), this)

    val textViewTitle: TextView
        get() = binding.title
    val textViewValue: ReadMoreTextView
        get() = binding.value

    var title: String = ""
        set(value) {
            field = value
            binding.title.text = value
        }
    var defaultValue: String = DEFAULT_VALUE

    var value: String = ""
        set(value) {
            field = value
            binding.value.text = value
            if (hideIfValueEmpty)
                binding.root.isVisible = value.isNotEmpty() && value != defaultValue
        }

    private var hideIfValueEmpty: Boolean = false


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
                    getString(R.styleable.LinearInfoView_title) ?: defaultValue
                value =
                    getString(R.styleable.LinearInfoView_value) ?: defaultValue
                defaultValue =
                    getString(R.styleable.LinearInfoView_defaultValue) ?: DEFAULT_VALUE

            } finally {
                recycle()
            }
        }
    }
}