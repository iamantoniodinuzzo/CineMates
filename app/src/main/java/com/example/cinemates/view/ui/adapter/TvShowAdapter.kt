package com.example.cinemates.view.ui.adapter

import android.view.View
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import com.example.cinemates.databinding.ListItemTvLongBinding
import com.example.cinemates.databinding.ListItemTvSmallBinding
import com.example.cinemates.model.TvShow
import com.example.cinemates.util.ViewSize
import com.example.cinemates.util.inflater

/**
 * @author Antonio Di Nuzzo (Indisparte)
 */
class TvShowAdapter : MultipleViewSizeAdapter<TvShow>() {
    private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<TvShow>() {

        override fun areItemsTheSame(oldItem: TvShow, newItem: TvShow): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: TvShow, newItem: TvShow): Boolean {
            return oldItem == newItem
        }

    }
    private val dataList = AsyncListDiffer(this, DIFF_CALLBACK)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvShowViewHolder {
        return when (viewSize) {
            ViewSize.LONG -> TvShowLongViewHolder(parent inflater ListItemTvLongBinding::inflate)
            ViewSize.SMALL -> TvShowSmallViewHolder(parent inflater ListItemTvSmallBinding::inflate)
        }
    }

    override fun onBindViewHolder(holder: BaseViewHolder<TvShow>, position: Int) {
        holder.bind(dataList.currentList[position])
    }

    override fun getItemCount(): Int {
        return dataList.currentList.size
    }

    override fun addItems(items: List<TvShow>) {
        dataList.submitList(items)
    }

    abstract inner class TvShowViewHolder(
        binding: ViewDataBinding
    ) : BaseViewHolder<TvShow>(binding) {

        abstract override fun bind(items: TvShow)
        protected fun onClick(view: View, movie: TvShow) {
           /* val action = NavGraphDirections.actionGlobalMovieDetailsFragment(movie)
            Navigation.findNavController(view).navigate(action)*/
        }
    }

    inner class TvShowLongViewHolder(
        private val binding: ListItemTvLongBinding,
    ) : TvShowViewHolder(binding) {
        override fun bind(items: TvShow) {
            binding.apply {
                tv = items
                executePendingBindings()
                root.setOnClickListener {
                    onClick(it, items)
                }
            }
        }
    }

    inner class TvShowSmallViewHolder(
        private val binding: ListItemTvSmallBinding,
    ) : TvShowViewHolder(binding) {
        override fun bind(items: TvShow) {
            binding.apply {
                tv = items
                executePendingBindings()
                root.setOnClickListener {
                    onClick(it, items)
                }
            }
        }
    }

}