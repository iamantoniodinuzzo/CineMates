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

import com.example.cinemates.R;
import com.example.cinemates.databinding.FragmentDetailActorMediaBinding;
import com.example.cinemates.model.Actor;
import com.example.cinemates.model.Person;
import com.example.cinemates.viewmodel.MovieViewModel;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;


public class DetailActorBottomSheetFragment extends BottomSheetDialogFragment {
    private FragmentDetailActorMediaBinding mBinding;
    private MovieViewModel mViewModel;
    private Person mPerson;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewModel = new ViewModelProvider(getActivity()).get(MovieViewModel.class);
        mPerson = DetailActorMediaFragmentArgs.fromBundle(getArguments()).getPerson();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = FragmentDetailActorMediaBinding.inflate(inflater, container, false);
        return mBinding.getRoot();
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mViewModel.getActor().observe(getViewLifecycleOwner(), new Observer<Actor>() {
            @Override
            public void onChanged(Actor actor) {
                mBinding.setActor(actor);
            }
        });
        mViewModel.getActorDetails(mPerson.getId());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mBinding = null;
    }
}