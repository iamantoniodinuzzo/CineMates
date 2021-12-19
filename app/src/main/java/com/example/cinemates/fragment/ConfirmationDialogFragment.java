package com.example.cinemates.fragment;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import com.example.cinemates.R;

/**
 * @author Antonio Di Nuzzo
 * Created 19/12/2021 at 09:27
 */
public class ConfirmationDialogFragment extends DialogFragment {

    public enum ConfirmationTypo{DELETE, DISCONNECT}
    private final ConfirmationTypo mConfirmationTypo;

    public ConfirmationDialogFragment(ConfirmationTypo confirmationTypo) {
        // Empty constructor required for DialogFragment
        this.mConfirmationTypo = confirmationTypo;
    }
    // Defines the listener interface
    public interface ConfirmationDialogListener {
        void onFinisConfirmationDialog(ConfirmationTypo typo);
    }

    public static ConfirmationDialogFragment newInstance(@NonNull String title,@NonNull String message,@NonNull String positiveButton, ConfirmationTypo typo) {
        ConfirmationDialogFragment frag = new ConfirmationDialogFragment(typo);
        Bundle args = new Bundle();
        args.putString("title", title);
        args.putString("message", message);
        args.putString("positiveBtn", positiveButton);
        frag.setArguments(args);

        return frag;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        String title = getArguments().getString("title");
        String message = getArguments().getString("message");
        String positiveButton = getArguments().getString("positiveBtn");

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity(), R.style.AppDialogTheme);
        alertDialogBuilder.setTitle(title);
        alertDialogBuilder.setMessage(message);
        alertDialogBuilder.setPositiveButton(positiveButton,  new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // on success
                sendBackResult(mConfirmationTypo);
            }
        });
        alertDialogBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (dialog != null ) {
                    dialog.dismiss();
                }
            }

        });

        return alertDialogBuilder.create();
    }

    // Call this method to send the data back to the parent fragment
    public void sendBackResult(ConfirmationTypo typo) {
        // Notice the use of `getTargetFragment` which will be set when the dialog is displayed
        ConfirmationDialogListener listener = (ConfirmationDialogListener) getTargetFragment();
        listener.onFinisConfirmationDialog(typo);
        dismiss();
    }
}
