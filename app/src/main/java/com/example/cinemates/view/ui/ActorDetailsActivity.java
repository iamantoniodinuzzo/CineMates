package com.example.cinemates.view.ui;


import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.cinemates.R;
import com.example.cinemates.adapter.ItemsRecyclerViewAdapter;
import com.example.cinemates.databinding.ActivityActorDetailsBinding;
import com.example.cinemates.model.data.Actor;
import com.example.cinemates.model.data.Movie;
import com.example.cinemates.model.data.Person;
import com.example.cinemates.util.ViewSize;
import com.example.cinemates.view.viewmodel.DbViewModel;
import com.example.cinemates.view.viewmodel.MovieViewModel;

import java.util.List;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class ActorDetailsActivity extends AppCompatActivity {

    private ActivityActorDetailsBinding mBinding;
    private ItemsRecyclerViewAdapter<Movie> mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivityActorDetailsBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());
        DbViewModel dbViewModel = new ViewModelProvider(this).get(DbViewModel.class);
        Person person = (Person) getIntent().getExtras().getSerializable("person");
        MovieViewModel viewModel = new ViewModelProvider(this).get(MovieViewModel.class);
        mAdapter = new ItemsRecyclerViewAdapter<>(ViewSize.SMALL);

        if (dbViewModel.getPerson(person) != null)
            mBinding.fab.setImageDrawable(getDrawable(R.drawable.ic_baseline_favorite_24));
        else
            mBinding.fab.setImageDrawable(getDrawable(R.drawable.ic_baseline_favorite_border_24));

        mBinding.recyclerView.setAdapter(mAdapter);

        viewModel.getActor().observe(this, new Observer<Actor>() {
            @Override
            public void onChanged(Actor actor) {
                mBinding.setActor(actor);
            }
        });
        viewModel.getMoviesByActor().observe(this, new Observer<List<Movie>>() {
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
                onBackPressed();
            }
        });

        mBinding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                person.setFavorite();
                if (dbViewModel.getPerson(person) != null) {//it was already into favorite
                    dbViewModel.delete(person);//should delete it
                    mBinding.fab.setImageDrawable(getDrawable(R.drawable.ic_baseline_favorite_border_24));
                    Toast.makeText(ActorDetailsActivity.this, "Removed from favorite", Toast.LENGTH_SHORT).show();
                } else {
                    dbViewModel.insert(person);
                    mBinding.fab.setImageDrawable(getDrawable(R.drawable.ic_baseline_favorite_24));
                    Toast.makeText(ActorDetailsActivity.this, "Added to favorite", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


}