package com.example.cinemates.model

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.HorizontalScrollView
import com.bumptech.glide.Glide.init
import com.example.cinemates.R
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup


/**
 * @author Antonio Di Nuzzo (Indisparte)
 */
class HorizontalChipView<T>(
    context: Context,
    attrs: AttributeSet?
) : HorizontalScrollView(context, attrs) {

    private val chipGroup: ChipGroup
    var onChipClicked: ((T) -> Unit)? = null

    init {
        // Inflate the layout for the view
        val inflater = LayoutInflater.from(context)
        inflater.inflate(R.layout.custom_chips_view, this, true)

        // Find the ChipGroup view in the layout
        chipGroup = findViewById(R.id.chip_group)

    }

    /**
     * Sets the list of chips in the ChipGroup view with a list of objects of type T.
     * The `textGetter` lambda function is used to extract the text to display on each chip from each object in the list.
     *
     * @param chipsList The list of objects of type `T` to be displayed as chips.
     * @param textGetter A lambda function that takes an object of type `T` and returns the text to display on the corresponding chip.
     */

    fun setChipsList(chipsList: List<T>, textGetter: (T) -> String) {
        // Clear the current chips in the group
        chipGroup.removeAllViews()

        // Create and add a chip for each item in the list
        chipsList.forEach { item ->
            val chipText = textGetter(item)
            val chip = Chip(context).apply {
                text = chipText
                setOnClickListener { onChipClicked?.invoke(item) }
                // Customize the chip's appearance here if desired
            }
            chipGroup.addView(chip)
        }
    }
   /* fun setChipsList(chipsList: List<String>) {
        // Clear the current chips in the group
        chipGroup.removeAllViews()

        // Create and add a chip for each item in the list
        chipsList.forEach { chipText ->
            // Create a chip and set its text value
            val chip = Chip(context).apply {
                text = chipText
                isClickable = true
                setOnClickListener { onChipClicked?.invoke(chipText) }
            }

            // Add the chip to the group
            chipGroup.addView(chip)
        }
    }*/

}
