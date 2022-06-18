package com.example.cinemates.views.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cinemates.adapter.PersonRecyclerViewAdapter;
import com.example.cinemates.databinding.FragmentSearchActorBinding;
import com.example.cinemates.model.Cast;
import com.example.cinemates.util.ChangeRvLayout;
import com.example.cinemates.util.SearchData;
import com.example.cinemates.viewmodel.MovieViewModel;

import java.util.ArrayList;


public class SearchActorFragment extends Fragment implements ChangeRvLayout, SearchData {

    private FragmentSearchActorBinding mBinding;
    private PersonRecyclerViewAdapter mRecyclerViewAdapter;
    private MovieViewModel mViewModel;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mRecyclerViewAdapter = new PersonRecyclerViewAdapter();
        mViewModel = new ViewModelProvider(getActivity()).get(MovieViewModel.class);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mBinding = FragmentSearchActorBinding.inflate(inflater, container, false);
        mBinding.recyclerView.setAdapter(mRecyclerViewAdapter);
        mBinding.recyclerView.setEmptyView(mBinding.emptyView.getRoot());

        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mViewModel.getQueriesPeoples().observe(getViewLifecycleOwner(), new Observer<ArrayList<Cast>>() {
            @Override
            public void onChanged(ArrayList<Cast> people) {
                mRecyclerViewAdapter.addItems(people);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mBinding = null;
    }

    @Override
    public void changeLayout(RecyclerView.LayoutManager layoutManager) {
      /*  mBinding.recyclerView.setLayoutManager(layoutManager);
        mRecyclerViewAdapter.notifyItemRangeChanged(0, mRecyclerViewAdapter.getItemCount());*/

    }

    @Override
    public void bindData(String query) {
        mViewModel.getPeoplesBySearch(query);

    }
}