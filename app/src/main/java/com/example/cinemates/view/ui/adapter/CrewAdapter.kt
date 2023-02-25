package com.example.cinemates.view.ui.adapter

import android.view.View
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import com.example.cinemates.databinding.ListItemCrewLongBinding
import com.example.cinemates.databinding.ListItemCrewSmallBinding
import com.example.cinemates.databinding.ListItemPersonLongBinding
import com.example.cinemates.databinding.ListItemPersonSmallBinding
import com.example.cinemates.model.Crew
import com.example.cinemates.model.Person
import com.example.cinemates.util.ViewSize
import com.example.cinemates.util.inflater

/**
 * @author Antonio Di Nuzzo (Indisparte)
 */
class CrewAdapter : MultipleViewSizeAdapter<Crew>() {

    private val diffCallback = object : DiffUtil.ItemCallback<Crew>() {

        override fun areItemsTheSame(oldItem: Crew, newItem: Crew): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Crew, newItem: Crew): Boolean {
            return oldItem == newItem
        }

    }
    private val dataList = AsyncListDiffer(this, diffCallback)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CrewViewHolder {
        return when (viewSize) {
            ViewSize.LONG -> CrewLongViewHolder(parent inflater ListItemCrewLongBinding::inflate)
            ViewSize.SMALL -> CrewSmallViewHolder(parent inflater ListItemCrewSmallBinding::inflate)
        }
    }

    override fun onBindViewHolder(holder: BaseViewHolder<Crew>, position: Int) {
        holder.bind(dataList.currentList[position])
    }

    override fun getItemCount(): Int {
        return dataList.currentList.size
    }

    override fun addItems(items: List<Crew>) {
        dataList.submitList(items)
    }

    abstract inner class CrewViewHolder(
        binding: ViewDataBinding
    ) : BaseViewHolder<Crew>(binding) {

        abstract override fun bind(items: Crew)
        protected fun onClick(view: View, crew: Crew) {
            // TODO: Navigate to crew detail
           /* val action = NavGraphDirections.actionGlobalActorDetailsFragment(person)
            Navigation.findNavController(view).navigate(action)*/
        }
    }

    inner class CrewLongViewHolder(
        private val binding: ListItemCrewLongBinding,
    ) : CrewAdapter.CrewViewHolder(binding) {
        override fun bind(items: Crew) {
            binding.apply {
                crew = items
                executePendingBindings()
                root.setOnClickListener { view ->
                    onClick(view, items)
                }
            }
        }
    }

    inner class CrewSmallViewHolder(
        private val binding: ListItemCrewSmallBinding,
    ) : CrewAdapter.CrewViewHolder(binding) {
        override fun bind(items: Crew) {
            binding.apply {
                crew = items
                executePendingBindings()
                root.setOnClickListener { view ->
                    onClick(view, items)
                }
            }
        }
    }


}