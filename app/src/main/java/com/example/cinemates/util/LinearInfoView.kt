package com.example.cinemates.util

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.TextView
import androidx.appcompat.widget.LinearLayoutCompat
import com.bumptech.glide.Glide.init
import com.example.cinemates.R
import com.example.cinemates.databinding.LayoutLinearInfoBinding

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

    private val binding: LayoutLinearInfoBinding =
        LayoutLinearInfoBinding.inflate(LayoutInflater.from(context), this, true)

    val titleTextView: TextView
        get() = binding.title
    val valueTextView: TextView
        get() = binding.value

    var title: String = ""
        set(value) {
            field = value
            binding.title.text = value
        }

    var value: String = ""
        set(value) {
            field = value
            binding.value.text = value
        }


    init {
        orientation = HORIZONTAL
        context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.LinearInfoView,
            0,
            0
        ).apply {
            try {
                title =
                    getString(R.styleable.LinearInfoView_title) ?: "Not specified"
                value =
                    getString(R.styleable.LinearInfoView_value) ?: "Not specified"
            } finally {
                recycle()
            }
        }
    }
}