package com.example.cinemates.viewmodel;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.cinemates.model.Genre;
import com.example.cinemates.model.Movie;
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
    private final MutableLiveData<List<Genre>> favorite_genres = new MutableLiveData<>();
    private final MutableLiveData<List<Genre>> genres = new MutableLiveData<>();
    private final CompositeDisposable disposables = new CompositeDisposable();


    @Inject
    public DbViewModel(DbRepository dbRepository) {
        mDbRepository = dbRepository;
    }

    public MutableLiveData<List<Movie>> getFavorite_movies() {
        return favorite_movies;
    }

    public MutableLiveData<List<Genre>> getFavorite_genres() {
        return favorite_genres;
    }

    public MutableLiveData<List<Genre>> getGenres() {
        return genres;
    }

    public MutableLiveData<List<Movie>> getTo_see() {
        return to_see;
    }

    public MutableLiveData<List<Movie>> getSeen() {
        return seen;
    }

    public void getAllFavorites() {
        disposables.add(mDbRepository.getAllFavoritesMovies()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(favorite_movies::setValue,
                        error -> Log.e(TAG, "getFavorites: " + error.getMessage()))
        );
    }

    public void getAllGenres() {
        disposables.add(mDbRepository.getAllGenres()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(genres::setValue,
                        error -> Log.e(TAG, "getAllGenres: " + error.getMessage()))
        );
    }

    public Movie getMovie(Movie movie) {
        return mDbRepository.retrieveMovie(movie.getId());
    }

    public Genre getGenre(Integer id) {
        return mDbRepository.retrieveGenre(id);
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

    public void getFavoriteGenres() {

        disposables.add(mDbRepository.getAllFavoritesGenres()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(favorite_genres::setValue,
                        error -> Log.e(TAG, "getFavoriteGenres: " + error.getMessage()))
        );

    }

    public void insertAll(Movie... movies) {
        mDbRepository.insertAllMovies(movies);
    }

    public void insertAll(List<Genre> genres) {
        mDbRepository.insertAllGenres(genres);
    }

    public void insert(Movie movie) {
        mDbRepository.insert(movie);
    }

    public void insert(Genre genre) {
        mDbRepository.insert(genre);
    }

    public void update(Movie movie) {
        mDbRepository.update(movie);
    }

    public void delete(Movie movie) {
        mDbRepository.delete(movie);
    }

    public void delete(Genre genre) {
        mDbRepository.delete(genre);
    }
}
