package com.example.cinemates.model.repository;

import com.example.cinemates.model.local.dao.MovieDao;
import com.example.cinemates.model.local.dao.PersonDao;
import com.example.cinemates.model.local.db.AppDatabase;
import com.example.cinemates.model.data.Movie;
import com.example.cinemates.model.data.Person;

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
    private PersonDao mPersonDao;


    @Inject
    public DbRepository(AppDatabase appDatabase) {
        mMovieDao = appDatabase.mMovieDao();
        mPersonDao = appDatabase.mPersonDao();
    }

    public Observable<List<Movie>> getAllFavoritesMovies() {
        return mMovieDao.getAllFavorite();
    }

    public Observable<List<Person>> getAllFavoritesPerson() {
        return mPersonDao.getAllFavorite();
    }


    public Observable<List<Movie>> getAllMoviesWithStatus(Movie.PersonalStatus status) {
        return mMovieDao.getAllWithStatus(status);
    }

    public long sumRuntimeAllWatchedMovies() {
        return mMovieDao.sumRuntimeAllWatchedMovies(Movie.PersonalStatus.SEEN);
    }

    public long getMovieCountByStatus(Movie.PersonalStatus status) {
        return mMovieDao.getMovieCountByStatus(status);
    }

    public Movie retrieveMovie(Integer id) {
        return mMovieDao.retrieveMovie(id);
    }

    public Person retrievePerson(Integer id) {
        return mPersonDao.retrievePerson(id);
    }


    public void insertAllMovies(Movie... movies) {
        mMovieDao.insertAllMovies(movies);
    }


    public void insert(Movie movie) {
        mMovieDao.insert(movie);
    }

    public void insert(Person person) {
        mPersonDao.insert(person);
    }


    public void update(Movie movie) {
        mMovieDao.update(movie);
    }

    public void delete(Movie movie) {
        mMovieDao.delete(movie);
    }

    public void delete(Person person) {
        mPersonDao.delete(person);
    }

}
