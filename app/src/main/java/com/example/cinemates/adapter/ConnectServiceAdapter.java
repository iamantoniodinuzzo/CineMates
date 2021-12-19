package com.example.cinemates.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cinemates.databinding.ListItemConnectServiceBinding;
import com.example.cinemates.model.ConnectService;

import java.util.ArrayList;
import java.util.List;

import info.movito.themoviedbapi.model.people.PersonCast;

/**
 * @author Antonio Di Nuzzo
 * Created 19/12/2021 at 08:23
 */
public class ConnectServiceAdapter extends RecyclerView.Adapter<ConnectServiceAdapter.ConnectServiceViewHolder> {
    private List<ConnectService> dataList = new ArrayList<>();


    @NonNull
    @Override
    public ConnectServiceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ListItemConnectServiceBinding listItemConnectServiceBinding = ListItemConnectServiceBinding.inflate(layoutInflater, parent, false);
        return new ConnectServiceViewHolder(listItemConnectServiceBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ConnectServiceViewHolder holder, int position) {
        ConnectService connectService = dataList.get(position);
        holder.mBinding.setConnectService(connectService);
        holder.mBinding.executePendingBindings();

    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public void addItems(List<ConnectService> dataList) {
        this.dataList.addAll(dataList);
        notifyDataSetChanged();
    }

    public static class ConnectServiceViewHolder extends RecyclerView.ViewHolder {
        ListItemConnectServiceBinding mBinding;

        ConnectServiceViewHolder(@NonNull ListItemConnectServiceBinding listItemConnectServiceBinding) {
            super(listItemConnectServiceBinding.getRoot());
            this.mBinding = listItemConnectServiceBinding;
        }
    }
}
