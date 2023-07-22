package com.indisparte.ui.custom_view


import android.content.Context
import android.os.Build
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.StyleRes
import androidx.recyclerview.widget.RecyclerView
import com.indisparte.ui.R
import com.indisparte.ui.databinding.ViewEmptyStateBinding
import com.indisparte.util.extension.gone
import com.indisparte.util.extension.visible

/**
 * A custom RecyclerView with support for displaying an empty state view when the adapter is empty.
 *
 * @param context The context in which the RecyclerView is created.
 * @param attrs The attribute set for the RecyclerView. (Default: null)
 * @param defStyle The default style resource ID. (Default: 0)
 * @constructor Creates an EmptyStateRecyclerView with the provided context, attributes, and style.
 */
class EmptyStateRecyclerView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0,
) : RecyclerView(context, attrs, defStyle) {

    // Private variables for managing the empty state view
    private var emptyStateView: ViewEmptyStateBinding? = null
    private var emptyStateButtonClickListener: (() -> Unit)? = null
    private val adapterDataObserver = object : AdapterDataObserver() {
        override fun onChanged() {
            super.onChanged()
            checkIfEmpty()
        }

        override fun onItemRangeInserted(positionStart: Int, itemCount: Int) {
            super.onItemRangeInserted(positionStart, itemCount)
            checkIfEmpty()
        }

        override fun onItemRangeRemoved(positionStart: Int, itemCount: Int) {
            super.onItemRangeRemoved(positionStart, itemCount)
            checkIfEmpty()
        }
    }

    /**
     * Initializes the EmptyStateRecyclerView by setting up the empty state view and
     * obtaining custom attributes from the provided attribute set.
     */
    private fun initializeView() {
        // Inflate the empty state view layout and initialize the binding
        val inflater = LayoutInflater.from(context)
        emptyStateView = ViewEmptyStateBinding.inflate(inflater)
        emptyStateView?.root?.gone()
    }

    /**
     * Applies default values to the empty state views if no custom attributes are provided.
     */
    private fun applyDefaultValues() {
        // Set default values for the empty state views
        val defaultTitleStyle = R.style.SmallTitleTextViewStyle
        val defaultSubTitleStyle = R.style.BodyTextViewStyle

        // Apply default values to the empty state views
        setTitleStyle(defaultTitleStyle)
        setSubTitleStyle(defaultSubTitleStyle)
    }

    init {
        initializeView()
        applyDefaultValues()
        applyCustomAttributes(attrs, defStyle)

    }

    /**
     * Applies custom attributes to the empty state views based on the provided attribute set.
     *
     * @param attrs The attribute set containing custom attributes for the view.
     * @param defStyleAttr The default style attribute for the view.
     */
    private fun applyCustomAttributes(attrs: AttributeSet?, defStyleAttr: Int) {
        // Retrieve custom attributes from the attribute set
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.EmptyStateRecyclerView)
        val emptyStateSubtitle =
            typedArray.getString(R.styleable.EmptyStateRecyclerView_emptyStateSubtitle)
        val emptyStateTitle =
            typedArray.getString(R.styleable.EmptyStateRecyclerView_emptyStateTitle)
        val emptyStateButtonTitle =
            typedArray.getString(R.styleable.EmptyStateRecyclerView_emptyStateButtonTitle)
        val emptyStateImageResId =
            typedArray.getResourceId(R.styleable.EmptyStateRecyclerView_emptyStateImage, 0)
        val emptyStateTitleStyle =
            typedArray.getResourceId(
                R.styleable.EmptyStateRecyclerView_emptyStateTitleStyle,
                R.style.SmallTitleTextViewStyle
            )
        val emptyStateSubTitleStyle =
            typedArray.getResourceId(
                R.styleable.EmptyStateRecyclerView_emptyStateSubtitleStyle,
                R.style.BodyTextViewStyle
            )

        // Apply custom attributes to the empty state views

        setEmptyStateSubtitle(emptyStateSubtitle)
        setEmptyStateTitle(emptyStateTitle)
        setTitleStyle(emptyStateTitleStyle)
        setSubTitleStyle(emptyStateSubTitleStyle)
        setEmptyStateButtonTitle(emptyStateButtonTitle)
        setEmptyStateImage(emptyStateImageResId)

        typedArray.recycle()

    }

    fun setTitleStyle(@StyleRes styleRes: Int) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            emptyStateView?.tvEmptyStateTitle?.setTextAppearance(styleRes)
        } else {
            @Suppress("DEPRECATION")
            emptyStateView?.tvEmptyStateTitle?.setTextAppearance(context, styleRes)
        }
    }

    fun setSubTitleStyle(@StyleRes styleRes: Int) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            emptyStateView?.tvEmptyStateSubtitle?.setTextAppearance(styleRes)
        } else {
            @Suppress("DEPRECATION")
            emptyStateView?.tvEmptyStateSubtitle?.setTextAppearance(context, styleRes)
        }
    }

    /**
     * Sets the subtitle for the empty state view.
     *
     * @param subtitle The subtitle to be displayed in the empty state view.
     */
    fun setEmptyStateSubtitle(subtitle: String?) {
        emptyStateView?.tvEmptyStateSubtitle?.apply {
            text = subtitle
            if (!subtitle.isNullOrEmpty()) visible() else gone()
        }
    }


    /**
     * Sets the custom image for the empty state view.
     *
     * @param imageResId The resource ID of the custom image to be displayed in the empty state view.
     */
    fun setEmptyStateImage(imageResId: Int) {
        emptyStateView?.ivEmptyStateImage?.apply {
            setImageResource(imageResId)
            if (imageResId != 0) visible() else gone()
        }
    }

    /**
     * Sets the title for the empty state view.
     *
     * @param title The title to be displayed in the empty state view.
     */
    fun setEmptyStateTitle(title: String?) {
        emptyStateView?.tvEmptyStateTitle?.apply {
            text = title
            if (!title.isNullOrEmpty()) visible() else gone()
        }
    }

    /**
     * Sets the button title for the empty state view.
     *
     * @param buttonTitle The title for the button in the empty state view.
     */
    fun setEmptyStateButtonTitle(buttonTitle: String?) {
        emptyStateView?.btnEmptyState?.apply {
            text = buttonTitle
            if (!buttonTitle.isNullOrEmpty()) visible() else gone()
        }
    }

    /**
     * Sets the click listener for the button in the empty state view.
     *
     * @param listener The click listener to be invoked when the button in the empty state view is clicked.
     */
    fun setEmptyStateButtonClickListener(listener: () -> Unit) {
        emptyStateButtonClickListener = listener
        emptyStateView?.btnEmptyState?.setOnClickListener { listener.invoke() }
    }

    /**
     * Checks if the adapter is empty and displays the empty state view if necessary.
     */
    private fun checkIfEmpty() {
        // Check if the adapter is empty and display the empty state view if necessary
        val adapter = adapter
        val empty = adapter?.itemCount == 0

        if (empty) {
            emptyStateView?.root?.let { emptyView ->
                if (emptyView.parent == null) {
                    val layoutParams = LayoutParams(
                        LayoutParams.MATCH_PARENT,
                        LayoutParams.MATCH_PARENT
                    )
                    (parent as? ViewGroup)?.addView(emptyView, layoutParams)
                }
                emptyView.visible()
            }
        } else {
            emptyStateView?.root?.gone()
        }
    }

    /**
     * Sets the adapter for the EmptyStateRecyclerView and registers an observer to
     * monitor changes in the adapter data.
     *
     * @param adapter The adapter to be set for the RecyclerView.
     */
    override fun setAdapter(adapter: Adapter<*>?) {
        // Unregister the previous adapter data observer
        val oldAdapter = getAdapter()
        oldAdapter?.unregisterAdapterDataObserver(adapterDataObserver)

        // Set the new adapter and register a data observer to monitor changes
        super.setAdapter(adapter)
        adapter?.registerAdapterDataObserver(adapterDataObserver)
        adapterDataObserver.onChanged()
    }
}


