package com.example.cinemates.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.AsyncDifferConfig
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.cinemates.adapter.SectionRecyclerViewAdapter.SectionViewHolder
import com.example.cinemates.databinding.ListItemSectionBinding
import com.example.cinemates.model.data.Cast
import com.example.cinemates.model.data.Movie
import com.example.cinemates.model.data.Person
import com.example.cinemates.model.data.Section

/**
 * @author Antonio Di Nuzzo
 * Created 15/12/2021 at 16:36
 */
class SectionRecyclerViewAdapter(private val lifecycleOwner: LifecycleOwner) :
    ListAdapter<Section<*>, SectionViewHolder>(asyncDiffConfig) {
    private val dataList: MutableList<Section<*>> = mutableListOf()

    private companion object {
        private const val PERSON = 0
        private const val MOVIE = 1
        private val diffCallback = object : DiffUtil.ItemCallback<Section<*>>() {
            override fun areItemsTheSame(oldItem: Section<*>, newItem: Section<*>): Boolean =
                oldItem.liveData == newItem.liveData

            override fun areContentsTheSame(oldItem: Section<*>, newItem: Section<*>): Boolean =
                areItemsTheSame(oldItem, newItem)
        }

        private val asyncDiffConfig = AsyncDifferConfig.Builder(diffCallback).build()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SectionViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val sectionRowBinding = ListItemSectionBinding.inflate(layoutInflater, parent, false)
        return SectionViewHolder(sectionRowBinding)
    }

    override fun onBindViewHolder(holder: SectionViewHolder, position: Int) {
        when (holder.itemViewType) {
            MOVIE -> {
                val movieSection = dataList[position] as Section<Movie>
                val sectionItemsMovie = ItemsRecyclerViewAdapter<Movie>(movieSection.viewSize)
                holder.binding.apply {
                    section = movieSection
                    executePendingBindings()
                    recyclerView.adapter = sectionItemsMovie
                    recyclerView.setEmptyView(holder.binding.emptyView.root)
                }
                movieSection.liveData.observe(lifecycleOwner) { items ->
                    sectionItemsMovie.addItems(
                        items
                    )
                }

            }
            PERSON -> {
                val personSection = dataList[position] as Section<Person>
                val sectionItemsPerson = ItemsRecyclerViewAdapter<Person>(personSection.viewSize)
                holder.binding.apply {
                    section = personSection
                    executePendingBindings()
                    recyclerView.adapter = sectionItemsPerson
                    recyclerView.setEmptyView(holder.binding.emptyView.root)
                }
                personSection.liveData.observe(lifecycleOwner) { items ->
                    sectionItemsPerson.addItems(
                        items
                    )
                }
            }
        }
    }


    override fun getItemCount(): Int {
        return dataList.size
    }

    fun addItems(dataList: List<Section<*>>) {
        this.dataList.clear()
        this.dataList.addAll(dataList)
        notifyDataSetChanged()
    }

    fun addItems(section: Section<Any?>) {
        dataList.add(section)
        notifyDataSetChanged()
    }

    //Returns the view type of the item at position for the purposes of view recycling.
    override fun getItemViewType(position: Int): Int {
        val value = dataList[position].genericType

        if (value == Movie::class.java) {
            return MOVIE
        } else if (value == Person::class.java || value == Cast::class.java) {
            return PERSON
        }
        return -1
    }

    class SectionViewHolder internal constructor(var binding: ListItemSectionBinding) :
        RecyclerView.ViewHolder(
            binding.root
        ) {

    }
}
