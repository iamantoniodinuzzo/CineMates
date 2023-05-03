package com.example.cinemates.ui.details.movie

import android.content.Context
import android.view.Gravity
import android.view.LayoutInflater
import android.view.WindowManager
import androidx.appcompat.app.AlertDialog
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
        binding = LayoutCollectionDialogBinding.inflate(LayoutInflater.from(context))
        dialogBuilder.setView(binding.root)

        alertDialog = dialogBuilder.create()
        alertDialog.window?.setGravity(Gravity.CENTER)
        alertDialog.window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN)
        alertDialog.setCanceledOnTouchOutside(true)

        adapter.items = collection.parts
        binding.partsOfCollection.adapter = adapter
        binding.collection = collection

        alertDialog.show()
    }


}