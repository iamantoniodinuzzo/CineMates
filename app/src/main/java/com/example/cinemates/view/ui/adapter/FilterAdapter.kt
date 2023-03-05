package com.example.cinemates.view.ui.adapter


/**
 * @author Antonio Di Nuzzo (Indisparte)
 */
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.example.cinemates.NavGraphDirections
import com.example.cinemates.R
import com.example.cinemates.databinding.ListItemCrewLongBinding
import com.example.cinemates.databinding.ListItemCrewSmallBinding
import com.example.cinemates.databinding.ListItemFilterBinding
import com.example.cinemates.databinding.ListItemMovieSmallBinding
import com.example.cinemates.databinding.ListItemPersonSmallBinding
import com.example.cinemates.databinding.ListItemTvSmallBinding
import com.example.cinemates.model.*
import com.example.cinemates.view.ui.discover.DiscoverFragmentDirections

class FilterAdapter :
    BaseAdapter<Filter, ListItemFilterBinding>(R.layout.list_item_filter, emptyList()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding =
            DataBindingUtil.inflate<ListItemFilterBinding>(inflater, itemLayoutResId, parent, false)
        return BaseViewHolder(binding)
    }

    override fun onBindItem(binding: ListItemFilterBinding, item: Filter) {
        binding.filter = item
        binding.root.setOnClickListener { view ->
            val action =
                DiscoverFragmentDirections.actionDiscoverFragmentToFilterFragment(item)
            Navigation.findNavController(view).navigate(action)
        }

    }
}
