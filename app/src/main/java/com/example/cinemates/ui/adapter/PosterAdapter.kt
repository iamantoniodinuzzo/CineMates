package com.example.cinemates.ui.adapter


/**
 * @author Antonio Di Nuzzo (Indisparte)
 */
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.cinemates.R
import com.example.cinemates.common.SingleViewAdapter
import com.example.cinemates.databinding.ListItemPosterBinding
import com.example.cinemates.model.*

class PosterAdapter : SingleViewAdapter<Image, ListItemPosterBinding>(R.layout.list_item_poster) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SingleViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<ListItemPosterBinding>(inflater, itemLayoutResId, parent, false)
        return SingleViewHolder(binding)
    }

    override fun onBindItem(binding: ListItemPosterBinding, item: Image) {
        binding.path = item.filePath
        binding.root.setOnClickListener {view->
            /*val action = NavGraphDirections.actionGlobalActorDetailsFragment(item)
            Navigation.findNavController(view).navigate(action)*/
        }

    }
}
