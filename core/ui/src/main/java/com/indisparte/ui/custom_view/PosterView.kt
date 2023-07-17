package com.indisparte.ui.custom_view

import android.content.Context
import android.graphics.Outline
import android.os.Build
import android.util.AttributeSet
import android.util.TypedValue
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewOutlineProvider
import android.widget.FrameLayout
import androidx.annotation.StyleRes
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.google.android.material.card.MaterialCardView
import com.indisparte.ui.R
import com.indisparte.ui.databinding.PosterViewBinding
import kotlin.math.ceil


/**
 * Displays a [MaterialCardView] containing a poster, a title, and a numerical value indicating the rating.
 * If the values of the title and valuation are null, it provides to hide the respective views.
 * @author Antonio Di Nuzzo (Indisparte)
 */
class PosterView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
) : FrameLayout(context, attrs, defStyleAttr) {

    private lateinit var binding: PosterViewBinding

    init {
        initializeView()
        applyDefaultValues()
        applyCustomAttributes(attrs, defStyleAttr)
    }

    private fun initializeView() {
        binding = PosterViewBinding.inflate(LayoutInflater.from(context), this)
    }

    private fun applyDefaultValues() {
        val defaultTitleSize = resources.getDimension(R.dimen.default_poster_title_size)
        val defaultTitleColor = ContextCompat.getColor(context, R.color.geyser)
        val defaultChipTextSize = resources.getDimension(R.dimen.default_chip_text_size)
        val defaultChipTextColor = ContextCompat.getColor(context, R.color.vermilion_100)
        val defaultChipStyle = R.style.CustomChipStyle
        val defaultPosterWidth = resources.getDimensionPixelSize(R.dimen.default_poster_width)
        val defaultPosterHeight = resources.getDimensionPixelSize(R.dimen.default_poster_height)
        val defaultCornerRadius = resources.getDimension(R.dimen.base_corner_radius)
        val background = ContextCompat.getDrawable(context, R.drawable.poster_view_selector)
        isClickable = true

        setTitleSize(defaultTitleSize)
        setTitleColor(defaultTitleColor)
        setChipTextSize(defaultChipTextSize)
        setChipTextColor(defaultChipTextColor)
        setChipStyle(defaultChipStyle)
        setPosterSize(defaultPosterWidth, defaultPosterHeight)
        setLayoutParams(defaultPosterWidth, defaultPosterHeight)
        foreground = background
        setCornerRadius(defaultCornerRadius)
    }

    private fun applyCustomAttributes(attrs: AttributeSet?, defStyleAttr: Int) {
        attrs?.let { attributeSet ->
            val typedArray = context.obtainStyledAttributes(
                attributeSet,
                R.styleable.PosterView,
                defStyleAttr,
                0
            )

            val title = typedArray.getString(R.styleable.PosterView_pv_title)
            val titleSize = typedArray.getDimension(
                R.styleable.PosterView_pv_title_size,
                resources.getDimension(R.dimen.default_poster_title_size)
            )
            val titleColor = typedArray.getColor(
                R.styleable.PosterView_pv_title_color,
                ContextCompat.getColor(context, R.color.geyser)
            )
            val chipValue = typedArray.getFloat(R.styleable.PosterView_pv_chipValue, -1f)
            val imageUrl = typedArray.getString(R.styleable.PosterView_pv_imageUrl)
            val cornerRadius = typedArray.getDimension(
                R.styleable.PosterView_pv_cornerRadius,
                resources.getDimension(R.dimen.base_corner_radius)
            )
            val posterWidth = typedArray.getDimensionPixelSize(
                R.styleable.PosterView_pv_posterWidth,
                resources.getDimensionPixelSize(R.dimen.default_poster_width)
            )
            val posterHeight = typedArray.getDimensionPixelSize(
                R.styleable.PosterView_pv_posterHeight,
                resources.getDimensionPixelSize(R.dimen.default_poster_height)
            )

            if (!title.isNullOrEmpty()) {
                setTitle(title)
            } else {
                hideTitle()
            }

            if (chipValue >= 0) {
                setChipValue(chipValue.toDouble())
            } else {
                hideChip()
            }

            if (!imageUrl.isNullOrEmpty()) {
                loadImage(imageUrl)
            }

            setCornerRadius(cornerRadius)
            setPosterSize(posterWidth, posterHeight)
            setTitleSize(titleSize)
            setTitleColor(titleColor)
            setLayoutParams(posterWidth, posterHeight)
            isClickable = true

            typedArray.recycle()
        }
    }

    fun setTitle(title: String) {
        binding.titleTextView.text = title
        binding.titleTextView.isVisible = true
    }

    fun hideTitle() {
        binding.titleTextView.isVisible = false
    }

    fun setTitleSize(size: Float) {
        binding.titleTextView.setTextSize(TypedValue.COMPLEX_UNIT_PX, size)
    }

    fun setTitleColor(color: Int) {
        binding.titleTextView.setTextColor(color)
    }

    fun setChipValue(value: Double) {
        val roundedValue =
            ceil(value * 10) / 10 // Approssima per eccesso con una cifra decimale
        binding.chipTextView.text = roundedValue.toString()
        binding.chipTextView.isVisible = true
    }


    fun hideChip() {
        binding.chipTextView.isVisible = false
    }

    fun setChipTextSize(size: Float) {
        binding.chipTextView.setTextSize(TypedValue.COMPLEX_UNIT_PX, size)
    }

    fun setChipTextColor(color: Int) {
        binding.chipTextView.setTextColor(color)
    }

    fun setChipStyle(@StyleRes styleRes: Int) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            binding.chipTextView.setTextAppearance(styleRes)
        } else {
            @Suppress("DEPRECATION")
            binding.chipTextView.setTextAppearance(context, styleRes)
        }
    }


    fun loadImage(imageUrl: String?) {
        Glide.with(context)
            .load(imageUrl)
            .error(R.drawable.ic_broken_image)
            .placeholder(R.drawable.ic_image)
            .transition(DrawableTransitionOptions.withCrossFade())
            .diskCacheStrategy(DiskCacheStrategy.DATA)
            .into(binding.posterImageView)
    }

    fun setPosterSize(width: Int, height: Int) {
        val layoutParams = binding.posterImageView.layoutParams
        layoutParams.width = width
        layoutParams.height = height
        binding.posterImageView.layoutParams = layoutParams
    }

    fun setCornerRadius(radius: Float) {
        binding.posterImageView.clipToOutline = true
        binding.posterImageView.outlineProvider = object : ViewOutlineProvider() {
            override fun getOutline(view: View, outline: Outline) {
                outline.setRoundRect(0, 0, view.width, view.height, radius)
            }
        }
    }

    private fun setLayoutParams(width: Int, height: Int) {
        val layoutParams = FrameLayout.LayoutParams(width, height)
        layoutParams.gravity = Gravity.CENTER
        setLayoutParams(layoutParams)
    }

}
