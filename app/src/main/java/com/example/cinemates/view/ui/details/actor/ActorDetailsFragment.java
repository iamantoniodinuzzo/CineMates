package com.example.cinemates.view.ui.details.actor;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.example.cinemates.R;
import com.example.cinemates.adapter.ItemsRecyclerViewAdapter;
import com.example.cinemates.databinding.FragmentActorDetailsBinding;
import com.example.cinemates.model.data.Actor;
import com.example.cinemates.model.data.Movie;
import com.example.cinemates.model.data.Person;
import com.example.cinemates.util.ViewSize;
import com.example.cinemates.view.ui.ActorDetailsFragmentArgs;
import com.example.cinemates.view.viewmodel.DbViewModel;
import com.example.cinemates.view.viewmodel.MovieViewModel;

import java.util.List;

public class ActorDetailsFragment extends Fragment {

    private FragmentActorDetailsBinding mBinding;
    private ItemsRecyclerViewAdapter<Movie> mAdapter;
    private MovieViewModel viewModel;
    private DbViewModel dbViewModel;
    private Person person;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dbViewModel = new ViewModelProvider(getActivity()).get(DbViewModel.class);
        person = ActorDetailsFragmentArgs.fromBundle(getArguments()).getPerson();
        viewModel = new ViewModelProvider(getActivity()).get(MovieViewModel.class);
        mAdapter = new ItemsRecyclerViewAdapter<>(ViewSize.SMALL);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = FragmentActorDetailsBinding.inflate(
                inflater,
                container,
                false
        );

        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (dbViewModel.getPerson(person) != null)
            mBinding.fab.setImageDrawable(getActivity().getDrawable(R.drawable.ic_baseline_favorite_24));
        else
            mBinding.fab.setImageDrawable(getActivity().getDrawable(R.drawable.ic_baseline_favorite_border_24));

        mBinding.recyclerView.setAdapter(mAdapter);

        viewModel.getActor().observe(getViewLifecycleOwner(), new Observer<Actor>() {
            @Override
            public void onChanged(Actor actor) {
                mBinding.setActor(actor);
            }
        });
        viewModel.getMoviesByActor().observe(getViewLifecycleOwner(), new Observer<List<Movie>>() {
            @Override
            public void onChanged(List<Movie> movies) {
                mAdapter.addItems(movies);
            }
        });

        viewModel.getMoviesByActor(String.valueOf(person.getId()));
        viewModel.getActorDetails(person.getId());

        mBinding.toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigateUp();
            }
        });

        mBinding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                person.setFavorite();
                if (dbViewModel.getPerson(person) != null) {//it was already into favorite
                    dbViewModel.delete(person);//should delete it
                    mBinding.fab.setImageDrawable(getActivity().getDrawable(R.drawable.ic_baseline_favorite_border_24));
                    Toast.makeText(getContext(), "Removed from favorite", Toast.LENGTH_SHORT).show();
                } else {
                    dbViewModel.insert(person);
                    mBinding.fab.setImageDrawable(getActivity().getDrawable(R.drawable.ic_baseline_favorite_24));
                    Toast.makeText(getContext(), "Added to favorite", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mBinding = null;
    }


}