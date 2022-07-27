package com.example.cinemates.repository;

import com.example.cinemates.local.dao.GenreDao;
import com.example.cinemates.local.db.AppDatabase;
import com.example.cinemates.local.dao.MovieDao;
import com.example.cinemates.model.Genre;
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
    private GenreDao mGenreDao;


    @Inject
    public DbRepository(AppDatabase appDatabase) {
        mMovieDao = appDatabase.mMovieDao();
        mGenreDao = appDatabase.mGenreDao();
    }

    public Observable<List<Movie>> getAllFavoritesMovies() {
        return mMovieDao.getAllFavorite();
    }
    public Observable<List<Genre>> getAllGenres() {
        return mGenreDao.getAllGenres();
    }

    public Observable<List<Genre>> getAllFavoritesGenres() {
        return mGenreDao.getAllFavorite();
    }

    public Observable<List<Movie>> getAllMoviesWithStatus(Movie.PersonalStatus status) {
        return mMovieDao.getAllWithStatus(status);
    }

    public Movie retrieveMovie(Integer id) {
        return mMovieDao.retrieveMovie(id);
    }

    public Genre retrieveGenre(Integer id) {
        return mGenreDao.retrieveGenre(id);
    }

    public void insertAllMovies(Movie... movies) {
        mMovieDao.insertAllMovies(movies);
    }
    public void insertAllGenres(List<Genre> genres) {
        mGenreDao.insertAllGenres(genres);
    }

    public void insert(Movie movie) {
        mMovieDao.insert(movie);
    }

    public void insert(Genre genre) {
        mGenreDao.insert(genre);
    }

    public void update(Movie movie) {
        mMovieDao.update(movie);
    }

    public void delete(Movie movie) {
        mMovieDao.delete(movie);
    }

    public void delete(Genre genre) {
        mGenreDao.delete(genre);
    }
}
