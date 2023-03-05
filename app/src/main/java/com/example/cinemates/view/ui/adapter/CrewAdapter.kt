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
import com.example.cinemates.databinding.ListItemCrewLongBinding
import com.example.cinemates.databinding.ListItemCrewSmallBinding
import com.example.cinemates.databinding.ListItemMovieSmallBinding
import com.example.cinemates.databinding.ListItemPersonSmallBinding
import com.example.cinemates.databinding.ListItemTvSmallBinding
import com.example.cinemates.model.Crew
import com.example.cinemates.model.Movie
import com.example.cinemates.model.Person
import com.example.cinemates.model.TvShow

class CrewAdapter : BaseAdapter<Crew, ListItemCrewLongBinding>(R.layout.list_item_crew_long, emptyList()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<ListItemCrewLongBinding>(inflater, itemLayoutResId, parent, false)
        return BaseViewHolder(binding)
    }

    override fun onBindItem(binding: ListItemCrewLongBinding, item: Crew) {
        binding.crew = item
        binding.root.setOnClickListener {view->
            /*val action = NavGraphDirections.actionGlobalActorDetailsFragment(item)
            Navigation.findNavController(view).navigate(action)*/
        }

    }
}
