package com.indisparte.posterview

import android.content.Context
import android.graphics.Color
import android.graphics.Typeface
import android.util.AttributeSet
import android.util.TypedValue
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.view.isVisible
import com.bumptech.glide.Glide
import com.google.android.material.card.MaterialCardView
import com.indisparte.posterview.databinding.LayoutPosterViewBinding


/**
 * @author Antonio Di Nuzzo (Indisparte)
 */
class PosterView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = R.attr.materialCardViewStyle
) : MaterialCardView(context, attrs, defStyleAttr) {

    private companion object {
        private const val DEFAULT_RATING_SIZE = 12f
        private const val DEFAULT_POSTER_RADIUS = 10f
        private const val DEFAULT_TITLE_SIZE = 18f
        private const val DEFAULT_RATING_RADIUS = 8f
    }

    private val binding: LayoutPosterViewBinding by lazy {
        LayoutPosterViewBinding.inflate(
            LayoutInflater.from(context),
            this
        )
    }
    private val imageView: ImageView by lazy { binding.poster.image }
    private val cardImageView: CardView by lazy { binding.poster.root }
    private val textViewRating: TextView by lazy { binding.ratingChip.textRating }
    private val cardRating: CardView by lazy { binding.ratingChip.root }
    private val textViewTitle: TextView by lazy { binding.title }

    var placeholder: Int = R.drawable.ic_placeholder
        set(value) {
            field = value
            imageView.setImageResource(placeholder)
        }

    var posterRadius: Float = DEFAULT_POSTER_RADIUS
        set(value) {
            field = value
            cardImageView.radius = value
        }

    var imageSrc: String? = null
        set(value) {
            field = value
            Glide.with(context)
                .load(value)
                .placeholder(imageView.drawable)
                .error(imageView.drawable)
                .into(imageView)

        }

    var rating: String? = null
        set(value) {
            field = value
            if (value != null) {
                textViewRating.text = value.toString()
                textViewRating.isVisible = true
            } else {
                textViewRating.isVisible = false
            }

        }

    var title: String? = null
        set(value) {
            field = value
            if (value != null) {
                textViewTitle.text = value
                textViewTitle.isVisible = true
            } else {
                textViewTitle.isVisible = false
            }

        }

    /**
     * Change title size, default value 18f
     */
    var titleSize: Float = DEFAULT_TITLE_SIZE
        set(value) {
            field = value
            textViewTitle.setTextSize(TypedValue.COMPLEX_UNIT_PX, value)
        }

    /**
     * Default value [Color.WHITE]
     */
    var titleColor: Int = Color.WHITE
        set(value) {
            field = value
            textViewTitle.setTextColor(value)
        }

    /**
     * Change title style, default value [Typeface.BOLD]
     */
    var titlePosterStyle: Int = Typeface.BOLD
        set(value) {
            field = value
            textViewTitle.setTypeface(textViewTitle.typeface, value)
        }

    /**
     * Default value [Color.WHITE]
     */
    var ratingColor: Int = Color.WHITE
        set(value) {
            field = value
            textViewRating.setTextColor(value)
        }

    /**
     * Change rating size, default value 12f
     */
    var ratingSize: Float = DEFAULT_RATING_SIZE
        set(value) {
            field = value
            textViewRating.setTextSize(TypedValue.COMPLEX_UNIT_SP, value)
        }

    /**
     * Change rating style, default value [Typeface.NORMAL]
     */
    var ratingStyle: Int = Typeface.NORMAL
        set(value) {
            field = value
            textViewRating.setTypeface(textViewTitle.typeface, value)
        }

    var ratingRadius: Float = DEFAULT_RATING_RADIUS
        set(value) {
            field = value
            cardRating.radius = value
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
                rating = getString(R.styleable.PosterView_rating)
                title = getString(R.styleable.PosterView_title)
                titleColor = getColor(R.styleable.PosterView_titleColor, Color.WHITE)
                titleSize = getDimension(R.styleable.PosterView_titleSize, DEFAULT_TITLE_SIZE)
                ratingColor = getColor(R.styleable.PosterView_ratingColor, Color.WHITE)
                ratingSize = getDimension(R.styleable.PosterView_ratingSize, DEFAULT_RATING_SIZE)
                ratingStyle = getInt(R.styleable.PosterView_ratingStyle, Typeface.NORMAL)
                titlePosterStyle = getInt(R.styleable.PosterView_titlePosterStyle, Typeface.BOLD)
                posterRadius = getDimension(
                    R.styleable.PosterView_posterRadius,
                    DEFAULT_POSTER_RADIUS
                )
                ratingRadius =
                    getDimension(R.styleable.PosterView_ratingRadius, DEFAULT_RATING_RADIUS)
            } finally {
                recycle()
            }
        }

    }

}