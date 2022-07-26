package com.example.cinemates.repository;

import com.example.cinemates.local.db.AppDatabase;
import com.example.cinemates.local.dao.MovieDao;
import com.example.cinemates.model.Movie;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Observable;

/**
 * @author Antonio Di Nuzzo
 * Created 26/07/2022 at 08:23
 */
public class DbRepository {
    private static final String TAG = DbRepository.class.getSimpleName();
    private MovieDao mMovieDao;


    @Inject
    public DbRepository(AppDatabase appDatabase) {
        mMovieDao = appDatabase.mMovieDao();
    }

    public Observable<List<Movie>> getAllFavorites() {
        return mMovieDao.getAllFavorite();
    }

    public Observable<List<Movie>> getAllWithStatus(Movie.PersonalStatus status) {
        return mMovieDao.getAllWithStatus(status);
    }

    public Movie retrieveMovie(Integer id) {
        return mMovieDao.retrieveMovie(id);
    }

    public void insertAll(Movie... movies) {
        mMovieDao.insertAll(movies);
    }

    public void insert(Movie movie) {
        mMovieDao.insert(movie);
    }

    public void update(Movie movie) {
        mMovieDao.update(movie);
    }

    public void delete(Movie movie) {
        mMovieDao.delete(movie);
    }
}
