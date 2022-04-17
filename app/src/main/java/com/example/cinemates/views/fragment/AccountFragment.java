package com.example.cinemates.views.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.cinemates.databinding.FragmentAccountBinding;
import com.example.cinemates.databinding.LayoutItemConnectServiceBinding;


public class AccountFragment extends Fragment implements ConfirmationDialogFragment.ConfirmationDialogListener {

    private FragmentAccountBinding mBinding;
    private LayoutItemConnectServiceBinding mItemConnectServiceBinding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mBinding = FragmentAccountBinding.inflate(inflater, container, false);
        //merged layout
        mItemConnectServiceBinding = LayoutItemConnectServiceBinding.bind(mBinding.getRoot());

        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mBinding.toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).popBackStack();
            }
        });

        mItemConnectServiceBinding.actionSyncAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO Sync account showing snack bar
            }
        });

        mItemConnectServiceBinding.actionDeleteAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog("Delete account", "Are you sure you want to delete this account?", "Yes, delete it!", ConfirmationDialogFragment.ConfirmationTypo.DELETE);
            }
        });

        mItemConnectServiceBinding.actionDisconnect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog("Disconnection", "Are you sure you want to log out of this account?", "Si, please!", ConfirmationDialogFragment.ConfirmationTypo.DISCONNECT);

            }
        });
    }

    private void showDialog(String title, String message, String positiveButton, ConfirmationDialogFragment.ConfirmationTypo typo) {
        FragmentManager fm = getParentFragmentManager();
        ConfirmationDialogFragment confirmationDialogFragment = ConfirmationDialogFragment.newInstance(title, message, positiveButton, typo);
        confirmationDialogFragment.setTargetFragment(AccountFragment.this, 300);
        confirmationDialogFragment.show(fm, "confirmation_dialog");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mBinding = null;

    }

    @Override
    public void onFinisConfirmationDialog(ConfirmationDialogFragment.ConfirmationTypo typo) {
        switch (typo) {
            case DELETE:
                //TODO delete account
                Toast.makeText(getContext(), "Delete account", Toast.LENGTH_SHORT).show();
                break;
            case DISCONNECT:
                //TODO disconnect account
                Toast.makeText(getContext(), "Disconnecting", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}