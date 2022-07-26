package com.example.cinemates.ui.fragment;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;

import com.example.cinemates.databinding.FragmentBottomsheetBinding;
import com.example.cinemates.model.Movie;
import com.example.cinemates.viewmodel.DbViewModel;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

/**
 * @author Antonio Di Nuzzo
 * Created 26/07/2022 at 09:01
 */
public class BottomSheetFragment extends BottomSheetDialogFragment {
    private FragmentBottomsheetBinding mBinding;
    private DbViewModel mViewModel;
    private Movie mMovie;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewModel = new ViewModelProvider(getActivity()).get(DbViewModel.class);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        BottomSheetDialog dialog = (BottomSheetDialog) super.onCreateDialog(savedInstanceState);
        mBinding = FragmentBottomsheetBinding.inflate(getLayoutInflater());
        dialog.setContentView(mBinding.getRoot());
        mMovie = BottomSheetFragmentArgs.fromBundle(getArguments()).getMovie();
        Movie tmp = mViewModel.getMovie(mMovie);
        if (tmp != null)
            mMovie = tmp;

        mBinding.setMovie(mMovie);
        mBinding.favoriteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO: 26/07/2022 Add to favorite
                mMovie.setFavorite();
                update(dialog);
            }
        });

        mBinding.watchlistBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO: 26/07/2022 Add to watchlist
                mMovie.setPersonalStatus(Movie.PersonalStatus.TO_SEE);
                update(dialog);
            }
        });

        mBinding.watchedBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO: 26/07/2022 Add to watched
                mMovie.setPersonalStatus(Movie.PersonalStatus.SEEN);
                update(dialog);
            }
        });


        return dialog;
    }

    private void update(BottomSheetDialog dialog) {
        mViewModel.insert(mMovie);
        mBinding.setMovie(mMovie);
        dialog.dismiss();
    }


}
