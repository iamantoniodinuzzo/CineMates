package com.example.cinemates.view.ui.profile;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.cinemates.adapter.SectionRecyclerViewAdapter;
import com.example.cinemates.databinding.FragmentProfileBinding;
import com.example.cinemates.model.data.Movie;
import com.example.cinemates.model.data.Person;
import com.example.cinemates.model.data.Section;
import com.example.cinemates.util.ViewSize;
import com.example.cinemates.view.viewmodel.DbViewModel;
import com.google.android.material.transition.MaterialFadeThrough;

import java.util.ArrayList;
import java.util.List;

public class ProfileFragment extends Fragment {

    private FragmentProfileBinding mBinding;
    private Section<Movie> mMovieSection;
    private Section<Person> mPersonSection;
    private DbViewModel mDbViewModel;
    private SectionRecyclerViewAdapter mAdapter;
    private List<Section<?>> mSectionList;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAdapter = new SectionRecyclerViewAdapter(this, getContext());
        mMovieSection = new Section<>("Favorites", "Movies", Movie.class, null, ViewSize.SMALL);
        mPersonSection = new Section<>("Favorites", "Actors", Person.class, null, ViewSize.SMALL);
        mSectionList = new ArrayList<>();
        mSectionList.add(mMovieSection);
        mSectionList.add(mPersonSection);
        mDbViewModel = new ViewModelProvider(getActivity()).get(DbViewModel.class);
        setupMotionAnimations();
    }

    private void setupMotionAnimations() {
        setEnterTransition(new MaterialFadeThrough());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mBinding = FragmentProfileBinding.inflate(inflater, container, false);
        mBinding.recyclerView.setAdapter(mAdapter);
        mAdapter.addItems(mSectionList);
        mBinding.statHours.statTitle.setText("Hours");
        mBinding.statWatchedCounter.statTitle.setText("Movies Seen");
        mBinding.statToSeeCounter.statTitle.setText("Movies To See");
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mMovieSection.setMutableLiveData(mDbViewModel.getFavorite_movies());
        mPersonSection.setMutableLiveData(mDbViewModel.getFavorite_persons());
        mDbViewModel.getAllFavoritesMovies();
        mDbViewModel.getAllFavoritesPersons();
        mDbViewModel.getAllWithStatus(Movie.PersonalStatus.SEEN);
        mBinding.statHours.statContent.setText(formatTime(mDbViewModel.sumRuntimeAllWatchedMovies()));
        mBinding.statWatchedCounter.statContent.setText(String.valueOf(mDbViewModel.getMovieCountByStatus(Movie.PersonalStatus.SEEN)));
        mBinding.statToSeeCounter.statContent.setText(String.valueOf(mDbViewModel.getMovieCountByStatus(Movie.PersonalStatus.TO_SEE)));

    }

    private String formatTime(long runtime) {
        long hours = (long) (runtime / 60);
//        int minutes = (int) (runtime % 60);
        return String.valueOf(hours);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mBinding = null;
    }
}