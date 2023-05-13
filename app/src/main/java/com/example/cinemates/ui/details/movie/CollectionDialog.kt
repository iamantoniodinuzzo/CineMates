package com.example.cinemates.ui.details.movie

import android.content.Context
import android.view.Gravity
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.WindowManager
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import com.example.cinemates.R
import com.example.cinemates.databinding.LayoutCollectionDialogBinding
import com.example.cinemates.domain.model.movie.Collection
import com.example.cinemates.ui.adapter.MediaAdapter

/**
 * @author Antonio Di Nuzzo (Indisparte)
 */
class CollectionDialog(
    private val context: Context,
    private val collection: Collection,
    private val adapter: MediaAdapter
) {

    private lateinit var alertDialog: AlertDialog
    private lateinit var binding: LayoutCollectionDialogBinding

    fun showDialog() {
        val dialogBuilder = AlertDialog.Builder(context)
        dialogBuilder.setTitle("Parts of ${collection.name}")
        binding = LayoutCollectionDialogBinding.inflate(LayoutInflater.from(context))
        dialogBuilder.setView(binding.root)

        alertDialog = dialogBuilder.create()
        alertDialog.window?.let { window ->
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
        alertDialog.setCanceledOnTouchOutside(true)

        adapter.items = collection.parts
        binding.partsOfCollection.adapter = adapter

        alertDialog.show()
    }


}