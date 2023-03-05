package com.example.cinemates.view.ui.adapter


/**
 * @author Antonio Di Nuzzo (Indisparte)
 */
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.cinemates.R
import com.example.cinemates.databinding.ListItemCrewLongBinding
import com.example.cinemates.databinding.ListItemPersonSmallBinding
import com.example.cinemates.model.Crew

class CrewAdapter :
    MultipleViewSizeAdapter<Crew, ListItemCrewLongBinding, ListItemPersonSmallBinding>(
        R.layout.list_item_crew_long,
        R.layout.list_item_person_small,
        emptyList()
    ) {
    override fun onBindLongItem(binding: ListItemCrewLongBinding, item: Crew) {
        binding.crew = item
    }

    override fun onBindSmallItem(binding: ListItemPersonSmallBinding, item: Crew) {
        binding.person = item
    }


}
