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
class ActorAdapter : MultipleViewSizeAdapter<Cast>() {

    private val diffCallback = object : DiffUtil.ItemCallback<Cast>() {

        override fun areItemsTheSame(oldItem: Cast, newItem: Cast): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Cast, newItem: Cast): Boolean {
            return oldItem == newItem
        }

    }
    private val dataList = AsyncListDiffer(this, diffCallback)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActorViewHolder {
        return when (viewSize) {
            ViewSize.LONG -> ActorLongViewHolder(parent inflater ListItemPersonLongBinding::inflate)
            ViewSize.SMALL -> ActorSmallViewHolder(parent inflater ListItemPersonSmallBinding::inflate)
        }
    }

    override fun onBindViewHolder(holder: BaseViewHolder<Cast>, position: Int) {
        holder.bind(dataList.currentList[position])
    }

    override fun getItemCount(): Int {
        return dataList.currentList.size
    }

    override fun addItems(items: List<Cast>) {
        dataList.submitList(items)
    }

    abstract inner class ActorViewHolder(
        binding: ViewDataBinding
    ) : BaseViewHolder<Cast>(binding) {

        abstract override fun bind(items: Cast)
        protected fun onClick(view: View, person: Cast) {
            val action = NavGraphDirections.actionGlobalActorDetailsFragment(person)
            Navigation.findNavController(view).navigate(action)
        }
    }

    inner class ActorLongViewHolder(
        private val binding: ListItemPersonLongBinding,
    ) : ActorViewHolder(binding) {
        override fun bind(items: Cast) {
            binding.apply {
                actor = items
                executePendingBindings()
                root.setOnClickListener { view ->
                    onClick(view, items)
                }
            }
        }
    }

    inner class ActorSmallViewHolder(
        private val binding: ListItemPersonSmallBinding,
    ) : ActorViewHolder(binding) {
        override fun bind(items: Cast) {
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