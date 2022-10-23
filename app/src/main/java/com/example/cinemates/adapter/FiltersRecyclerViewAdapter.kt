package com.example.cinemates.adapter

import android.app.DownloadManager
import android.content.Context
import android.net.Uri
import android.os.Environment
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.example.cinemates.databinding.ListItemBackdropBinding
import com.example.cinemates.databinding.ListItemFilterBinding
import com.example.cinemates.databinding.ListItemPosterBinding
import com.example.cinemates.databinding.ListItemSectionBinding
import com.example.cinemates.model.data.Filter
import com.example.cinemates.model.data.ImagesResponse
import com.example.cinemates.util.BASE_URL
import com.example.cinemates.util.DialogFactory
import com.example.cinemates.util.inflater
import com.example.cinemates.view.ui.discover.DiscoverFragmentDirections
import java.io.File

/**
 * @author Antonio Di Nuzzo
 * Created 23/06/2022 at 17:23
 */
class FiltersRecyclerViewAdapter :
    RecyclerView.Adapter<FiltersRecyclerViewAdapter.FilterViewHolder>() {

    private val dataList: MutableList<Filter> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilterViewHolder {
        return FilterViewHolder(parent inflater ListItemFilterBinding::inflate)

    }

    override fun onBindViewHolder(holder: FilterViewHolder, position: Int) {
        val filter = dataList[position]
        holder.binding.apply {
            setFilter(filter)
            root.setOnClickListener{
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