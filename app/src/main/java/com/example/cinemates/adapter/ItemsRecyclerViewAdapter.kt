package com.example.cinemates.adapter

import androidx.navigation.Navigation.findNavController
import com.example.cinemates.util.ViewSize
import androidx.recyclerview.widget.RecyclerView
import android.view.ViewGroup
import android.view.LayoutInflater
import com.example.cinemates.model.data.Cast
import android.content.Intent
import android.view.View
import com.example.cinemates.view.ui.ActorDetailsFragment
import androidx.recyclerview.widget.DiffUtil
import com.example.cinemates.NavGraphDirections
import com.example.cinemates.databinding.ListItemMovieLongBinding
import com.example.cinemates.databinding.ListItemMovieSmallBinding
import com.example.cinemates.databinding.ListItemPersonLongBinding
import com.example.cinemates.databinding.ListItemPersonSmallBinding
import com.example.cinemates.model.data.Movie
import com.example.cinemates.model.data.Person
import com.example.cinemates.util.MyDiffUtilCallbacks
import java.lang.IllegalStateException

private const val PERSON = 0
private const val MOVIE = 1

/**
 * @author Antonio Di Nuzzo
 * Created 15/12/2021 at 16:36
 */
class ItemsRecyclerViewAdapter<T>(private val mViewSize: ViewSize) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var dataList: MutableList<T> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val viewHolder: RecyclerView.ViewHolder
        when (viewType) {
            MOVIE -> viewHolder = if (mViewSize == ViewSize.SMALL) {
                val smallMovieBinding =
                    ListItemMovieSmallBinding.inflate(layoutInflater, parent, false)
                MovieViewHolder(smallMovieBinding)
            } else {
                val longMovieBinding =
                    ListItemMovieLongBinding.inflate(layoutInflater, parent, false)
                MovieViewHolder(longMovieBinding)
            }
            PERSON -> viewHolder = if (mViewSize == ViewSize.SMALL) {
                val smallPersonBinding =
                    ListItemPersonSmallBinding.inflate(layoutInflater, parent, false)
                PersonViewHolder(smallPersonBinding)
            } else {
                val defaultBinding =
                    ListItemPersonLongBinding.inflate(layoutInflater, parent, false)
                PersonViewHolder(defaultBinding)
            }
            else -> throw IllegalStateException("Unexpected value: $viewType")
        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder.itemViewType) {
            MOVIE -> {
                val movie = dataList[position] as Movie
                when (mViewSize) {
                    ViewSize.LONG -> {
                        (holder as MovieViewHolder).mMovieLongBinding.movie = movie
                        holder.mMovieLongBinding.executePendingBindings()
                        holder.mMovieLongBinding.root.setOnClickListener(
                            View.OnClickListener { view -> navigateToMovieDetails(view, movie) })
                    }
                    ViewSize.SMALL -> {
                        (holder as MovieViewHolder).mMovieSmallBinding.movie = movie
                        holder.mMovieSmallBinding.executePendingBindings()
                        holder.mMovieSmallBinding.root.setOnClickListener { view ->
                            navigateToMovieDetails(
                                view,
                                movie
                            )
                        }
                    }
                }
            }
            PERSON -> {
                val person = dataList[position] as Person
                when (mViewSize) {
                    ViewSize.LONG -> {
                        (holder as PersonViewHolder).mLongBinding.actor =
                            dataList.get(position) as Cast?
                        holder.mLongBinding.executePendingBindings()
                        holder.mLongBinding.root.setOnClickListener { view ->
                            navigateToActorDetails(view, person)
                        }
                    }
                    ViewSize.SMALL -> {
                        (holder as PersonViewHolder).mSmallBinding.person =
                            dataList.get(position) as Person?
                        holder.mSmallBinding.executePendingBindings()
                        holder.mSmallBinding.root.setOnClickListener { view ->
                            navigateToActorDetails(view, person)
                        }
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
                    when (mViewSize) {
                        ViewSize.LONG -> {
                            (holder as MovieViewHolder).mMovieLongBinding.movie = movie
                            holder.mMovieLongBinding.executePendingBindings()
                            holder.mMovieLongBinding.root.setOnClickListener { view ->
                                navigateToMovieDetails(
                                    view,
                                    movie
                                )
                            }
                        }
                        ViewSize.SMALL -> {
                            (holder as MovieViewHolder).mMovieSmallBinding.movie = movie
                            holder.mMovieSmallBinding.executePendingBindings()
                            holder.mMovieSmallBinding.root.setOnClickListener { view ->
                                navigateToMovieDetails(
                                    view,
                                    movie
                                )
                            }
                        }
                    }
                }
                PERSON -> {
                    val person = dataList[position] as Person
                    when (mViewSize) {
                        ViewSize.LONG -> {
                            (holder as PersonViewHolder).mLongBinding.actor =
                                dataList.get(position) as Cast?
                            holder.mLongBinding.executePendingBindings()
                            holder.mLongBinding.root.setOnClickListener { view ->
                                navigateToActorDetails(
                                    view,
                                    person
                                )
                            }
                        }
                        ViewSize.SMALL -> {
                            (holder as PersonViewHolder).mSmallBinding.person =
                                dataList.get(position) as Person?
                            holder.mSmallBinding.executePendingBindings()
                            holder.mSmallBinding.root.setOnClickListener { view ->
                                navigateToActorDetails(
                                    view,
                                    person
                                )
                            }
                        }
                    }
                }
            }
        }
    }

    private fun navigateToActorDetails(view: View, person: Person) {
        val action = NavGraphDirections.actionGlobalActorDetailsFragment(person)
        findNavController(view).navigate(action)
    }

    private fun navigateToMovieDetails(view: View, movie: Movie) {
        val action = NavGraphDirections.actionGlobalMovieDetailsFragment(movie)
        findNavController(view).navigate(action)
    }

    //Returns the view type of the item at position for the purposes of view recycling.
    override fun getItemViewType(position: Int): Int {
        if (dataList[position] is Movie) {
            return MOVIE
        } else if ((dataList[position] is Person)) {
            return PERSON
        }
        return -1
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    fun addItems(dataList: MutableList<T>) {
        val diffResult = DiffUtil.calculateDiff(MyDiffUtilCallbacks<T>(this.dataList, dataList))
        diffResult.dispatchUpdatesTo(this)
        this.dataList.clear()
        this.dataList.addAll(dataList)
        notifyDataSetChanged()
    }
}