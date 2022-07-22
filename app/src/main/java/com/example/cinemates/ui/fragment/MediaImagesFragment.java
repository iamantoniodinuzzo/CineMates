package com.example.cinemates.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.cinemates.adapter.ImageRecyclerViewAdapter;
import com.example.cinemates.databinding.FragmentMediaImagesBinding;
import com.example.cinemates.model.Images;
import com.example.cinemates.model.Movie;
import com.example.cinemates.viewmodel.MovieViewModel;

/**
 * @author Antonio Di Nuzzo
 * Created 19/06/2022 at 09:54
 */
public class MediaImagesFragment extends Fragment {
    private FragmentMediaImagesBinding mBinding;
    private MovieViewModel mViewModel;
    private ImageRecyclerViewAdapter mPosterAdapter, mBackdropAdapter;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewModel = new ViewModelProvider(getActivity()).get(MovieViewModel.class);
        mPosterAdapter = new ImageRecyclerViewAdapter(getContext());
        mBackdropAdapter = new ImageRecyclerViewAdapter(getContext());
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = FragmentMediaImagesBinding.inflate(inflater, container, false);
        Movie movie = (Movie) getArguments().getSerializable("movie");
        mBinding.postersRv.setAdapter(mPosterAdapter);
        mBinding.postersRv.setEmptyView(mBinding.emptyPosterView.getRoot());
        mBinding.backdropRv.setEmptyView(mBinding.emptyBackdropView.getRoot());
        mBinding.backdropRv.setAdapter(mBackdropAdapter);

        mViewModel.getImages().observe(getViewLifecycleOwner(), new Observer<Images>() {
            @Override
            public void onChanged(Images images) {
                mPosterAdapter.addItems(images.getPosters());
                mBackdropAdapter.addItems(images.getBackdrops());
            }
        });
        mViewModel.getImages(movie.getId());
        return mBinding.getRoot();
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mBinding = null;
    }
}
