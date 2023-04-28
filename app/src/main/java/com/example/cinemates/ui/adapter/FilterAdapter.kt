package com.example.cinemates.ui.adapter


/**
 * @author Antonio Di Nuzzo (Indisparte)
 */
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import com.example.cinemates.R
import com.example.cinemates.common.SingleViewAdapter
import com.example.cinemates.databinding.ListItemFilterBinding
import com.example.cinemates.domain.model.common.Filter
import com.example.cinemates.ui.discover.DiscoverFragmentDirections

class FilterAdapter :
    SingleViewAdapter<Filter, ListItemFilterBinding>(R.layout.list_item_filter,) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SingleViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding =
            DataBindingUtil.inflate<ListItemFilterBinding>(inflater, itemLayoutResId, parent, false)
        return SingleViewHolder(binding)
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
