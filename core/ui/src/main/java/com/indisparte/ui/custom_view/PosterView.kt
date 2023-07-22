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
import com.indisparte.ui.R
import com.indisparte.ui.databinding.PosterViewBinding


/**
 * Custom view for displaying a poster with a title and optional chip value.
 *
 * This view provides functionality for customizing the appearance of the poster,
 * such as setting the title, chip value, image, corner radius, and dimensions.
 *
 * @param context The context of the view.
 * @param attrs The attribute set containing custom attributes for the view.
 * @param defStyleAttr The default style attribute for the view.
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

    /**
     * Initializes the view by inflating the layout and setting up the binding.
     */
    private fun initializeView() {
        binding = PosterViewBinding.inflate(LayoutInflater.from(context), this)
    }

    /**
     * Applies default values to the view's properties, such as title size, color, chip style,
     * poster dimensions, corner radius, and background.
     */
    private fun applyDefaultValues() {
        // Set default values for properties
        val defaultTitleSize = resources.getDimension(R.dimen.default_poster_title_size)
        val defaultTitleColor = ContextCompat.getColor(context, R.color.geyser)
        val defaultChipTextSize = resources.getDimension(R.dimen.default_chip_text_size)
        val defaultChipTextColor = ContextCompat.getColor(context, R.color.vermilion_100)
        val defaultPosterWidth = resources.getDimensionPixelSize(R.dimen.default_poster_width)
        val defaultPosterHeight = resources.getDimensionPixelSize(R.dimen.default_poster_height)
        val defaultCornerRadius = resources.getDimension(R.dimen.base_corner_radius)
        val background = ContextCompat.getDrawable(context, R.drawable.poster_view_selector)
        isClickable = true

        // Apply default values to the view

        setTitleSize(defaultTitleSize)
        setTitleColor(defaultTitleColor)
        setChipTextSize(defaultChipTextSize)
        setChipTextColor(defaultChipTextColor)
        setPosterSize(defaultPosterWidth, defaultPosterHeight)
        setLayoutParams(defaultPosterWidth, defaultPosterHeight)
        foreground = background
        setCornerRadius(defaultCornerRadius)
    }

    /**
     * Applies custom attributes to the view based on the provided attribute set.
     *
     * @param attrs The attribute set containing custom attributes for the view.
     * @param defStyleAttr The default style attribute for the view.
     */
    private fun applyCustomAttributes(attrs: AttributeSet?, defStyleAttr: Int) {
        // Retrieve custom attributes from the attribute set
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
            val chipValue = typedArray.getString(R.styleable.PosterView_pv_chipValue)
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
            // Retrieve the custom style attribute for the title text
            val titleStyle = typedArray.getResourceId(
                R.styleable.PosterView_pv_title_style,
                0
            )

            if (titleStyle != 0) {
                setTitleStyle(titleStyle)
            }

            if (!title.isNullOrEmpty()) {
                setTitle(title)
            } else {
                hideTitle()
            }

            if (!chipValue.isNullOrEmpty()) {
                setChipValue(chipValue)
            } else {
                hideChip()
            }

            if (!imageUrl.isNullOrEmpty()) {
                loadImage(imageUrl)
            }

            // Apply custom attributes to the view
            setCornerRadius(cornerRadius)
            setPosterSize(posterWidth, posterHeight)
            setTitleSize(titleSize)
            setTitleColor(titleColor)
            setLayoutParams(posterWidth, posterHeight)
            isClickable = true

            typedArray.recycle()
        }
    }


    /**
     * Sets the title of the poster.
     *
     * @param title The title to be displayed.
     */
    fun setTitle(title: String) {
        binding.titleTextView.text = title
        binding.titleTextView.isVisible = true
    }

    /**
     * Hides the title of the poster.
     */
    fun hideTitle() {
        binding.titleTextView.isVisible = false
    }

    /**
     * Sets the size of the title text.
     *
     * @param size The size of the title text.
     */
    fun setTitleSize(size: Float) {
        binding.titleTextView.setTextSize(TypedValue.COMPLEX_UNIT_PX, size)
    }

    /**
     * Sets the color of the title text.
     *
     * @param color The color of the title text.
     */
    fun setTitleColor(color: Int) {
        binding.titleTextView.setTextColor(color)
    }

    fun setTitleStyle(@StyleRes styleRes: Int) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            binding.titleTextView.setTextAppearance(styleRes)
        } else {
            @Suppress("DEPRECATION")
            binding.titleTextView.setTextAppearance(context, styleRes)
        }
    }

    /**
     * Sets the value of the chip displayed on the poster.
     *
     * @param value The value to be displayed on the chip.
     */
    fun setChipValue(value: String) {
        binding.chipTextView.text = value
        binding.chipTextView.isVisible = true
    }

    /**
     * Hides the chip displayed on the poster.
     */
    fun hideChip() {
        binding.chipTextView.isVisible = false
    }

    /**
     * Sets the size of the chip text.
     *
     * @param size The size of the chip text.
     */
    fun setChipTextSize(size: Float) {
        binding.chipTextView.setTextSize(TypedValue.COMPLEX_UNIT_PX, size)
    }

    /**
     * Sets the color of the chip text.
     *
     * @param color The color of the chip text.
     */
    fun setChipTextColor(color: Int) {
        binding.chipTextView.setTextColor(color)
    }

    /**
     * Sets the style of the chip text.
     *
     * @param styleRes The style resource for the chip text.
     */
    fun setChipStyle(@StyleRes styleRes: Int) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            binding.chipTextView.setTextAppearance(styleRes)
        } else {
            @Suppress("DEPRECATION")
            binding.chipTextView.setTextAppearance(context, styleRes)
        }
    }

    /**
     * Loads and displays an image in the poster view.
     *
     * @param imageUrl The URL of the image to be loaded.
     */
    fun loadImage(imageUrl: String?) {
        Glide.with(context)
            .load(imageUrl)
            .error(R.drawable.ic_broken_image)
            .placeholder(R.drawable.ic_image)
            .transition(DrawableTransitionOptions.withCrossFade())
            .diskCacheStrategy(DiskCacheStrategy.DATA)
            .into(binding.posterImageView)
    }


    /**
     * Sets the corner radius of the poster view.
     *
     * @param radius The corner radius of the poster view.
     */
    fun setCornerRadius(radius: Float) {
        binding.posterImageView.clipToOutline = true
        binding.posterImageView.outlineProvider = object : ViewOutlineProvider() {
            override fun getOutline(view: View, outline: Outline) {
                outline.setRoundRect(0, 0, view.width, view.height, radius)
            }
        }
    }

    /**
     * Sets the layout parameters of the poster view.
     *
     * @param width The width of the poster view.
     * @param height The height of the poster view.
     */
    private fun setLayoutParams(width: Int, height: Int) {
        val layoutParams = FrameLayout.LayoutParams(width, height)
        layoutParams.gravity = Gravity.CENTER
        setLayoutParams(layoutParams)
    }

    /**
     * Sets the size of the poster view.
     *
     * @param width The width of the poster view.
     * @param height The height of the poster view.
     */
    fun setPosterSize(width: Int, height: Int) {
        val layoutParams = binding.posterImageView.layoutParams
        layoutParams.width = width
        layoutParams.height = height
        binding.posterImageView.layoutParams = layoutParams

        // Update the title text size based on the new dimensions
        val scaledTextSize = calculateScaledTextSize(width)
        setTitleSize(scaledTextSize)
    }

    private fun calculateScaledTextSize(width: Int): Float {
        val maxWidth = resources.getDimensionPixelSize(R.dimen.default_poster_width)
        val defaultTextSize = resources.getDimension(R.dimen.default_poster_title_size)
        val scaleFactor = width.toFloat() / maxWidth
        return defaultTextSize * scaleFactor
    }


    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)

        // If the width or height is set to match_parent, update the size of the poster view
        if (layoutParams.width == LayoutParams.MATCH_PARENT || layoutParams.height == LayoutParams.MATCH_PARENT) {
            val measuredWidth = measuredWidth
            val measuredHeight = measuredHeight

            // Adjust the poster size
            val posterWidth = measuredWidth - paddingLeft - paddingRight
            val posterHeight = measuredHeight - paddingTop - paddingBottom
            setPosterSize(posterWidth, posterHeight)

            // Adjust the title text size based on the new dimensions
            val scaledTextSize = calculateScaledTextSize(posterWidth)
            setTitleSize(scaledTextSize)

            // Re-measure the view to accommodate the changes
            super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        }
    }

}
