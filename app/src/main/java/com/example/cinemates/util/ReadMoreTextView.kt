package com.example.cinemates.util

import android.content.Context
import android.graphics.Color
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.TextPaint
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.util.AttributeSet
import android.view.View
import android.view.ViewTreeObserver.OnGlobalLayoutListener
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.content.ContextCompat
import com.example.cinemates.R


/**
 * @author Antonio Di Nuzzo (Indisparte)
 */
class ReadMoreTextView(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : AppCompatTextView(context, attrs, defStyleAttr) {
    constructor(context: Context) : this(context, null, 0)
    constructor(context: Context, attrs: AttributeSet) : this(context, attrs, 0)

    companion object {
        private const val TRIM_MODE_LINES = 0
        private const val TRIM_MODE_LENGTH = 1
        private const val DEFAULT_TRIM_LENGTH = 240
        private const val DEFAULT_TRIM_LINES = 2
        private const val INVALID_END_INDEX = -1
        private const val DEFAULT_SHOW_TRIM_EXPANDED_TEXT = false
        private const val ELLIPSIZE = "... "
    }


    private var content: CharSequence? = null
    private var bufferType: BufferType? = null
    private var readMore = true
    private var trimLength = 0
    private var trimCollapsedText: CharSequence? = null
    private var trimExpandedText: CharSequence? = null
    private var viewMoreSpan: ReadMoreClickableSpan? = null
    private var colorClickableText = 0
    private var showTrimExpandedText = false

    private var trimMode = 0
    private var lineEndIndex = 0
    private var trimLines = 0


    init {
        viewMoreSpan = ReadMoreClickableSpan()
        onGlobalLayoutLineEndIndex()
        setText()
        context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.ReadMoreTextView,
            0,
            0
        ).apply {
            try {
                trimLength =
                    getInt(R.styleable.ReadMoreTextView_trimLength, DEFAULT_TRIM_LENGTH)
                trimCollapsedText =
                    getString(R.styleable.ReadMoreTextView_trimCollapsedText)?: "Read more"
                trimExpandedText =
                    getString(R.styleable.ReadMoreTextView_trimExpandedText)?: "Read less"
                trimLines = getInt(R.styleable.ReadMoreTextView_trimLines, DEFAULT_TRIM_LINES)
                val accentColor = ContextCompat.getColor(context, R.color.vermilion_100)
                colorClickableText =
                    getColor(R.styleable.ReadMoreTextView_colorClickableText, accentColor)
                showTrimExpandedText =
                    getBoolean(
                        R.styleable.ReadMoreTextView_showTrimExpandedText,
                        DEFAULT_SHOW_TRIM_EXPANDED_TEXT
                    )
                trimMode = getInt(R.styleable.ReadMoreTextView_trimMode, TRIM_MODE_LINES)
            } finally {
                recycle()
            }
        }
    }


    private fun setText() {
        super.setText(getDisplayableText(), bufferType)
        movementMethod = LinkMovementMethod.getInstance()
        highlightColor = Color.TRANSPARENT
    }

    private fun getDisplayableText(): CharSequence? {
        return getTrimmedText(content)
    }

    override fun setText(text: CharSequence?, type: BufferType) {
        this.content = text
        bufferType = type
        setText()
    }

    private fun getTrimmedText(text: CharSequence?): CharSequence? {
        if (trimMode == TRIM_MODE_LENGTH) {
            if (text != null && text.length > trimLength) {
                return if (readMore) {
                    updateCollapsedText()
                } else {
                    updateExpandedText()
                }
            }
        }
        if (trimMode == TRIM_MODE_LINES) {
            if (text != null && lineEndIndex > 0) {
                if (readMore) {
                    if (layout.lineCount > trimLines) {
                        return updateCollapsedText()
                    }
                } else {
                    return updateExpandedText()
                }
            }
        }
        return text
    }

    private fun updateCollapsedText(): CharSequence {
        var trimEndIndex = content!!.length
        when (trimMode) {
            TRIM_MODE_LINES -> {
                trimEndIndex = lineEndIndex - (ELLIPSIZE.length + trimCollapsedText!!.length + 1)
                if (trimEndIndex < 0) {
                    trimEndIndex = trimLength + 1
                }
            }
            TRIM_MODE_LENGTH -> trimEndIndex = trimLength + 1
        }
        val s = SpannableStringBuilder(content, 0, trimEndIndex)
            .append(ELLIPSIZE)
            .append(trimCollapsedText)
        return addClickableSpan(s, trimCollapsedText!!)
    }

    private fun updateExpandedText(): CharSequence? {
        if (showTrimExpandedText) {
            val s = SpannableStringBuilder(content, 0, content!!.length).append(trimExpandedText)
            return addClickableSpan(s, trimExpandedText!!)
        }
        return content
    }

    private fun addClickableSpan(s: SpannableStringBuilder, trimText: CharSequence): CharSequence {
        s.setSpan(
            viewMoreSpan,
            s.length - trimText.length,
            s.length,
            Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        return s
    }


    private inner class ReadMoreClickableSpan : ClickableSpan() {

        override fun onClick(widget: View) {
            readMore = !readMore
            setText()
        }

        override fun updateDrawState(ds: TextPaint) {
            ds.color = colorClickableText
        }
    }

    private fun onGlobalLayoutLineEndIndex() {
        if (trimMode == TRIM_MODE_LINES) {
            viewTreeObserver.addOnGlobalLayoutListener(object : OnGlobalLayoutListener {
                override fun onGlobalLayout() {
                    val obs = viewTreeObserver
                    obs.removeOnGlobalLayoutListener(this)
                    refreshLineEndIndex()
                    setText()
                }
            })
        }
    }

    private fun refreshLineEndIndex() {
        try {
            lineEndIndex = when (trimLines) {
                0 -> {
                    layout.getLineEnd(0)
                }
                in 1..lineCount -> {
                    layout.getLineEnd(trimLines - 1)
                }
                else -> {
                    INVALID_END_INDEX
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}