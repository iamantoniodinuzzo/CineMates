package com.example.cinemates.adapter

import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.cinemates.databinding.ListItemFilterBinding
import com.example.cinemates.model.data.Filter
import com.example.cinemates.util.inflater
import com.example.cinemates.view.ui.discover.DiscoverFragmentDirections

/**
 * @author Antonio Di Nuzzo
 * Created 23/06/2022 at 17:23
 */
class FiltersRecyclerViewAdapter() :
    RecyclerView.Adapter<FiltersRecyclerViewAdapter.FilterViewHolder>() {

    private val dataList: MutableList<Filter> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilterViewHolder {
        return FilterViewHolder(parent inflater ListItemFilterBinding::inflate)
    }

    override fun onBindViewHolder(holder: FilterViewHolder, position: Int) {
        val filter = dataList[position]
        holder.binding.apply {
            setFilter(filter)
            root.setOnClickListener {
                val action =
                    DiscoverFragmentDirections.actionDiscoverFragmentToFilterFragment(filter)
                Navigation.findNavController(it).navigate(action)
            }


        }
    }

        override fun getItemCount(): Int {
            return dataList.size
        }

        fun addItems(dataList: List<Filter>?) {
            this.dataList.clear()
            this.dataList.addAll(dataList!!)
            notifyDataSetChanged()
        }

        fun addItem(filter: Filter) {
            this.dataList.add(filter)
            notifyDataSetChanged()
        }


        inner class FilterViewHolder(val binding: ListItemFilterBinding) :
            RecyclerView.ViewHolder(
                binding.root
            ) {

        }

    }