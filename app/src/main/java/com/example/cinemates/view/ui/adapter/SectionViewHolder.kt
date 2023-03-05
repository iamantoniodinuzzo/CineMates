package com.example.cinemates.view.ui.adapter

import android.view.MotionEvent
import android.view.View
import android.view.View.OnTouchListener
import android.widget.TextView
import androidx.core.view.MotionEventCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cinemates.databinding.ListItemSectionBinding
import com.example.cinemates.model.SectionMovie
import com.example.cinemates.model.SectionPersons
import com.example.cinemates.model.SectionTvShow
import com.example.cinemates.util.DoubleTouchListener


/**
 * @author Antonio Di Nuzzo (Indisparte)
 */
sealed class SectionViewHolder(
    val binding: ListItemSectionBinding,
    val dragStartDragListener: OnStartDragListener? = null
) :
    RecyclerView.ViewHolder(binding.root) {
    protected val titleView: TextView = binding.textSectionTitle
    protected val recyclerView: RecyclerView = binding.recyclerView
    abstract fun bind(section: Any)

}

class SectionMovieViewHolder(binding: ListItemSectionBinding, dragListener: OnStartDragListener) :
    SectionViewHolder(binding, dragListener) {

    override fun bind(section: Any) {
        section as SectionMovie
        binding.section = section

        recyclerView.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            val movieAdapter = MovieAdapter()
            movieAdapter.updateItems(section.items)
            adapter = movieAdapter
        }

        binding.root.setOnTouchListener(object : DoubleTouchListener() {
            override fun onDoubleClick(v: View, event: MotionEvent) {
                if (event.action == MotionEvent.ACTION_DOWN) {
                    dragStartDragListener?.onStartDrag(this@SectionMovieViewHolder)
                }
            }

        })

    }
}

class SectionTvViewHolder(binding: ListItemSectionBinding, dragListener: OnStartDragListener) :
    SectionViewHolder(binding, dragListener) {
    override fun bind(section: Any) {
        section as SectionTvShow
        binding.section = section

        recyclerView.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            val tvShowAdapter = TvShowAdapter()
            tvShowAdapter.updateItems(section.items)
            adapter = tvShowAdapter
        }


        binding.root.setOnTouchListener(object : DoubleTouchListener() {
            override fun onDoubleClick(v: View, event: MotionEvent) {
                if (event.action == MotionEvent.ACTION_DOWN) {
                    dragStartDragListener?.onStartDrag(this@SectionTvViewHolder)
                }
            }

        })

    }
}

class SectionActorViewHolder(binding: ListItemSectionBinding, dragListener: OnStartDragListener) :
    SectionViewHolder(binding, dragListener) {
    override fun bind(section: Any) {
        section as SectionPersons
        binding.section = section

        recyclerView.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            val personAdapter = PersonAdapter()
            personAdapter.updateItems(section.items)
            adapter = personAdapter
        }


        binding.root.setOnTouchListener(object : DoubleTouchListener() {
            override fun onDoubleClick(v: View, event: MotionEvent) {
                if (event.action == MotionEvent.ACTION_DOWN) {
                    dragStartDragListener?.onStartDrag(this@SectionActorViewHolder)
                }
            }

        })
    }
}
