package com.example.cinemates.util;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;

import androidx.annotation.Nullable;

import com.example.cinemates.R;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

/**
 * @author Antonio Di Nuzzo
 * Created 24/06/2022 at 14:11
 */
public class DialogFactory {
    /**
     * Mostra una semplice dialog di errore con del testo con un positive button legato ad una azione
     *
     * @param context                     il contesto in cui mostrare la dialog
     * @param message                     il messaggio da mostrare nella dialog
     * @param positive_text_btn           il testo da far comparire sul positive button
     * @param negative_text_btn           il testo da far comparire sul negative button
     * @param positiveButtonClickListener l'azione da svolgere al click del positive button
     * @param cancelButtonOnClickListener l'azione da svolgere al click del negative button
     * @return
     */
    public static Dialog createSimpleOkCancelDialog(Context context,
                                                    String message,
                                                    String positive_text_btn,
                                                    @Nullable String negative_text_btn,
                                                    @Nullable DialogInterface.OnClickListener positiveButtonClickListener,
                                                    @Nullable DialogInterface.OnClickListener cancelButtonOnClickListener) {
        final MaterialAlertDialogBuilder alertDialog = new MaterialAlertDialogBuilder(context)
                .setMessage(message)
                .setCancelable(false)
                .setPositiveButton(positive_text_btn, positiveButtonClickListener)
                .setNegativeButton(negative_text_btn, cancelButtonOnClickListener);
        return alertDialog.create();
    }

}
