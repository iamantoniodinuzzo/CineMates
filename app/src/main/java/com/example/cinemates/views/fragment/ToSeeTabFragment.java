package com.example.cinemates.views.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cinemates.databinding.FragmentToSeeBinding;
import com.example.cinemates.util.ChangeRvLayout;


public class ToSeeTabFragment extends Fragment implements ChangeRvLayout {


    private FragmentToSeeBinding mBinding;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView (LayoutInflater inflater,
                              ViewGroup container,
                              Bundle savedInstanceState) {
        mBinding = FragmentToSeeBinding.inflate(inflater, container, false);
        View view = mBinding.getRoot();
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mBinding = null;
    }

    @Override
    public void changeLayout(RecyclerView.LayoutManager layoutManager) {
        mBinding.recyclerView.setLayoutManager(layoutManager);
        Toast.makeText(getContext(),"LAyout modificato", Toast.LENGTH_SHORT).show();
    }
}