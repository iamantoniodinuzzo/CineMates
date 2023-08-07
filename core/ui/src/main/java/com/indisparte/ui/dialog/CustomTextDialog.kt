package com.indisparte.ui.dialog

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import com.indisparte.ui.R

/**
 * A custom text dialog for displaying a dialog with a title, message, and customizable button actions.
 *
 * @param context The context in which the dialog is shown.
 * @param title The title of the dialog.
 * @param message The message displayed in the dialog.
 * @param positiveButtonText The text for the positive button.
 * @param positiveButtonAction The action to be performed when the positive button is clicked.
 * @param negativeButtonText The text for the optional negative button. (Default: null)
 * @param negativeButtonAction The action to be performed when the negative button is clicked. (Default: null)
 * @param styleResId The resource ID of the custom dialog style. (Default: R.style.CustomDialogStyle)
 * @param isCancelable If the dialog must be cancelable. (Default: false)
 * @author Antonio Di Nuzzo (Indisparte)
 */
class CustomTextDialog(
    private val context: Context,
    private val title: String,
    private val message: String,
    private val positiveButtonText: String,
    private val positiveButtonAction: (() -> Unit),
    private val negativeButtonText: String? = null,
    private val negativeButtonAction: (() -> Unit)? = null,
    private val styleResId: Int = R.style.CustomDialogStyle,
    private val isCancelable: Boolean = false
) {
    /**
     * Shows the custom text dialog.
     */
    fun show() {
        val dialogBuilder = AlertDialog.Builder(context, styleResId)
        dialogBuilder.setTitle(title)
        dialogBuilder.setMessage(message)
        dialogBuilder.setCancelable(isCancelable)

        dialogBuilder.setPositiveButton(positiveButtonText) { dialogInterface: DialogInterface, _: Int ->
            positiveButtonAction.invoke()
            dialogInterface.dismiss()
        }

        negativeButtonText?.let {
            dialogBuilder.setNegativeButton(negativeButtonText) { dialogInterface: DialogInterface, _: Int ->
                negativeButtonAction?.invoke()
                dialogInterface.dismiss()
            }
        }

        val dialog = dialogBuilder.create()
        dialog.show()
    }
}
