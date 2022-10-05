package com.example.cinemates.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.cinemates.NavGraphDirections
import com.example.cinemates.databinding.ListItemMovieLongBinding
import com.example.cinemates.databinding.ListItemMovieSmallBinding
import com.example.cinemates.databinding.ListItemPersonLongBinding
import com.example.cinemates.databinding.ListItemPersonSmallBinding
import com.example.cinemates.model.data.Cast
import com.example.cinemates.model.data.Movie
import com.example.cinemates.model.data.Person
import com.example.cinemates.util.MyDiffUtilCallbacks
import com.example.cinemates.util.ViewSize

private const val PERSON = 0
private const val MOVIE = 1

/**
 * @author Antonio Di Nuzzo
 * Created 15/12/2021 at 16:36
 */
class ItemsRecyclerViewAdapter<T>(private val viewType: ViewSize) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var dataList: MutableList<T> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val viewHolder: RecyclerView.ViewHolder
        when (viewType) {
            MOVIE -> viewHolder = if (this.viewType == ViewSize.SMALL) {
                val smallMovieBinding =
                    ListItemMovieSmallBinding.inflate(layoutInflater, parent, false)
                MovieViewHolder(smallMovieBinding)
            } else {
                val longMovieBinding =
                    ListItemMovieLongBinding.inflate(layoutInflater, parent, false)
                MovieViewHolder(longMovieBinding)
            }
            PERSON -> viewHolder = if (this.viewType == ViewSize.SMALL) {
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
                when (viewType) {
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
                when (viewType) {
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
                    when (viewType) {
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
                    when (viewType) {
                        ViewSize.LONG -> {
                            (holder as PersonViewHolder).mLongBinding.actor =
                                dataList[position] as Cast?
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
                                dataList[position] as Person?
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
        val diffResult = DiffUtil.calculateDiff(MyDiffUtilCallbacks(this.dataList, dataList))
        diffResult.dispatchUpdatesTo(this)
        this.dataList.clear()
        this.dataList.addAll(dataList)
        notifyDataSetChanged()
    }
}