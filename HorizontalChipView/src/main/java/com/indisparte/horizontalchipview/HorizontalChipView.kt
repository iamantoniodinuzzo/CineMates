package com.indisparte.horizontalchipview

import android.content.Context
import android.graphics.Color
import android.graphics.Typeface
import android.util.AttributeSet
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import androidx.annotation.ColorInt
import androidx.annotation.IdRes
import androidx.annotation.StyleRes
import androidx.appcompat.widget.LinearLayoutCompat
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipDrawable
import com.google.android.material.chip.ChipGroup
import com.indisparte.horizontalchipview.databinding.LayoutHorizontalChipviewBinding


/**
 * Show a vertical [LinearLayoutCompat] section containing an optional title and a horizontal chip view.
 *     <b>The orientation cannot be changed but will remain vertical</b>
 * @author Antonio Di Nuzzo (Indisparte)
 */
class HorizontalChipView<T>(
    context: Context,
    attrs: AttributeSet?,
    defStyleAttr: Int = 0
) : LinearLayoutCompat(context, attrs, defStyleAttr) {

    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet) : this(context, attrs, 0)

    private val binding: LayoutHorizontalChipviewBinding by lazy {
        LayoutHorizontalChipviewBinding.inflate(
            LayoutInflater.from(
                context
            ), this
        )
    }

    private val chipGroup: ChipGroup by lazy { binding.chipGroup }
    private val textViewTitle: TextView by lazy { binding.title }

    @StyleRes
    var chipStyle: Int? = null

    var onChipClicked: ((T) -> Unit)? = null

    var title: String = ""
        set(value) {
            field = value
            textViewTitle.text = value
            textViewTitle.visibility = if (value.isEmpty()) View.GONE else View.VISIBLE
        }

    /**
     * Change text color, default value [Color.WHITE]
     */
    @ColorInt
    var textColor: Int = Color.WHITE
        set(value) {
            field = value
            textViewTitle.setTextColor(value)
        }

    /**
     * Change text style, default value [Typeface.BOLD]
     */
    var textStyle: Int = Typeface.BOLD
        set(value) {
            field = value
            textViewTitle.setTypeface(textViewTitle.typeface, value)
        }

    /**
     * Change text typeface
     */
    var typefacePath: String? = null
        set(value) {
            field = value
            value?.let {
                val typeface = Typeface.createFromAsset(context.assets, it)
                textViewTitle.setTypeface(typeface, textStyle)
            }
        }

    /**
     * Change text size, default value 14f
     */
    var textSize: Float = 14f
        set(value) {
            field = value
            textViewTitle.setTextSize(TypedValue.COMPLEX_UNIT_PX, value)
        }

    var requireSelection: Boolean = false
        set(value) {
            field = value
            chipGroup.isSelectionRequired = value
        }

    var singleCheck: Boolean = false
        set(value) {
            field = value
            chipGroup.isSingleSelection = value
        }

    @IdRes
    var selectedChipId: Int? = null
        set(value) {
            field = value
            value?.let {
                chipGroup.check(it)
            }
        }

    init {
        requireNotNull(attrs) { "attrs cannot be null" }
        context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.HorizontalChipView,
            defStyleAttr,
            0
        ).apply {
            try {
                title = getString(R.styleable.HorizontalChipView_title) ?: ""
                textColor = getColor(R.styleable.HorizontalChipView_titleColor, Color.WHITE)
                textSize = getDimension(R.styleable.HorizontalChipView_titleSize, 14f)
                textStyle = getInt(R.styleable.HorizontalChipView_titleStyle, Typeface.BOLD)
                typefacePath = getString(R.styleable.HorizontalChipView_titleTypeface)
                orientation = VERTICAL // default orientation, immutable
                requireSelection = getBoolean(
                    R.styleable.HorizontalChipView_requireSelection, false
                )
                singleCheck = getBoolean(R.styleable.HorizontalChipView_singleCheck, false)
                selectedChipId = getResourceId(R.styleable.HorizontalChipView_selectedChipId, -1)
                chipStyle = getResourceId(R.styleable.HorizontalChipView_chipStyle, -1)
            } finally {
                recycle()
            }

        }
    }

    /**
     * Orientation must be vertical
     */
    override fun setOrientation(orientation: Int) {
        super.setOrientation(VERTICAL)
    }

    /**
     * Sets the list of chips in the ChipGroup view with a list of objects of type T.
     * The `textGetter` lambda function is used to extract the text to display on each chip from each object in the list.
     *
     * @param chipsList The list of objects of type `T` to be displayed as chips.
     * @param textGetter A lambda function that takes an object of type `T` and returns the text to display on the corresponding chip.
     */
    fun setChipsList(chipsList: List<T>, textGetter: (T) -> String) {
        val chipDrawable = chipStyle?.let {
            ChipDrawable.createFromAttributes(context, null, 0, it)
        }
//        chipDrawable?.let { chipGroup. }// add style if exists
        // Remove any views that are not needed
        while (chipGroup.childCount > chipsList.size) {
            chipGroup.removeViewAt(chipsList.size)
        }

        // Reuse existing views
        for (i in chipsList.indices) {
            val chip = if (i < chipGroup.childCount) {
                chipGroup.getChildAt(i) as? Chip
            } else {
                null
            }

            val item = chipsList[i]
            val chipText = textGetter(item)

            if (chip == null) {
                val newChip = Chip(context).apply {
                    setOnClickListener { onChipClicked?.invoke(item) }
                    // Customize the chip's appearance here if desired
                    chipDrawable?.let { setChipDrawable(it) }
                }
                chipGroup.addView(newChip)
                newChip.text = chipText
            } else {
                chip.text = chipText
                chip.setOnClickListener { onChipClicked?.invoke(item) }
                chipDrawable?.let { chip.setChipDrawable(it) }// add style if exists
            }
        }
    }

    var chipAttributes: ((Chip) -> Unit)? = null
        set(value) {
            field = value
            value?.let {
                for (i in 0 until chipGroup.childCount) {
                    val chip = chipGroup.getChildAt(i) as Chip
                    it(chip)
                }
            }
        }


    fun setChipGroupAttributes(
        chipSpacingHorizontal: Float? = null,
        selectionRequired: Boolean? = null,
        checkedChip: Int? = null,
        singleSelection: Boolean? = null
    ) {
        chipSpacingHorizontal?.let { chipGroup.chipSpacingHorizontal = it.toInt() }
        selectionRequired?.let { chipGroup.isSelectionRequired = it }
        checkedChip?.let { chipGroup.check(it) }
        singleSelection?.let { chipGroup.isSingleSelection = it }
    }


}
