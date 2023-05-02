package com.indisparte.posterview

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.util.TypedValue
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.DrawableRes
import androidx.core.view.isVisible
import com.google.android.material.card.MaterialCardView
import com.google.android.material.chip.Chip
import com.indisparte.posterview.databinding.LayoutPosterViewBinding


/**
 * @author Antonio Di Nuzzo (Indisparte)
 */
class PosterView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = R.attr.materialCardViewStyle
) : MaterialCardView(context, attrs, defStyleAttr) {

    private val binding: LayoutPosterViewBinding by lazy {
        LayoutPosterViewBinding.inflate(
            LayoutInflater.from(context),
            this
        )
    }
    private val imageView: ImageView by lazy { binding.moviePoster }
    private val chip: Chip by lazy { binding.movieRating }
    private val textView: TextView by lazy { binding.movieTitle }

    var placeholder: Int = R.drawable.ic_placeholder
        set(value) {
            field = value
            imageView.setImageResource(placeholder)
        }

    var imageSrc: String? = null
        set(value) {
            field = value
            value?.let {
                // TODO: Add glide dependency
                /* Glide.with(context)
                     .load(it)
                     .placeholder(imageView.drawable)
                     .error(imageView.drawable)
                     .into(imageView)*/
            }
        }

    var rating: Float? = null
        set(value) {
            field = value
            value?.let {
                //TODO adding formatting float
                chip.text = it.toString()
                chip.isVisible = true
            }

        }

    var title: String? = null
        set(value) {
            field = value
            value?.let {
                textView.text = it
                textView.isVisible = true
            }
        }

    /**
     * Default value 14f
     */
    var titleSize: Float = 14f
        set(value) {
            field = value
            textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, value)
        }

    /**
     * Default value [Color.WHITE]
     */
    var titleColor: Int = Color.WHITE
        set(value) {
            field = value
            textView.setTextColor(value)
        }

    /**
     * Default value [Color.WHITE]
     */
    var ratingColor: Int = Color.WHITE
        set(value) {
            field = value
            chip.setTextColor(value)
        }


    init {
        requireNotNull(attrs) { "attrs cannot be null" }
        context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.PosterView,
            0,
            0
        ).apply {
            try {
                placeholder =
                    getResourceId(R.styleable.PosterView_placeholder, R.drawable.ic_placeholder)
                imageSrc = getString(R.styleable.PosterView_imageSrc)
                rating = getFloat(R.styleable.PosterView_rating, 0f)
                title = getString(R.styleable.PosterView_title)
                titleColor = getColor(R.styleable.PosterView_titleColor, Color.WHITE)
                titleSize = getDimension(R.styleable.PosterView_titleSize, 14f)
                ratingColor = getColor(R.styleable.PosterView_ratingColor, Color.WHITE)

            } finally {
                recycle()
            }
        }

    }

}