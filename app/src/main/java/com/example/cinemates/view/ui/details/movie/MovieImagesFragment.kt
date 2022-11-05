package com.example.cinemates.view.ui.details.movie

import android.app.DownloadManager
import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.cinemates.adapter.BaseAdapter
import com.example.cinemates.databinding.FragmentMovieImagesBinding
import com.example.cinemates.databinding.ListItemBackdropBinding
import com.example.cinemates.databinding.ListItemPosterBinding
import com.example.cinemates.model.entities.Image
import com.example.cinemates.util.BASE_URL
import com.example.cinemates.util.DialogFactory
import com.example.cinemates.util.inflater
import java.io.File

/**
 * @author Antonio Di Nuzzo
 * Created 19/06/2022 at 09:54
 */
class MovieImagesFragment : Fragment() {

    private var _binding: FragmentMovieImagesBinding? = null
    private val binding: FragmentMovieImagesBinding
        get() = _binding!!
    private lateinit var posterAdapter: BaseAdapter<Image, ListItemPosterBinding>
    private lateinit var backdropAdapter: BaseAdapter<Image, ListItemBackdropBinding>
    private val viewModel: MovieDetailsViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        posterAdapter = BaseAdapter(
            expressionOnCreateViewHolder = { viewGroup ->
                viewGroup inflater ListItemPosterBinding::inflate
            },
            expressionViewHolderBinding = { poster, binding ->
                binding.apply {
                    image = poster
                    root.setOnClickListener {
                        downloadImage(poster)
                    }
                }
            })
        backdropAdapter = BaseAdapter(
            expressionOnCreateViewHolder = { viewGroup ->
                viewGroup inflater ListItemBackdropBinding::inflate
            },
            expressionViewHolderBinding = { backdrop, binding ->
                binding.apply {
                    image = backdrop
                    root.setOnClickListener {
                        downloadImage(backdrop)
                    }
                }
            })
    }

    private fun downloadImage(image: Image) {
        DialogFactory.createSimpleOkCancelDialog(context,
            "Want to download this image?",
            "Download",
            "Cancel",
            { _, _ ->
                val filename =
                    "${viewModel.selectedMovie.value?.title}_${image.width}_${image.height}"
                try {
                    val dm =
                        requireContext().getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
                    val downloadUri = Uri.parse("$BASE_URL${image.file_path}")
                    val request = DownloadManager.Request(downloadUri)
                    request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI or DownloadManager.Request.NETWORK_MOBILE)
                        .setAllowedOverRoaming(false)
                        .setTitle(filename)
                        .setMimeType("image/jpeg") // Your file type. You can use this code to download other file types also.
                        .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
                        .setDestinationInExternalPublicDir(
                            Environment.DIRECTORY_PICTURES,
                            File.separator + filename + ".jpg"
                        )
                    dm.enqueue(request)
                    Toast.makeText(context, "Image download started.", Toast.LENGTH_SHORT)
                        .show()
                } catch (e: Exception) {
                    Toast.makeText(context, "Image download failed.", Toast.LENGTH_SHORT).show()
                }
            }) { dialogInterface, _ -> dialogInterface.dismiss() }.show()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMovieImagesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            postersRv.adapter = posterAdapter
            postersRv.setEmptyView(emptyPosterView.root)
            backdropRv.adapter = backdropAdapter
            backdropRv.setEmptyView(emptyBackdropView.root)
        }
        
        with(viewModel){
            posters.observe(viewLifecycleOwner){posters->
                posterAdapter.dataList = posters
            }
            backdrops.observe(viewLifecycleOwner){backdrops->
                backdropAdapter.dataList = backdrops
            }
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}