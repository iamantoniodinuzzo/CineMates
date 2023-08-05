package com.indisparte.movie_details.dialog

import android.content.Context
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.WindowManager
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.indisparte.model.entity.Movie
import com.indisparte.movie_details.R
import com.indisparte.movie_details.databinding.LayoutCollectionDialogBinding
import com.indisparte.ui.adapter.MovieAdapter

/**
 * @author Antonio Di Nuzzo (Indisparte)
 */
internal class CollectionDialog(
    private val context: Context,
) : AlertDialog(context) {

    private lateinit var binding: LayoutCollectionDialogBinding
    private lateinit var recyclerView: RecyclerView
    private val adapter by lazy {
        MovieAdapter()
    }

    fun setData(itemList: List<Movie>?) {
        adapter.submitList(itemList)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = LayoutCollectionDialogBinding.inflate(LayoutInflater.from(context))
        setContentView(binding.root)
        setupDialogAppearance()
        setupRecyclerView()
    }

    private fun setupDialogAppearance() {
        window?.let { window ->
            window.setBackgroundDrawableResource(android.R.color.transparent) // Remove default dialog background
            window.setGravity(Gravity.CENTER)
            window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN)
            window.setLayout(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
            window.setBackgroundDrawable(
                ContextCompat.getDrawable(
                    context,
                    R.drawable.dialog_background
                )
            ) // Set custom rounded background
        }
        setCanceledOnTouchOutside(true)
    }

    private fun setupRecyclerView() {
        recyclerView = binding.partsOfCollection
        recyclerView.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        recyclerView.adapter = adapter
    }


}