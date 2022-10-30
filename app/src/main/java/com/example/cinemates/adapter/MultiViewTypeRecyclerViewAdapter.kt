package com.example.cinemates.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.cinemates.databinding.ListItemMovieLongBinding
import com.example.cinemates.databinding.ListItemMovieSmallBinding
import com.example.cinemates.databinding.ListItemPersonLongBinding
import com.example.cinemates.databinding.ListItemPersonSmallBinding
import com.example.cinemates.model.data.Cast
import com.example.cinemates.model.data.Movie
import com.example.cinemates.model.data.Person
import com.example.cinemates.util.ViewSize
import com.example.cinemates.util.inflater


/**
 * @author Antonio Di Nuzzo
 * Created 15/12/2021 at 16:36
 */
class MultiViewTypeRecyclerViewAdapter<T>(
    private val viewType: ViewSize,
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var dataList: MutableList<T> = arrayListOf()

    private companion object {
        const val PERSON = 0
        const val MOVIE = 1
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            MOVIE ->
                if (this.viewType == ViewSize.SMALL) {
                    MovieSmallViewHolder(parent inflater ListItemMovieSmallBinding::inflate)
                } else {
                    MovieLongViewHolder(parent inflater ListItemMovieLongBinding::inflate)
                }
            PERSON ->
                if (this.viewType == ViewSize.SMALL) {
                    PersonSmallViewHolder(parent inflater ListItemPersonSmallBinding::inflate)
                } else {
                    PersonLongViewHolder(parent inflater ListItemPersonLongBinding::inflate)
                }
            else -> throw IllegalStateException("Unexpected value: $viewType")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder.itemViewType) {
            MOVIE -> {
                val movie = dataList[position] as Movie
                when (viewType) {
                    ViewSize.LONG -> {
                        (holder as MovieLongViewHolder).bind(movie)
                    }
                    ViewSize.SMALL -> {
                        (holder as MovieSmallViewHolder).bind(movie)
                    }
                }
            }
            PERSON -> {
                val person = dataList[position] as Person
                when (viewType) {
                    ViewSize.LONG -> {
                        (holder as PersonLongViewHolder).bind(person as Cast)
                    }
                    ViewSize.SMALL -> {
                        (holder as PersonSmallViewHolder).bind(person)
                    }
                }
            }
        }
    }

    override fun onBindViewHolder(
        holder: RecyclerView.ViewHolder,
        position: Int,
        payloads: List<Any>
    ) {
        if (payloads.isEmpty()) super.onBindViewHolder(holder, position, payloads) else {
            when (holder.itemViewType) {
                MOVIE -> {
                    val movie = dataList[position] as Movie
                    when (viewType) {
                        ViewSize.LONG -> {
                            (holder as MovieLongViewHolder).bind(movie)
                        }
                        ViewSize.SMALL -> {
                            (holder as MovieSmallViewHolder).bind(movie)
                        }
                    }
                }
                PERSON -> {
                    val person = dataList[position] as Person
                    when (viewType) {
                        ViewSize.LONG -> {
                            (holder as PersonLongViewHolder).bind(person as Cast)
                        }
                        ViewSize.SMALL -> {
                            (holder as PersonSmallViewHolder).bind(person)
                        }
                    }
                }
            }
        }
    }

    //Returns the view type of the item at position for the purposes of view recycling.
    override fun getItemViewType(position: Int): Int {
        return when (dataList[position]) {
            is Movie -> MOVIE
            is Person -> PERSON
            else -> -1
        }
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    fun addItems(dataList: MutableList<T>) {
        this.dataList.clear()
        this.dataList.addAll(dataList)
        notifyDataSetChanged()
    }


}



