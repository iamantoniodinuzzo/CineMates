package com.example.cinemates.views;


import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.cinemates.adapter.MovieRecyclerViewAdapter;
import com.example.cinemates.databinding.ActivityActorDetailsBinding;
import com.example.cinemates.model.Actor;
import com.example.cinemates.model.Movie;
import com.example.cinemates.model.Person;
import com.example.cinemates.viewmodel.MovieViewModel;

import java.util.List;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class ActorDetailsActivity extends AppCompatActivity {

    private ActivityActorDetailsBinding mBinding;
    private MovieViewModel mViewModel;
    private MovieRecyclerViewAdapter mAdapter;
    private Person mPerson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivityActorDetailsBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());

        mPerson = (Person) getIntent().getExtras().getSerializable("person");
        mViewModel = new ViewModelProvider(this).get(MovieViewModel.class);
        mAdapter = new MovieRecyclerViewAdapter();
        mBinding.recyclerView.setAdapter(mAdapter);

        mViewModel.getActor().observe(this, new Observer<Actor>() {
            @Override
            public void onChanged(Actor actor) {
                mBinding.setActor(actor);
            }
        });
        mViewModel.getMoviesByActor().observe(this, new Observer<List<Movie>>() {
            @Override
            public void onChanged(List<Movie> movies) {
                mAdapter.addItems(movies);
            }
        });

        mViewModel.getMoviesByActor(String.valueOf(mPerson.getId()));
        mViewModel.getActorDetails(mPerson.getId());

        mBinding.toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        mBinding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(ActorDetailsActivity.this, "On develop", Toast.LENGTH_SHORT).show();
            }
        });
    }


}