package com.indisparte.movie_details.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.indisparte.model.entity.Crew
import com.indisparte.movie_details.databinding.ListItemCrewBinding

class CrewGridAdapter : BaseAdapter() {

    private var crewList = emptyList<Crew>()

    fun updateCrewList(newCrewList: List<Crew>?) {
        if (!newCrewList.isNullOrEmpty()) {
            crewList = newCrewList
            notifyDataSetChanged()
        }
    }

    override fun getCount(): Int {
        return crewList.size
    }

    override fun getItem(position: Int): Any {
        return crewList[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val binding: ListItemCrewBinding
        val view: View

        if (convertView == null) {
            val layoutInflater = LayoutInflater.from(parent.context)
            binding = ListItemCrewBinding.inflate(layoutInflater, parent, false)
            view = binding.root
            view.tag = binding
        } else {
            binding = convertView.tag as ListItemCrewBinding
            view = convertView
        }

        val crew = getItem(position) as Crew
        binding.crew = crew
        binding.executePendingBindings()

        return view
    }
}