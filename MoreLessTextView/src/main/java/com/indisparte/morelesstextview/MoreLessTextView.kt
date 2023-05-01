package com.indisparte.morelesstextview

import android.content.Context
import android.graphics.Color
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.util.AttributeSet
import android.view.View
import androidx.appcompat.widget.AppCompatTextView


/**
 * @author Antonio Di Nuzzo (Indisparte)
 */
class MoreLessTextView(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : AppCompatTextView(context, attrs, defStyleAttr) {

    constructor(context: Context) : this(context, null, 0)
    constructor(context: Context, attrs: AttributeSet) : this(context, attrs, 0)

    companion object {
        private const val READ_MORE = "Read More"
        private const val READ_LESS = "Read Less"

    }

    private var isExpanded = false

    var readMoreText: String = READ_MORE
        set(value) {
            field = value
            updateText()
        }

    var readLessText: String = READ_LESS
        set(value) {
            field = value
            updateText()
        }

    var clickableColor: Int = Color.BLUE
        set(value) {
            field = value
            updateText()
        }

    init {
        // Read custom attributes from XML
        context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.MoreLessTextView,
            defStyleAttr,
            0
        ).apply {
            try {
                readMoreText = getString(R.styleable.MoreLessTextView_readMoreText) ?: READ_MORE
                readLessText = getString(R.styleable.MoreLessTextView_readLessText) ?: READ_LESS
                clickableColor = getColor(R.styleable.MoreLessTextView_clickableColor, Color.BLUE)
            } finally {
                recycle()
            }
        }

        // Set the text and make it clickable
        movementMethod = LinkMovementMethod.getInstance()
        updateText()
    }

    private fun updateText() {
        if (maxLines <= 0) {
            return
        }

        val originalText = text.toString().trim()
        val spannable = SpannableStringBuilder(originalText)

        val lineEndIndex = layout?.getLineEnd(maxLines - 1) ?: -1
        if (lineEndIndex >= 0 && lineEndIndex < originalText.length) {
            if (!isExpanded) {
                val moreText = "... $readMoreText"
                spannable.insert(lineEndIndex, moreText)
                spannable.setSpan(object : ClickableSpan() {
                    override fun onClick(widget: View) {
                        isExpanded = true
                        updateText()
                    }
                }, lineEndIndex, lineEndIndex + moreText.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
            } else {
                val lessText = " $readLessText"
                spannable.append(lessText)
                spannable.setSpan(
                    object : ClickableSpan() {
                        override fun onClick(widget: View) {
                            isExpanded = false
                            updateText()
                        }
                    },
                    spannable.length - lessText.length,
                    spannable.length,
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                )
            }
        }

        text = spannable
    }
}