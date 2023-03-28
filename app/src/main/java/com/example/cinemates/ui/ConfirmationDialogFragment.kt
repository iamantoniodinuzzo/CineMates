package com.example.cinemates.ui

import android.app.Dialog
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.example.cinemates.R

/**
 * @author Antonio Di Nuzzo
 * Created 19/12/2021 at 09:27
 */
class ConfirmationDialogFragment     // Empty constructor required for DialogFragment
    (private val mConfirmationTypo: ConfirmationTypo) : DialogFragment() {
    enum class ConfirmationTypo {
        DELETE, DISCONNECT
    }

    // Defines the listener interface
    interface ConfirmationDialogListener {
        fun onFinisConfirmationDialog(typo: ConfirmationTypo?)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val title = requireArguments().getString("title")
        val message = requireArguments().getString("message")
        val positiveButton = requireArguments().getString("positiveBtn")
        val alertDialogBuilder = AlertDialog.Builder(
            requireActivity(), R.style.AppDialogTheme
        )
        alertDialogBuilder.setTitle(title)
        alertDialogBuilder.setMessage(message)
        alertDialogBuilder.setPositiveButton(positiveButton) { _, _ -> // on success
            sendBackResult(mConfirmationTypo)
        }
        alertDialogBuilder.setNegativeButton("No") { dialog, _ ->
            dialog?.dismiss()
        }
        return alertDialogBuilder.create()
    }

    // Call this method to send the data back to the parent fragment
    private fun sendBackResult(typo: ConfirmationTypo) {
        // Notice the use of `getTargetFragment` which will be set when the dialog is displayed
        val listener = targetFragment as ConfirmationDialogListener?
        listener!!.onFinisConfirmationDialog(typo)
        dismiss()
    }

    companion object {
        /**
         * Crea una nuova istanza della dialog
         *
         * @param title
         * @param message
         * @param positiveButton
         * @param typo           La tipologia della dialog
         * @return
         */
        fun newInstance(
            title: String,
            message: String,
            positiveButton: String,
            typo: ConfirmationTypo
        ): ConfirmationDialogFragment {
            val frag = ConfirmationDialogFragment(typo)
            val args = Bundle()
            args.putString("title", title)
            args.putString("message", message)
            args.putString("positiveBtn", positiveButton)
            frag.arguments = args
            return frag
        }
    }
}