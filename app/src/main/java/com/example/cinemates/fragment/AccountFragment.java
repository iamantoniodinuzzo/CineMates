package com.example.cinemates.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.cinemates.R;
import com.example.cinemates.adapter.ConnectServiceAdapter;
import com.example.cinemates.databinding.FragmentAccountBinding;
import com.example.cinemates.model.ConnectService;

import java.util.ArrayList;
import java.util.List;


public class AccountFragment extends Fragment {

    private FragmentAccountBinding mBinding;
    private ConnectServiceAdapter mAdapter;
    private List<ConnectService> mConnectServices;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAdapter = new ConnectServiceAdapter();

        initConnectServicesList();


    }

    private void initConnectServicesList() {
        mConnectServices = new ArrayList<>();
        mConnectServices.add(new ConnectService("Sincronizza i dati dell'account", R.drawable.ic_round_autorenew_24, 1));
        mConnectServices.add(new ConnectService("Elimina account", R.drawable.ic_baseline_delete_24, 1));
        mConnectServices.add(new ConnectService("Disconnessione", R.drawable.ic_baseline_person_24, 1));

        mAdapter.addItems(mConnectServices);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mBinding = FragmentAccountBinding.inflate(inflater, container, false);
        mBinding.recyclerView.setAdapter(mAdapter);

        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mBinding.toolbar.setNavigationIcon(R.drawable.ic_outline_arrow_back_24);
        mBinding.toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).popBackStack();
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mBinding = null;

    }
}