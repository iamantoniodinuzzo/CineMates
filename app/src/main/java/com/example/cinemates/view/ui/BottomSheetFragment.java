package com.example.cinemates.view.ui;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;

import com.example.cinemates.databinding.FragmentBottomsheetBinding;
import com.example.cinemates.model.data.Movie;
import com.example.cinemates.model.data.PersonalStatus;
import com.example.cinemates.view.viewmodel.DbViewModel;
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
        // TODO: 25/08/2022 Error with set favorite, need other branch to solve issues w Room
     /*   mBinding.favoriteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mMovie.setFavorite();
                if (mMovie.isFavorite())
                    Toast.makeText(getContext(), "Added to favorites", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(getContext(), "Removed from favorites", Toast.LENGTH_SHORT).show();

                update(dialog);
            }
        });*/

       /* mBinding.watchlistBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mMovie.setPersonalStatus(PersonalStatus.TO_SEE))
                    Toast.makeText(getContext(), "Added to watch list!", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(getContext(), "Removed from watch list!", Toast.LENGTH_SHORT).show();
                update(dialog);
            }
        });*/

     /*   mBinding.watchedBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mMovie.setPersonalStatus(Movie.PersonalStatus.SEEN))
                    Toast.makeText(getContext(), "Added to watched list!", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(getContext(), "Removed from watched list!", Toast.LENGTH_SHORT).show();

                update(dialog);
            }
        });*/


        return dialog;
    }

    private void update(BottomSheetDialog dialog) {
        //This 'if' check if movie has no propriety for stay into db
       /* if (!mMovie.isFavorite() && mMovie.getPersonalStatus() == Movie.PersonalStatus.EMPTY) {
            mViewModel.delete(mMovie);
            return;
        } else if (mMovie.getPersonalStatus() == null)
            mMovie.setPersonalStatus(Movie.PersonalStatus.EMPTY);
        mViewModel.insert(mMovie);
        mBinding.setMovie(mMovie);
        dialog.dismiss();*/
    }


}
