package com.example.cinemates.ui.adapter

import android.graphics.Color
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.cinemates.databinding.ListItemSectionBinding
import com.example.cinemates.databinding.ListItemSectionCollassableBinding
import com.example.cinemates.model.section.*
import com.example.cinemates.util.VibrationUtils
import com.example.cinemates.util.inflater
import java.util.*


/**
 * @author Antonio Di Nuzzo (Indisparte)
 */
class SectionAdapter(
    private var sections: MutableList<Section<*>>,
    private val dragListener: OnStartDragListener?
) : RecyclerView.Adapter<SectionViewHolder>(), ItemTouchHelperAdapter {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SectionViewHolder {
        return when (viewType) {
            0 -> SectionMovieViewHolder(
                parent inflater ListItemSectionBinding::inflate,
                dragListener
            )
            1 -> SectionTvViewHolder(parent inflater ListItemSectionBinding::inflate,
                dragListener)
            2 -> SectionActorViewHolder(
                parent inflater ListItemSectionBinding::inflate,
                dragListener
            )
            3 -> SectionEpisodeViewHolder(
                parent inflater ListItemSectionCollassableBinding::inflate,
                dragListener
            )
            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    /**
     * Updates the list of data items and notifies the adapter of the changes.
     *
     * @param newItems The new list of data items to display in the RecyclerView.
     */
    fun updateItems(newItems: MutableList<Section<*>>) {
        val diffResult = DiffUtil.calculateDiff(BaseAdapter.DiffCallback(sections, newItems))
        sections = newItems
        diffResult.dispatchUpdatesTo(this)
    }

    override fun onBindViewHolder(holder: SectionViewHolder, position: Int) {
        val section = sections[position]
        holder.bind(section)
    }

    override fun getItemCount(): Int {
        return sections.size
    }

    override fun getItemViewType(position: Int): Int {
        return when (sections[position]) {
            is SectionMovie -> 0
            is SectionTvShow -> 1
            is SectionPersons -> 2
            is SectionEpisodesGroup -> 3
            else -> throw IllegalArgumentException("Invalid data type")
        }
    }

    override fun onItemMove(fromPosition: Int, toPosition: Int): Boolean {
        Collections.swap(sections, fromPosition, toPosition)
        notifyItemMoved(fromPosition, toPosition)
        return true
    }

    override fun onRowSelected(holder: SectionViewHolder) {
        holder.binding.root.setBackgroundColor(Color.GRAY)
        VibrationUtils.vibrate(100)
    }

    override fun onRowClear(holder: SectionViewHolder) {
        holder.binding.root.setBackgroundColor(Color.TRANSPARENT)
    }

    override fun onItemDismiss(position: Int) {
        sections.removeAt(position)
        notifyItemRemoved(position)
    }


}

