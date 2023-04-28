package com.example.cinemates.ui.details


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.cinemates.data.remote.response.image.ImageType
import com.example.cinemates.databinding.FragmentListingItemsBinding
import com.example.cinemates.ui.adapter.ImageAdapter
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

/**
 *@author Antonio Di Nuzzo (Indisparte)
 */
class MediaImagesFragment : BottomSheetDialogFragment() {
    private var _binding: FragmentListingItemsBinding? = null
    private val binding: FragmentListingItemsBinding
        get() = _binding!!
    private lateinit var adapter: ImageAdapter
    private val args: MediaImagesFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        adapter = ImageAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentListingItemsBinding.inflate(inflater, container, false)


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()

    }

    private fun setupRecyclerView() {
        binding.layoutRecyclerView.apply {
            val images = args.images.asList()
            if(images.isNotEmpty()) {
                val imageType = images[0].imageType
                if (imageType == ImageType.BACKDROP) {
                    recyclerView.layoutManager =
                        LinearLayoutManager(requireContext())
                } else if (imageType == ImageType.POSTER) {
                    recyclerView.layoutManager =
                        GridLayoutManager(requireContext(), 3)
                }
                recyclerView.adapter = adapter
                adapter.items = images
            }
            recyclerView.adapter = adapter
            adapter.items = images
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}