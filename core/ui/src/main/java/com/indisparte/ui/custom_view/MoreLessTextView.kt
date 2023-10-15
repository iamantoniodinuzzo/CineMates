package com.indisparte.ui.custom_view


/**
 * @author Antonio Di Nuzzo
 */
import android.content.Context
import android.graphics.Color
import android.graphics.Rect
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.text.buildSpannedString
import androidx.core.text.color
import androidx.core.view.doOnLayout
import androidx.core.view.isInvisible
import com.indisparte.ui.R

/**
 * A custom `TextView` that provides "Read More" and "Read Less" functionality for text
 * that exceeds a certain number of lines.
 *
 * This class allows you to display a truncated version of text with a "Read More" link.
 * When clicked, it expands the text to show the full content. If the text is expanded,
 * it offers a "Read Less" link to collapse the text back to its original truncated state.
 *
 * @property readMoreMaxLine The maximum number of lines to display when collapsed.
 * @property readMoreText The text to display for the "Read More" link.
 * @property readMoreColor The color for the "Read More" link text.
 * @property readLessText The text to display for the "Read Less" link.
 * @property readLessColor The color for the "Read Less" link text.
 * @property changeListener A listener for state change events.
 * @author Antonio Di Nuzzo
 */
class MoreLessTextView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : AppCompatTextView(context, attrs, defStyleAttr) {

    private var readMoreMaxLine = DEFAULT_MAX_LINE
    private var readMoreText = "Read more"
    private var readMoreColor = Color.BLUE
    private var readLessText = "Read less"
    private var readLessColor = Color.BLUE

    private var state: State = State.COLLAPSED
        private set(value) {
            field = value
            updateText()
            changeListener?.onStateChange(value)
        }

    private val isExpanded: Boolean
        get() = state == State.EXPANDED

    private val isCollapsed: Boolean
        get() = state == State.COLLAPSED && !isExpanded

    var changeListener: ChangeListener? = null

    private var originalText: CharSequence = ""
    private var collapseText: CharSequence = ""

    init {
        readAttributes(attrs, defStyleAttr)
        setupListener()
    }

    private fun readAttributes(attrs: AttributeSet?, defStyleAttr: Int) {
        val typedArray = context.obtainStyledAttributes(
            attrs,
            R.styleable.MoreLessTextView,
            defStyleAttr,
            0
        )

        readMoreMaxLine = typedArray.getInt(
            R.styleable.MoreLessTextView_maxLinesShown,
            DEFAULT_MAX_LINE
        )
        readMoreText = typedArray.getString(R.styleable.MoreLessTextView_readMoreText) ?: readMoreText
        readMoreColor = typedArray.getColor(
            R.styleable.MoreLessTextView_clickableColor,
            readMoreColor
        )
        readLessColor = typedArray.getColor(
            R.styleable.MoreLessTextView_clickableColor,
            readLessColor
        )
        typedArray.recycle()
    }

    private fun setupListener() {
        super.setOnClickListener { toggleState() }
    }

    private fun toggleState() {
        if (isExpanded) {
            collapse()
        } else {
            expand()
        }
    }

    private fun collapse() {
        if (isCollapsed || collapseText.isEmpty()) {
            return
        }
        state = State.COLLAPSED
    }

    private fun expand() {
        if (isExpanded || originalText.isEmpty()) {
            return
        }
        state = State.EXPANDED
    }

    override fun setText(text: CharSequence?, type: BufferType?) {
        super.setText(text, type)
        doOnLayout {
            post { setupReadMore() }
            if (isExpanded) {
                post { setupReadLess() }
            }
        }
    }

    private fun setupReadMore() {
        if (shouldSkipSetupReadMore()) {
            return
        }
        originalText = text

        val adjustCutCount = calculateAdjustCutCount(readMoreMaxLine, readMoreText)
        val maxTextIndex = layout.getLineVisibleEnd(readMoreMaxLine - 1)
        val originalSubText = originalText.substring(0, maxTextIndex - 1 - adjustCutCount)

        collapseText = buildSpannedString {
            append(originalSubText)
            color(readMoreColor) { append(" ...$readMoreText") }
        }

        updateText()
    }

    private fun setupReadLess() {
        val textWithoutReadLess = originalText.toString().removeSuffix(" $readLessText")

        val fullText = buildSpannedString {
            append(textWithoutReadLess)
            color(readLessColor) {
                append(" $readLessText")
            }
        }
        text = fullText
    }

    private fun shouldSkipSetupReadMore(): Boolean =
        isInvisible || lineCount <= readMoreMaxLine || isExpanded || text == null || text == collapseText

    private fun calculateAdjustCutCount(maxLine: Int, readMoreText: String): Int {
        val lastLineStartIndex = layout.getLineVisibleEnd(maxLine - 2) + 1
        val lastLineEndIndex = layout.getLineVisibleEnd(maxLine - 1)
        val lastLineText = text.substring(lastLineStartIndex, lastLineEndIndex)
        val bounds = Rect()
        paint.getTextBounds(lastLineText, 0, lastLineText.length, bounds)
        var adjustCutCount = -1
        do {
            adjustCutCount++
            val subText = lastLineText.substring(0, lastLineText.length - adjustCutCount)
            val replacedText = subText + readMoreText
            paint.getTextBounds(replacedText, 0, replacedText.length, bounds)
            val replacedTextWidth = bounds.width()
        } while (replacedTextWidth > width)
        return adjustCutCount
    }

    private fun updateText() {
        text = if (isExpanded) originalText else collapseText
    }

    enum class State {
        EXPANDED, COLLAPSED
    }

    interface ChangeListener {
        fun onStateChange(state: State)
    }

    companion object {
        private const val DEFAULT_MAX_LINE = 4
    }
}
