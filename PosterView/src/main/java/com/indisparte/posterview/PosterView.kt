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
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.google.android.material.card.MaterialCardView
import com.indisparte.posterview.databinding.LayoutPosterViewBinding


/**
 * Displays a [MaterialCardView] containing a poster, a title, and a numerical value indicating the rating.
 * If the values of the title and valuation are null, it provides to hide the respective views.
 * @author Antonio Di Nuzzo (Indisparte)
 */
class PosterView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet,
    defStyleAttr: Int = R.attr.materialCardViewStyle
) : MaterialCardView(context, attrs, defStyleAttr) {

    private val binding: LayoutPosterViewBinding = LayoutPosterViewBinding.inflate(
        LayoutInflater.from(context),
        this,
    )

    private val imageView: ImageView = binding.poster.image
    private val cardImageView: CardView = binding.poster.root
    private val textViewRating: TextView = binding.ratingChip.textRating
    private val cardRating: CardView = binding.ratingChip.root
    private val textViewTitle: TextView = binding.title

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
                .placeholder(placeholder)
                .error(placeholder)
                .transition(DrawableTransitionOptions.withCrossFade())
                .diskCacheStrategy(DiskCacheStrategy.DATA)
                .centerCrop()
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

    var titleSize: Float = DEFAULT_TITLE_SIZE
        set(value) {
            field = value
            textViewTitle.setTextSize(TypedValue.COMPLEX_UNIT_PX, value)
        }

    var titleColor: Int = Color.WHITE
        set(value) {
            field = value
            textViewTitle.setTextColor(value)
        }

    var titlePosterStyle: Int = Typeface.BOLD
        set(value) {
            field = value
            textViewTitle.setTypeface(textViewTitle.typeface, value)
        }

    var ratingColor: Int = Color.WHITE
        set(value) {
            field = value
            textViewRating.setTextColor(value)
        }

    var ratingSize: Float = DEFAULT_RATING_SIZE
        set(value) {
            field = value
            textViewRating.setTextSize(TypedValue.COMPLEX_UNIT_SP, value)
        }

    var ratingStyle: Int = Typeface.NORMAL
        set(value) {
            field = value
            textViewRating.setTypeface(textViewRating.typeface, value)
        }

    var ratingRadius: Float = DEFAULT_RATING_RADIUS
        set(value) {
            field = value
            cardRating.radius = value
        }

    init {
        val attributes = context.obtainStyledAttributes(
            attrs,
            R.styleable.PosterView,
            defStyleAttr,
            0
        )

        try {
            placeholder = attributes.getResourceId(
                R.styleable.PosterView_placeholder,
                R.drawable.ic_placeholder
            )
            imageSrc = attributes.getString(R.styleable.PosterView_imageSrc)
            rating = attributes.getString(R.styleable.PosterView_rating)
            title = attributes.getString(R.styleable.PosterView_title)
            titleColor = attributes.getColor(R.styleable.PosterView_titleColor, Color.WHITE)
            titleSize = attributes.getDimension(
                R.styleable.PosterView_titleSize,
                DEFAULT_TITLE_SIZE
            )
            ratingColor = attributes.getColor(R.styleable.PosterView_ratingColor, Color.WHITE)
            ratingSize = attributes.getDimension(
                R.styleable.PosterView_ratingSize,
                DEFAULT_RATING_SIZE
            )
            ratingStyle = attributes.getInt(R.styleable.PosterView_ratingStyle, Typeface.NORMAL)
            titlePosterStyle =
                attributes.getInt(R.styleable.PosterView_titlePosterStyle, Typeface.BOLD)
            posterRadius = attributes.getDimension(
                R.styleable.PosterView_posterRadius,
                DEFAULT_POSTER_RADIUS
            )
            ratingRadius =
                attributes.getDimension(R.styleable.PosterView_ratingRadius, DEFAULT_RATING_RADIUS)
        } finally {
            attributes.recycle()
        }
    }

    companion object {
        private const val DEFAULT_RATING_SIZE = 12f
        private const val DEFAULT_POSTER_RADIUS = 10f
        private const val DEFAULT_TITLE_SIZE = 18f
        private const val DEFAULT_RATING_RADIUS = 8f
    }
}
