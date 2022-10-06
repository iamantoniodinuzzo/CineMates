package com.example.cinemates.adapter

import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.cinemates.NavGraphDirections
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
class ItemsRecyclerViewAdapter<T>(private val viewType: ViewSize) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var dataList: MutableList<T> = arrayListOf()

    private companion object {
        const val PERSON = 0
        const val MOVIE = 1

        class MovieSmallViewHolder(val binding: ListItemMovieSmallBinding) :
            RecyclerView.ViewHolder(binding.root) {
        }

        class MovieLongViewHolder(val binding: ListItemMovieLongBinding) :
            RecyclerView.ViewHolder(binding.root) {
        }

        class PersonSmallViewHolder(val binding: ListItemPersonSmallBinding) :
            RecyclerView.ViewHolder(binding.root) {
        }

        class PersonLongViewHolder(val binding: ListItemPersonLongBinding) :
            RecyclerView.ViewHolder(binding.root) {
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val viewHolder: RecyclerView.ViewHolder = when (viewType) {
            MOVIE -> if (this.viewType == ViewSize.SMALL) {
                MovieSmallViewHolder(parent inflater ListItemMovieSmallBinding::inflate)
            } else {
                MovieLongViewHolder(parent inflater ListItemMovieLongBinding::inflate)
            }
            PERSON -> if (this.viewType == ViewSize.SMALL) {
                PersonSmallViewHolder(parent inflater ListItemPersonSmallBinding::inflate)
            } else {
                PersonLongViewHolder(parent inflater ListItemPersonLongBinding::inflate)
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
                        (holder as MovieLongViewHolder).binding.apply {
                            this.movie = movie
                            executePendingBindings()
                            root.setOnClickListener { view -> navigateToMovieDetails(view, movie) }
                        }
                    }
                    ViewSize.SMALL -> {
                        (holder as MovieSmallViewHolder).binding.apply {
                            this.movie = movie
                            executePendingBindings()
                            root.setOnClickListener { view ->
                                navigateToMovieDetails(
                                    view,
                                    movie
                                )
                            }
                        }
                    }
                }
            }
            PERSON -> {
                val person = dataList[position] as Person
                when (viewType) {
                    ViewSize.LONG -> {
                        (holder as PersonLongViewHolder).binding.apply {
                            actor = person as Cast
                            executePendingBindings()
                            root.setOnClickListener { view ->
                                navigateToActorDetails(view, person)
                            }
                        }
                    }
                    ViewSize.SMALL -> {
                        (holder as PersonSmallViewHolder).binding.apply {
                            this.person = person
                            executePendingBindings()
                            root.setOnClickListener { view ->
                                navigateToActorDetails(view, person)
                            }
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
                            (holder as MovieLongViewHolder).binding.apply {
                                this.movie = movie
                                executePendingBindings()
                                root.setOnClickListener { view ->
                                    navigateToMovieDetails(
                                        view,
                                        movie
                                    )
                                }
                            }
                        }
                        ViewSize.SMALL -> {
                            (holder as MovieSmallViewHolder).binding.apply {
                                this.movie = movie
                                executePendingBindings()
                                root.setOnClickListener { view ->
                                    navigateToMovieDetails(
                                        view,
                                        movie
                                    )
                                }
                            }
                        }
                    }
                }
                PERSON -> {
                    val person = dataList[position] as Person
                    when (viewType) {
                        ViewSize.LONG -> {
                            (holder as PersonLongViewHolder).binding.apply {
                                actor = person as Cast
                                executePendingBindings()
                                root.setOnClickListener { view ->
                                    navigateToActorDetails(view, person)
                                }
                            }
                        }
                        ViewSize.SMALL -> {
                            (holder as PersonSmallViewHolder).binding.apply {
                                this.person = person
                                executePendingBindings()
                                root.setOnClickListener { view ->
                                    navigateToActorDetails(view, person)
                                }
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
        val value = dataList[position]
        if (value is Movie) {
            return MOVIE
        } else if (value is Person) {
            return PERSON
        }
        return -1
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



