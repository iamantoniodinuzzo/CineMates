package com.example.cinemates.ui.saved

import android.os.Bundle
import android.view.View
import com.example.cinemates.databinding.ListItemMediaSmallBinding
import com.example.cinemates.common.ListFragment
import com.example.cinemates.domain.model.common.Media
import com.example.cinemates.ui.adapter.MediaAdapter

/**
 * @author Antonio Di Nuzzo
 * Created 25/07/2022 at 08:24
 */
class SeenFragment : ListFragment<Media, ListItemMediaSmallBinding, MediaAdapter>(MediaAdapter()) {


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            /*  dbViewModel.seen.observe(viewLifecycleOwner){movies->
                  adapter.addItems(movies)
                  counter=adapter.itemCount
              }*/
        }
    }
}