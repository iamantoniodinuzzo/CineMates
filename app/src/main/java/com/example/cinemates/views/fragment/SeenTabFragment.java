package com.example.cinemates.views.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cinemates.databinding.FragmentSeenBinding;
import com.example.cinemates.util.ChangeRvLayout;


public class SeenTabFragment extends Fragment implements ChangeRvLayout {

    private FragmentSeenBinding mBinding;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView (LayoutInflater inflater,
                              ViewGroup container,
                              Bundle savedInstanceState) {
        mBinding = FragmentSeenBinding.inflate(inflater, container, false);

        mBinding.recyclerView.setEmptyView(mBinding.emptyView.getRoot());

        return mBinding.getRoot();

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