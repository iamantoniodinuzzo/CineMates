package com.example.cinemates.ui.adapter

import android.view.MotionEvent
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.example.cinemates.databinding.ListItemSectionBinding
import com.example.cinemates.databinding.ListItemSectionCollassableBinding
import com.example.cinemates.model.section.SectionEpisodesGroup
import com.example.cinemates.model.section.SectionMovie
import com.example.cinemates.model.section.SectionPersons
import com.example.cinemates.model.section.SectionTvShow
import com.example.cinemates.util.DoubleTouchListener


/**
 * @author Antonio Di Nuzzo (Indisparte)
 */
sealed class SectionViewHolder(
    open val binding: ViewBinding,
    val dragStartDragListener: OnStartDragListener? = null
) : RecyclerView.ViewHolder(binding.root) {

    abstract fun bind(section: Any)

}

class SectionMovieViewHolder(
    override val binding: ListItemSectionBinding,
    dragListener: OnStartDragListener?
) :
    SectionViewHolder(binding, dragListener) {
    val recyclerView: RecyclerView = binding.recyclerView

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

class SectionTvViewHolder(
    override val binding: ListItemSectionBinding,
    dragListener: OnStartDragListener?
) :
    SectionViewHolder(binding, dragListener) {
    override fun bind(section: Any) {
        section as SectionTvShow
        val recyclerView: RecyclerView = binding.recyclerView
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

class SectionActorViewHolder(
    override val binding: ListItemSectionBinding,
    dragListener: OnStartDragListener?
) :
    SectionViewHolder(binding, dragListener) {
    override fun bind(section: Any) {
        section as SectionPersons
        binding.section = section

        binding.recyclerView.apply {
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

class SectionEpisodeViewHolder(
    override val binding: ListItemSectionCollassableBinding,
    dragListener: OnStartDragListener?
) :
    SectionViewHolder(binding, dragListener) {
    override fun bind(item: Any) {
        item as SectionEpisodesGroup
        binding.apply {
            section = item
            recyclerView.apply {
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
                val episodeAdapter = EpisodeAdapter()
                episodeAdapter.updateItems(item.items)
                adapter = episodeAdapter
            }
            collapseButton.setOnClickListener {
                if (recyclerView.visibility == View.VISIBLE) {
                    recyclerView.visibility = View.GONE
                } else {
                    recyclerView.visibility = View.VISIBLE
                }
            }

        }


        binding.root.setOnTouchListener(object : DoubleTouchListener() {
            override fun onDoubleClick(v: View, event: MotionEvent) {
                if (event.action == MotionEvent.ACTION_DOWN) {
                    dragStartDragListener?.onStartDrag(this@SectionEpisodeViewHolder)
                }
            }

        })
    }
}
