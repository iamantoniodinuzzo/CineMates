package com.example.cinemates.view.ui.adapter


/**
 * @author Antonio Di Nuzzo (Indisparte)
 */
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import com.example.cinemates.NavGraphDirections
import com.example.cinemates.R
import com.example.cinemates.databinding.ListItemMovieSmallBinding
import com.example.cinemates.databinding.ListItemTvSmallBinding
import com.example.cinemates.model.Movie
import com.example.cinemates.model.TvShow

class TvShowAdapter : BaseAdapter<TvShow, ListItemTvSmallBinding>(R.layout.list_item_tv_small, emptyList()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<ListItemTvSmallBinding>(inflater, itemLayoutResId, parent, false)
        return BaseViewHolder(binding)
    }

    override fun onBindItem(binding: ListItemTvSmallBinding, item: TvShow) {
        binding.tv = item
        binding.root.setOnClickListener {view->
            val action = NavGraphDirections.actionGlobalTvDetailsFragment(item)
            Navigation.findNavController(view).navigate(action)
        }

    }
}
