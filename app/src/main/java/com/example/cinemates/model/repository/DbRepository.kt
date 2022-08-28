package com.example.cinemates.model.repository

import com.example.cinemates.model.data.Movie
import com.example.cinemates.model.data.Person
import javax.inject.Inject
import com.example.cinemates.model.local.db.AppDatabase
import com.example.cinemates.model.local.dao.MovieDao
import com.example.cinemates.model.local.dao.PersonDao
import com.example.cinemates.model.data.PersonalStatus
import io.reactivex.rxjava3.core.Observable

private const val TAG = "DbRepository"

/**
 * @author Antonio Di Nuzzo
 * Created 26/07/2022 at 08:23
 */
class DbRepository
@Inject
constructor(private val appDatabase: AppDatabase) {
    private val movieDao: MovieDao = appDatabase.mMovieDao()
    private val personDao: PersonDao = appDatabase.mPersonDao()

    val allFavoritesMovies: Observable<List<Movie>>
        get() = movieDao.allFavorite
    val allFavoritesPerson: Observable<List<Person>>
        get() = personDao.allFavorite

    fun getAllMoviesWithStatus(status: PersonalStatus): Observable<List<Movie>> {
        return movieDao.getAllWithStatus(status)
    }

    fun sumRuntimeAllWatchedMovies(): Long {
        return movieDao.sumRuntimeAllWatchedMovies(PersonalStatus.SEEN)
    }

    fun getMovieCountByStatus(status: PersonalStatus): Long {
        return movieDao.getMovieCountByStatus(status)
    }

    fun retrieveMovie(id: Int): Movie {
        return movieDao.retrieveMovie(id)
    }

    fun retrievePerson(id: Int): Person {
        return personDao.retrievePerson(id)
    }

    fun insertAllMovies(vararg movies: Movie?) {
        movieDao.insertAllMovies(*movies)
    }

    fun insert(movie: Movie) {
        movieDao.insert(movie)
    }

    fun insert(person: Person) {
        personDao.insert(person)
    }

    fun update(movie: Movie) {
        movieDao.update(movie)
    }

    fun delete(movie: Movie) {
        movieDao.delete(movie)
    }

    fun delete(person: Person) {
        personDao.delete(person)
    }


}