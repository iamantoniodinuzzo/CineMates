package com.indisparte.ui.dialog

import android.content.Context
import androidx.appcompat.app.AlertDialog
import com.indisparte.ui.R

/**
 *@author Antonio Di Nuzzo
 */
class DialogHelper(private val context: Context) {

    fun createConfirmationDialog(
        title: String,
        message: String,
        positiveButtonText: String,
        negativeButtonText: String,
        positiveAction: () -> Unit,
        negativeAction: () -> Unit,
    ): AlertDialog {
        return AlertDialog.Builder(context, R.style.CustomDialogStyle)
            .setTitle(title)
            .setMessage(message)
            .setPositiveButton(positiveButtonText) { _, _ -> positiveAction.invoke() }
            .setNegativeButton(negativeButtonText) { _, _ -> negativeAction.invoke() }
            .create()
    }
}
