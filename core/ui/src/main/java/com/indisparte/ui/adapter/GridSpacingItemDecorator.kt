package com.indisparte.ui.adapter

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView


/**
 * Custom item decorator for adding spacing between items in a GridLayout RecyclerView with spanCount of 2.
 *
 * @property spacing The desired spacing between items in pixels.
 * @author Antonio Di Nuzzo (Indisparte)
 */
class GridSpacingItemDecorator(private val spacing: Int) : RecyclerView.ItemDecoration() {
    /**
     * Retrieves any offsets for the given item. Each field of [outRect] specifies the number of pixels that the
     * item view should be inset by, similar to padding or margin. The default implementation sets the bounds of
     * [outRect] to 0 and returns.
     *
     * @param outRect The [Rect] to receive the output.
     * @param view The child view to decorate.
     * @param parent The [RecyclerView] this ItemDecoration is decorating.
     * @param state The current state of the [RecyclerView].
     */
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State,
    ) {
        super.getItemOffsets(outRect, view, parent, state)
        val position = parent.getChildAdapterPosition(view)
        val spanCount = (parent.layoutManager as? GridLayoutManager)?.spanCount ?: 1

        val column = position % spanCount

        outRect.left = spacing - column * spacing / spanCount
        outRect.right = (column + 1) * spacing / spanCount

        if (position >= spanCount) {
            outRect.top = spacing
        }
    }
}