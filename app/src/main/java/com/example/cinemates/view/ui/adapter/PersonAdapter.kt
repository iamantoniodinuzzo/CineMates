package com.example.cinemates.view.ui.adapter

import android.view.View
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.navigation.Navigation
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import com.example.cinemates.NavGraphDirections
import com.example.cinemates.databinding.ListItemPersonLongBinding
import com.example.cinemates.databinding.ListItemPersonSmallBinding
import com.example.cinemates.model.Cast
import com.example.cinemates.model.Person
import com.example.cinemates.util.ViewSize
import com.example.cinemates.util.inflater

/**
 * @author Antonio Di Nuzzo (Indisparte)
 */
class PersonAdapter : MultipleViewSizeAdapter<Person>() {

    private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Person>() {

        override fun areItemsTheSame(oldItem: Person, newItem: Person): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Person, newItem: Person): Boolean {
            return oldItem == newItem
        }

    }
    private val dataList = AsyncListDiffer(this, DIFF_CALLBACK)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonViewHolder {
        return when (viewSize) {
            ViewSize.LONG -> PersonLongViewHolder(parent inflater ListItemPersonLongBinding::inflate)
            ViewSize.SMALL -> PersonSmallViewHolder(parent inflater ListItemPersonSmallBinding::inflate)
        }
    }

    override fun onBindViewHolder(holder: BaseViewHolder<Person>, position: Int) {
        holder.bind(dataList.currentList[position])
    }

    override fun getItemCount(): Int {
        return dataList.currentList.size
    }

    override fun addItems(items: List<Person>) {
        dataList.submitList(items)
    }

    abstract inner class PersonViewHolder(
        binding: ViewDataBinding
    ) : BaseViewHolder<Person>(binding) {

        abstract override fun bind(items: Person)
        protected fun onClick(view: View, person: Person) {
            val action = NavGraphDirections.actionGlobalActorDetailsFragment(person)
            Navigation.findNavController(view).navigate(action)
        }
    }

    inner class PersonLongViewHolder(
        private val binding: ListItemPersonLongBinding,
    ) : PersonViewHolder(binding) {
        override fun bind(items: Person) {
            binding.apply {
                actor = items as Cast
                executePendingBindings()
                root.setOnClickListener { view ->
                    onClick(view, items)
                }
            }
        }
    }

    inner class PersonSmallViewHolder(
        private val binding: ListItemPersonSmallBinding,
    ) : PersonViewHolder(binding) {
        override fun bind(items: Person) {
            binding.apply {
                person = items
                executePendingBindings()
                root.setOnClickListener { view ->
                    onClick(view, items)
                }
            }
        }
    }


}