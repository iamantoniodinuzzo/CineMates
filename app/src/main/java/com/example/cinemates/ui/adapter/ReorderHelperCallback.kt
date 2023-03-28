package com.example.cinemates.ui.adapter

import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView


/**
 * @author Antonio Di Nuzzo (Indisparte)
 */
class ReorderHelperCallback(val adapter: ItemTouchHelperAdapter) : ItemTouchHelper.Callback()
{
    override fun getMovementFlags(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder
    ): Int {
        val dragFlags = ItemTouchHelper.UP or ItemTouchHelper.DOWN
        val swipeFlags = 0
        return makeMovementFlags(dragFlags, swipeFlags)
    }

    override fun onMove(
        recyclerView: RecyclerView,
        source: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean {
        // Notify the adapter of the move
        adapter.onItemMove(
            source.adapterPosition,
            target.adapterPosition
        )
        return true
    }


    override fun onSelectedChanged(viewHolder: RecyclerView.ViewHolder?, actionState: Int) {
        if (actionState !== ItemTouchHelper.ACTION_STATE_IDLE) {
            if (viewHolder is SectionViewHolder) {
                val myViewHolder: SectionViewHolder =
                    viewHolder
                adapter.onRowSelected(myViewHolder)
            }
        }
        super.onSelectedChanged(viewHolder, actionState)
    }

    override fun clearView(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder) {
        super.clearView(recyclerView, viewHolder)
        if (viewHolder is SectionViewHolder) {
            val myViewHolder: SectionViewHolder =
                viewHolder
            adapter.onRowClear(myViewHolder)
        }
    }

    //draw red canva when swipe to right
   /* override fun onChildDraw(
        c: Canvas,
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        dX: Float,
        dY: Float,
        actionState: Int,
        isCurrentlyActive: Boolean
    ) {
        super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)

        val itemView = viewHolder.itemView
        val background = ColorDrawable(Color.RED)
        val intrinsicWidth = background.intrinsicWidth
        val intrinsicHeight = background.intrinsicHeight

        // Draw the red background for the delete action.
        if (dX > 0) {
            background.setBounds(
                itemView.left,
                itemView.top,
                itemView.left + dX.toInt() + intrinsicWidth,
                itemView.bottom
            )
        } *//*else {
            background.setBounds(
                itemView.right + dX.toInt() - intrinsicWidth,
                itemView.top,
                itemView.right,
                itemView.bottom
            )
        }*//*
        background.draw(c)

        // Draw the delete icon on top of the red background.
        val deleteIcon =
            ContextCompat.getDrawable(CineMatesApp.getContext(), R.drawable.ic_baseline_delete_24)!!
        val deleteIconMargin = (itemView.height - deleteIcon.intrinsicHeight) / 2
        val deleteIconTop = itemView.top + (itemView.height - deleteIcon.intrinsicHeight) / 2
        val deleteIconBottom = deleteIconTop + deleteIcon.intrinsicHeight
        if (dX > 0) {
            val deleteIconLeft = itemView.left + deleteIconMargin
            val deleteIconRight = itemView.left + deleteIconMargin + deleteIcon.intrinsicWidth
            deleteIcon.setBounds(deleteIconLeft, deleteIconTop, deleteIconRight, deleteIconBottom)
        }*//* else {
            val deleteIconLeft = itemView.right - deleteIconMargin - deleteIcon.intrinsicWidth
            val deleteIconRight = itemView.right - deleteIconMargin
            deleteIcon.setBounds(deleteIconLeft, deleteIconTop, deleteIconRight, deleteIconBottom)
        }*//*
        deleteIcon.draw(c)
    }*/

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        // Remove the item from the adapter's dataset.
      /*  val position = viewHolder.adapterPosition
        if (direction == ItemTouchHelper.RIGHT) {
            adapter.onItemDismiss(position)
        }*/
    }

}

interface OnStartDragListener {

    /**
     * Called when a view is requesting a start of a drag.
     *
     * @param viewHolder The holder of the view to drag.
     */
    fun onStartDrag(viewHolder: RecyclerView.ViewHolder?)

}

interface ItemTouchHelperAdapter {
    /**
     * Called when an item has been dragged far enough to trigger a move. This is called every time
     * an item is shifted, and **not** at the end of a "drop" event.<br></br>
     * <br></br>
     * Implementations should call {link RecyclerView.Adapter#notifyItemMoved(int, int)} after
     * adjusting the underlying data to reflect this move.
     *
     * @param fromPosition The start position of the moved item.
     * @param toPosition   Then resolved position of the moved item.
     * @return True if the item was moved to the new adapter position.
     *
     * see RecyclerView#getAdapterPositionFor(RecyclerView.ViewHolder)
     * see RecyclerView.ViewHolder#getAdapterPosition()
     */
    fun onItemMove(fromPosition: Int, toPosition: Int): Boolean


    /**
     * Called when an item has been dismissed by a swipe.<br></br>
     * <br></br>
     * Implementations should call {link RecyclerView.Adapter#notifyItemRemoved(int)}after
     * adjusting the underlying data to reflect this removal.
     *
     * @param position The position of the item dismissed.
     * see RecyclerView#getAdapterPositionFor(RecyclerView.ViewHolder)
     * see RecyclerView.ViewHolder#getAdapterPosition()
     */
    fun onItemDismiss(position: Int)

    fun onRowSelected(holder: SectionViewHolder)
    fun onRowClear(holder: SectionViewHolder)
}