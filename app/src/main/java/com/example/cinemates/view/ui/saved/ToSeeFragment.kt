package com.example.cinemates.view.ui.saved

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.cinemates.adapter.ItemsRecyclerViewAdapter
import com.example.cinemates.databinding.FragmentListingBinding
import com.example.cinemates.model.data.Movie
import com.example.cinemates.util.ViewSize
import com.example.cinemates.view.viewmodel.DbMovieViewModel

/**
 * @author Antonio Di Nuzzo
 * Created 25/07/2022 at 08:24
 */
class ToSeeFragment : Fragment() {
    private var _binding: FragmentListingBinding? = null
    private val binding: FragmentListingBinding
        get() = _binding!!
    private val dbViewModel: DbMovieViewModel by activityViewModels()
    private lateinit var adapter: ItemsRecyclerViewAdapter<Movie>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        adapter = ItemsRecyclerViewAdapter(ViewSize.SMALL)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentListingBinding.inflate(inflater, container, false)


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            recyclerView.adapter = adapter
            recyclerView.setEmptyView(emptyView.root)
            dbViewModel.toSee.observe(viewLifecycleOwner){movies->
                adapter.addItems(movies as MutableList<Movie>)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}