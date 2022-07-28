package com.example.cinemates.viewmodel;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.cinemates.model.Movie;
import com.example.cinemates.model.Person;
import com.example.cinemates.repository.DbRepository;

import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

/**
 * @author Antonio Di Nuzzo
 * Created 26/07/2022 at 08:33
 */
@HiltViewModel
public class DbViewModel extends ViewModel {
    private static final String TAG = DbViewModel.class.getSimpleName();
    private final DbRepository mDbRepository;
    private final MutableLiveData<List<Movie>> favorite_movies = new MutableLiveData<>(),
            to_see = new MutableLiveData<>(),
            seen = new MutableLiveData<>();
    private final MutableLiveData<List<Person>> favorite_persons = new MutableLiveData<>();
    private final CompositeDisposable disposables = new CompositeDisposable();


    @Inject
    public DbViewModel(DbRepository dbRepository) {
        mDbRepository = dbRepository;
    }

    public MutableLiveData<List<Movie>> getFavorite_movies() {
        return favorite_movies;
    }

    public MutableLiveData<List<Person>> getFavorite_persons() {
        return favorite_persons;
    }

    public MutableLiveData<List<Movie>> getTo_see() {
        return to_see;
    }

    public MutableLiveData<List<Movie>> getSeen() {
        return seen;
    }

    public void getAllFavoritesMovies() {
        disposables.add(mDbRepository.getAllFavoritesMovies()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(favorite_movies::setValue,
                        error -> Log.e(TAG, "getFavoritesMovies: " + error.getMessage()))
        );
    }

    public void getAllFavoritesPersons() {
        disposables.add(mDbRepository.getAllFavoritesPerson()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(favorite_persons::setValue,
                        error -> Log.e(TAG, "getFavoritesPersons: " + error.getMessage()))
        );
    }


    public Movie getMovie(Movie movie) {
        return mDbRepository.retrieveMovie(movie.getId());
    }

    public Person getPerson(Person person) {
        return mDbRepository.retrievePerson(person.getId());
    }


    public void getAllWithStatus(Movie.PersonalStatus status) {
        switch (status) {

            case TO_SEE:
                disposables.add(mDbRepository.getAllMoviesWithStatus(status)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(to_see::setValue,
                                error -> Log.e(TAG, "getToSeeMovies: " + error.getMessage()))
                );
                break;
            case SEEN:
                disposables.add(mDbRepository.getAllMoviesWithStatus(status)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(seen::setValue,
                                error -> Log.e(TAG, "getSeenMovies: " + error.getMessage()))
                );
                break;
        }
    }


    public void insertAll(Movie... movies) {
        mDbRepository.insertAllMovies(movies);
    }


    public void insert(Movie movie) {
        mDbRepository.insert(movie);
    }

    public void insert(Person person) {
        mDbRepository.insert(person);
    }


    public void update(Movie movie) {
        mDbRepository.update(movie);
    }

    public void delete(Movie movie) {
        mDbRepository.delete(movie);
    }

    public void delete(Person person) {
        mDbRepository.delete(person);
    }

}
